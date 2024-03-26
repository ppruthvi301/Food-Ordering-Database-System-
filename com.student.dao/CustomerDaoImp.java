package com.student.dao;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.student.entities.Customer;
import com.student.entities.FoodItem;


public class CustomerDaoImp implements CustomerDao{

	private Session session;

    public  CustomerDaoImp(Session session)
	{
		this.session=session;
	}

	@Override
	public void insert(Customer cobj) {
		// TODO Auto-generated method stub
		try {
			Transaction tx = session.beginTransaction();
			session.save(cobj);
			tx.commit();

			System.out.println(cobj);
			System.out.println("Detail inserted in CustomerTable:");		
		  } catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void delete(int customerID) {
		// TODO Auto-generated method stub
		try
		{
	Customer customerid = session.find(Customer.class, customerID);
	if (customerid == null)
		System.out.print("Record not found");
	else {
		Transaction tx = session.beginTransaction();
		session.remove(customerid);
		tx.commit();
		System.out.println(customerid);
		System.out.println("Detail deleted from CustomerTable");
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}

		
	}

	@Override
	public void update(int customerID, String customerName, String customerEmail, String customerPWD,
			String customerAddress, String customerPhone) {
		// TODO Auto-generated method stub
		try {
			Customer customerid  = session.find(Customer.class, customerID );
			if (customerid == null)
				System.out.print("Detail not found");
			else {
				customerid.setCustomerName(customerName);
				customerid.setCustomerEmail(customerEmail);
				customerid.setCustomerPWD(customerPWD);
				customerid.setCustomerAddress(customerAddress);
				customerid.setCustomerPhone(customerPhone);
				Transaction tx = session.beginTransaction();
				session.merge(customerid);
				tx.commit();
				System.out.println(customerid);
				System.out.println(" Detail updated into CustomerTable");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void search(int customerID) {
		// TODO Auto-generated method stub
		try
		{
		 Customer customerid = session.find(Customer.class, customerID);
		if (customerid == null)
			System.out.print("Detail not found");
		else
			System.out.println(customerid);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}

	@Override
	public List<Customer> select() {
		Transaction tx = session.beginTransaction();
		Query qobj = session.createQuery("select fobj from FoodItem fobj");
		@SuppressWarnings("unchecked")
		List<Customer> list = (List<Customer>) qobj.getResultList();
		tx.commit();
		return list;
	}       
}