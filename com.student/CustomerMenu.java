package com.student;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.transform.Transformers;

import com.student.dao.FoodItemDao.*;
import com.student.entities.FoodItem.*;



public class CustomerMenu {

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory sf = meta.getSessionFactoryBuilder().build();

	Session session = sf.openSession();
	int choice,foodId;
	String customerName,customerPassword;

	FoodDao foodDao = new FoodDao(session);
	OrderDao orderDao = new OrderDao(session);
	CustomerDao customerDao = new CustomerDao(session);

	Food food;
	Customer customer;
	Scanner sc= new Scanner(System.in);
	Transaction tx = null;

	public void Ordering(String customerName, String customerPassword) {
		Query quser = session.createQuery("SELECT e FROM Customer e WHERE e.customerName = :customerName");
		quser.setParameter("customerName", customerName);
		Customer cUser = (Customer) quser.getSingleResult();

		Query qpws = session.createQuery("SELECT e FROM Customer e WHERE e.customerPassword = :customerPassword");
		qpws.setParameter("customerPassword", customerPassword);
		Customer cPwd = (Customer) qpws.getSingleResult();
		if (customerName.equals(((Customer) quser.getResultList().get(0)).getCustomerName()) && 
				customerPassword.equals(((Customer) qpws.getResultList().get(0)).getCustomerPassword())) {

			System.out.println("-------------| SUCCESSFULLY LOGIN |--------------");
			customer=cUser;
			loginCustomer(customer);		//Login menu for customer 	
		}else {
			System.err.println("INVALIED USER NAEM OR PASSWORD....\n\n");
		}
	}
	public void loginCustomer(Customer customer) {
		try {
			do {
				System.out.println("1.View Menu");
				System.out.println("2.Select Food");
				System.out.println("3.Order Status");
				System.out.println("4.Log out");
				System.out.print("Enter your choice : ");
				choice = sc.nextInt();
				String yesRNo;
				switch(choice) {      //customer menu inside
				case 1:     
					List<Food> list = foodDao.select();
					if(list.isEmpty())
						System.err.println("NO FOOD AVAILABLE RIGHT NOW.....");
					else {
						System.out.println("|----------------------------------------------------|");
						System.out.println("|    Food ID    |    Food Name    |    FoodPrice     |");
						System.out.println("|----------------------------------------------------|");
						for(Food food1 : list) {
							System.out.println("\t"+ food1.getFoodID() +"\t\t"+food1.getFoodName()+"\t  \t"+food1.getFoodPrice());							
						}
						System.out.println("|----------------------------------------------------|");
					}
					break;

				case 2:		
					System.out.println("\n--------| SELECT FROM MENU |---------- ");                                                        
					do {
						System.out.print("Enetr FoodID you want : ");
						foodId = sc.nextInt();
						Booking(foodId,customer);
						System.out.println("\nDo you want select more? type -> (yes/no): ");
						yesRNo = sc.next().toLowerCase();
					}while(!yesRNo.equals("no") && !yesRNo.equals("n"));  
					break;

				case 3:	checkStatus1(customer);		//order status
				break;

				case 4:
					System.out.println("--------------| SUCCESSFULLY LOG OUT |-------------");
					System.out.println("-------------| THANK YOU FOR VISITING |-------------");
					break;

				default :
					System.err.println("INVALIED CHOICES...");
				}                                                                                                //customer menu inside
			}while(choice != 4);
		}catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void Booking(int foodId,Customer customer) {
		try {
			Food food = session.find(Food.class,foodId);
			if(food==null) {
				System.out.println("NO FOOD WITH FOODID.."+foodId);
				return;
			}
			Orders order =new Orders();
			order.setCustomer(customer);
			order.setAmount(food.getFoodPrice()); //select sum(amount) from orders where customerID=1;
			order.setDate(LocalDateTime.now());
			order.setStatus("Pending");
			food.getOrderObj().add(order);
			orderDao.insert(order);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public List<Orders> checkStatus1(Customer customer) {
		try {
			List<Orders> list = session.createQuery("from Orders where customer.customerID=:customerID",Orders.class)
					.setParameter("customerID", customer.getCustomerID())
					.getResultList();
			if(list.isEmpty())
				System.out.println("SORRY! You have no Confirmed Orders");
			else {
				System.out.println("|-------------------------------------------------------------------------------------------|");
				System.out.println(" Order ID \t Order_amount \t Order_Status \t  \t Order_Date \t\t CustomerID");
				System.out.println("|-------------------------------------------------------------------------------------------|");
				for(Orders otObj:list) {
					System.out.println("|  "+otObj.getOrderID()+"    \t    "+otObj.getAmount()+"\t   "+otObj.getStatus()+"\t  "+otObj.getDate()+"\t\t"+otObj.getCustomer().getCustomerID()+"   |");
				}
				System.out.println("|-------------------------------------------------------------------------------------------|");
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String checkStatus(Customer customer) {
		try {
			//			String queryString = "select o FROM orders o where o.customerID =: " + customer.getCustomerID();
			//			
			//			Query query = session.createQuery(queryString);
			//			
			//			Query q = session.createQuery("select o from orders o where o.customerID=:customerID ").setParameter("customerID", customer.getCustomerID());
			//			
			//			System.out.println(q.toString());
			//			
			//			List<Orders> order = new ArrayList<>();

			//					(List<Orders>) session.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(Orders.class));

			//			List<Orders> order = query.getResultList();

			//			List result = ((org.hibernate.query.Query) query).list();

			List<Orders> order = session.createQuery("from Orders where customer.customerID=:customerID",Orders.class)
					.setParameter("customerID", customer.getCustomerID())
					.getResultList();

			if (order.isEmpty()) {

				return "SORRY! You have no Confirmed Orders";
			} else {
				StringBuilder statusInfo = new StringBuilder();
				for (Orders orderList : order) {

					System.out.println(orderList.getStatus());
					statusInfo.append("|\n");

					if("confirmed".equals(orderList.getStatus())) {
						statusInfo.append("---- YOUR ORDER HAS BEEN CONFIRMED!\n");
						statusInfo.append("---- Order ID: ").append(orderList.getOrderID()).append("\n");
					}else {
						statusInfo.append("---- Order ID: ").append(orderList.getOrderID()).append("\n");
						statusInfo.append("---- Status: ").append(orderList.getStatus()).append("\n");
						statusInfo.append("---- PLEASE WAIT FOR CONFIRMATION FROM THE MANAGER.\n");
					}
				}
				System.out.println(statusInfo.toString());
				return statusInfo.toString();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "Error while fetching reservation status.";
		}
	}

}