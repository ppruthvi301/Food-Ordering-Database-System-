package com.student.dao;


import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.student.entities.Payment;

public class PaymentDaoImp implements PaymentDao
{
	private Session session;
	public PaymentDaoImp(Session session)
	{
		this.session=session;
	}
	public void insert(Payment pobj)
	{
		try {
			Transaction tx = session.beginTransaction();
			session.save(pobj);
			tx.commit();

			System.out.println(pobj);
			System.out.println("Record inserted into FoodItem table");		
		  } catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void update(int paymentID, int orderID,String status,double amount)
	{
	try {
		Payment pobj = session.find(Payment.class, paymentID );
		if (pobj == null)
			System.out.print("Record not found");
		else {
			pobj.setStatus(status);
			pobj.setAmount(amount);
			Transaction tx = session.beginTransaction();
			session.merge(pobj);
			tx.commit();
			System.out.println(pobj);
			System.out.println("Record updated into Payment table");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
	
	public void delete(int paymentID)
	{
		try
		{
			Payment pobj = session.find(Payment.class, paymentID);
	if (pobj == null)
		System.out.print("Record not found");
	else {
		Transaction tx = session.beginTransaction();
		session.remove(pobj);
		tx.commit();
		System.out.println(pobj);
		System.out.println("Record deleted from Payment table");
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	public List<Payment> select()
	{
		Transaction tx = session.beginTransaction();
		Query qobj = session.createQuery("select pobj from Payment pobj");
		@SuppressWarnings("unchecked")
		List<Payment> list = (List<Payment>) qobj.getResultList();
		tx.commit();
		return list;
	}
	
	public void search(int paymentID)
	{
		try
		{
			Payment pobj = session.find(Payment.class, paymentID);
		if (pobj == null)
			System.out.print("Record not found");
		else
			System.out.println(pobj);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(String status, double amount) {
		// TODO Auto-generated method stub
		
	}

}

