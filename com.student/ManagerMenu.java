package com.student;


import java.util.List;
import java.util.Scanner;

import javax.transaction.SystemException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.student.dao.FoodItemDao.*;
import com.student.entities.FoodItem.*;

public class ManagerMenu {

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory sf = meta.getSessionFactoryBuilder().build();

	Session session = sf.openSession();


	CustomerDao customerDao = new CustomerDao(session);
	FoodDao foodDao = new FoodDao(session);
	OrderDao orderDao = new OrderDao(session);

	Customer cObj = null;
	Food fObj = null;
	Orders oObj=null;

	String customerName,customerEmail,customerPassword,customerPhone,customerAddress,foodName;

	int foodPrice;
	int foodId;

	Scanner sc=new Scanner(System.in);

	public void  confirmBooking(int orderID) throws IllegalStateException, SystemException {
		try {
			Transaction tx = null;

			if (!session.getTransaction().isActive()) {
				tx = session.beginTransaction();
			}

			Orders order = session.get(Orders.class,orderID);
			if (order == null) {
				System.out.println("Order not found with ID: " + orderID);
				return;
			}  
			order.setStatus("confirmed");
			session.update(order);
			tx.commit();
			System.out.println("ORDER CONFIRMED SUCCESSFULLY!!");
			System.out.println();

		}catch(Exception e) {

			e.printStackTrace();
		}
	}



	public void displayAllFood() {
		List<Food> list = foodDao.select();
		if(list.isEmpty())
			System.out.println("NO FOOD AVAILABLE RIGHT NOW.....");
		else {
			System.out.println("|----------------------------------------------------|");
			System.out.println("|    Food ID    |    Food Name    |    FoodPrice     |");
			System.out.println("|----------------------------------------------------|");
			for(Food food1 : list) {
				System.out.println("\t"+ food1.getFoodID() +"\t\t"+food1.getFoodName()+"\t  \t"+food1.getFoodPrice());							
			}
			System.out.println("|----------------------------------------------------|");
		}
	}

	public void displayOrders() {
		List<Orders> list = orderDao.select();
		if(list.isEmpty())
			System.out.println("NO ORDER'S ARE THERE RIGHT NOW..... ");
		else {
			System.out.println("|-------------------------------------------------------------------------------------------|");
			System.out.println(" Order ID \t Order_amount \t Order_Status \t  \t Order_Date \t\t CustomerID");
			System.out.println("|-------------------------------------------------------------------------------------------|");
			for(Orders otObj:list) {
				System.out.println("|  "+otObj.getOrderID()+"    \t    "+otObj.getAmount()+"\t   "+otObj.getStatus()+"\t  "+otObj.getDate()+"\t\t"+otObj.getCustomer().getCustomerID()+"   |");
			}
			System.out.println("|-------------------------------------------------------------------------------------------|");
		}
	}

	public void addFood(String foodName,double foodPrice) {
		fObj = new Food();
		fObj.setFoodName(foodName);
		fObj.setFoodPrice(foodPrice);
		foodDao.insert(fObj);
	}

	public void deleteFood() {
		System.out.print("Enter food ID : ");
		foodId = sc.nextInt();
		foodDao.delete(foodId);
	}

	public void customerDetails() {
		List<Customer> list1 = customerDao.select();
		if(list1.isEmpty())
			System.out.println("No records found");
		else {
			System.out.println("|---------------------------------------------------------------------------------------------------------------|");
			System.out.println("|   ID         Name              Email ID           Password            Phone                  Address          |");
			System.out.println("|---------------------------------------------------------------------------------------------------------------|");
			for(Customer caObj:list1) {
				System.out.println("|   "+caObj.getCustomerID()+"\t|\t"+caObj.getCustomerName()+"\t|\t"+caObj.getCustomerEmail()+"\t|\t"+caObj.getCustomerPassword()+"\t|\t"+caObj.getCustomerPhone()+"\t|\t"+caObj.getCustomerAddress()+"\t|");
			}
			System.out.println("|---------------------------------------------------------------------------------------------------------------|");
		}
	}
}