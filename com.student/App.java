package com.student;


import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.foodDao.*;
import com.foodEntity.*;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public class App {
	public static void main( String[] args ){
		try {
			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
			SessionFactory sf = meta.getSessionFactoryBuilder().build();

			Session session = sf.openSession();

			int mainChoice,cusChoice,manChoice;


			String customerName,customerEmail,customerPassword,customerPhone,customerAddress,foodName;

			double foodPrice;
			int foodId;


			Customer cObj = null;
			CustomerMenu customerMenu = new CustomerMenu();
			ManagerMenu managerMenu = new ManagerMenu();

			CustomerDao customerDao = new CustomerDao(session);


			Scanner sc=new Scanner(System.in);
			do {//main menu do
				System.out.println("\n\n----------| WELLCOM TO FOOD ORDERING SYSTEM MANAGEMENT SYSTEM |----------\n");	
				System.out.println("------------------------| SELECT ANY ONE MENU |--------------------------");
				System.out.println("1.Customer");
				System.out.println("2.Manager");
				System.out.println("3.Exit\n");
				System.out.print("Enter your choice : ");
				mainChoice=sc.nextInt();
				switch(mainChoice){//customer menu do
				case 1: 
					do {
						System.out.println("\n-----------------------| WELLCOM CUSTOMER |--------------------------");
						System.out.println("----------| SELECT WHICH TYPE OF SERVICES WE CAN PROVIDE  |----------");
						System.out.println("1.CREATE NEW ACCOUNT ");
						System.out.println("2.LOGIN ");
						System.out.println("3.BACK TO MAIN MENU ");
						System.out.print("Enter your choice : ");
						cusChoice = sc.nextInt();
						switch(cusChoice) {
						case 1: 
							System.out.println("\n-------------| PLEASE PROVIDE DETAILS |-------------");
							System.out.print("Enter Your Name : ");
							sc.nextLine();
							customerName = sc.nextLine();

							System.out.print("Enter Your E-Mail : ");
							customerEmail = sc.nextLine();

							System.out.print("Enter Strong Password : ");
							customerPassword = sc.nextLine();

							System.out.print("Enter your Address : ");
							customerAddress =  sc.nextLine();

							System.out.print("Enter your Phone Number : ");
							customerPhone =  sc.nextLine();

							cObj = new Customer();
							cObj.setCustomerName(customerName);
							cObj.setCustomerEmail(customerEmail); 
							cObj.setCustomerPassword(customerPassword);
							cObj.setCustomerAddress(customerAddress);
							cObj.setCustomerPhone(customerPhone);
							customerDao.insert(cObj);
							break;
						case 2:
							System.out.print("\nENTER USER NAME : ");
							sc.nextLine();
							customerName = sc.nextLine();
							System.out.print("ENTER PASSWORD : ");
							customerPassword = sc.nextLine();

							cObj = customerDao.getCustomerByEmailAndPassword(customerName, customerPassword);
							if(cObj!=null) {
								customerMenu.Ordering(customerName, customerPassword);
							} else {
								System.out.println("Customer not found");
							}

							break;
						case 3:

							break;

						default :
							System.err.println("INVALIED CHOICES...");

						}//switch for  
					}while(cusChoice != 3);
					break;
				case 2:  
					do{
						System.out.println("\n--------- WELCOME  MANAGER ----------");
						System.out.println("-------------------------------------");
						System.out.println("\n1.Display All Food");
						System.out.println("2.Add To List");
						System.out.println("3.Delete from list");
						System.out.println("4.Customer Details");
						System.out.println("5.Conforming Order");
						System.out.println("6.Log out");
						System.out.print("Enter your choice : ");
						manChoice = sc.nextInt();
						switch(manChoice) {
						case 1: //Display all food
							managerMenu.displayAllFood();
							break;

						case 2:	//add to list
							System.out.print("Enter Food name : ");
							sc.nextLine();
							foodName = sc.nextLine();
							System.out.print("Enter Food price : ");
							foodPrice= sc.nextInt();
							managerMenu.addFood(foodName,foodPrice);
							break;

						case 3://Delete to list
							managerMenu.deleteFood();
							break;

						case 4://customer Details
							managerMenu.customerDetails();
							break;

						case 5://confirming order
							System.out.println("-------------  ALL ORDERS  --------------");
							managerMenu.displayOrders();
							System.out.print("Enter Order ID to confirm  the Order : ");
							foodId = sc.nextInt();
							managerMenu.confirmBooking(foodId);
							break;

						case 6:	System.out.println("--------- Successfully log out -------------");
						break;
						default :
							System.err.println("INVALIED CHOICES...");
						}
					}while(manChoice !=6);
					break;
				case 3:
					break;

				default:
					System.err.println("INVALIED CHOICES...");

				}//customer menu do while
			}while(mainChoice != 3);//main menu while do
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}