package com.student.dao;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.student.entities.Orders;


public class OrderDaoImp implements OrderDao
{
	private Session session;
	public OrderDaoImp(Session session)
	{
		this.session=session;
	}
	public void insert(Orders robj)
	{
		try {
			Transaction tx = session.beginTransaction();
			session.save(robj);
			tx.commit();

			System.out.println(robj);
			System.out.println("Record inserted into order table");		
		  } catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void update(int orderID,LocalDate date,double amount)
	{
	try {
		Orders robj = session.find(Orders.class, orderID );
		if (robj == null)
			System.out.print("Record not found");
		else {
			robj.setDate(date);
			robj.setAmount(amount);
			Transaction tx = session.beginTransaction();
			session.merge(robj);
			tx.commit();
			System.out.println(robj);
			System.out.println("Record updated into order table");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
	
	public void delete(int orderID)
	{
		try
		{
			Orders robj = session.find(Orders.class,orderID);
	if (robj == null)
		System.out.print("Record not found");
	else {
		Transaction tx = session.beginTransaction();
		session.remove(robj);
		tx.commit();
		System.out.println(robj);
		System.out.println("Record deleted from order table");
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	public List<Orders> select()
	{
		Transaction tx = session.beginTransaction();
		Query qobj = session.createQuery("select robj from order robj");
		@SuppressWarnings("unchecked")
		List<Orders> list = (List<Orders>) qobj.getResultList();
		tx.commit();
		return list;
	}
	
	public void search(int orderID)
	{
		try
		{
			Orders robj = session.find(Orders.class, orderID);
		if (robj == null)
			System.out.print("Record not found");
		else
			System.out.println(robj);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private static SessionFactory sessionFactory;
	public Orders getOrderDetails(int orderID) {
		Session session = sessionFactory.getCurrentSession();
	    Transaction transaction = null;
	    Orders order = null;
	    try {
	        transaction = session.beginTransaction();
	        order = session.get(Orders.class, orderID);
	        transaction.commit();
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return order;
	}
	
	// OrderDaoImp.java
	public List<Orders> getCartByCustomerId(int customerId) {
	    String hql = "FROM Order o WHERE o.customer.customerID = :customerId AND o.orderStatus = 'CART'";
	    Session session = sessionFactory.getCurrentSession();
	    Query<Orders> query = session.createQuery(hql, Orders.class);
	    query.setParameter("customerId", customerId);
	    return query.getResultList();
	}
	
	// OrderDaoImp.java
	public List<Orders> getOrdersByCustomerId(int customerId) {
	    String hql = "FROM Order o WHERE o.customer.customerID = :customerId AND o.orderStatus != 'CART'";
	    Session session = sessionFactory.getCurrentSession();
	    Query<Orders> query = session.createQuery(hql, Orders.class);
	    query.setParameter("customerId", customerId);
	    return query.getResultList();
	}
	
	public void addOrder(Orders order) {
	    Session session = sessionFactory.getCurrentSession();
	    session.save(order);
	}
	@Override
	public void update(int orderID, Date date, double amount) {
		// TODO Auto-generated method stub
		
	}
	}
