package com.student.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.student.entities.FoodItem;
import org.hibernate.SessionFactory;

public class FoodItemDaoImp implements FoodItemDao
{
	private static SessionFactory sessionFactory;
	private Session session;
	public FoodItemDaoImp(Session session)
	{
		this.session=session;
	}
	public void insert(FoodItem Fobj)
	{
		try {
			Transaction tx = session.beginTransaction();
			session.save(Fobj);
			tx.commit();

			System.out.println(Fobj);
			System.out.println("Record inserted into FoodItem table");		
		  } catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void update(int foodID,String foodName,double foodPrice)
	{
	try {
		FoodItem fobj = session.find(FoodItem.class, foodID );
		if (fobj == null)
			System.out.print("Record not found");
		else {
			fobj.setFoodName(foodName);
			fobj.setFoodPrice(foodPrice);
			Transaction tx = session.beginTransaction();
			session.merge(fobj);
			tx.commit();
			System.out.println(fobj);
			System.out.println("Record updated into FoodItem table");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
	
	public void delete(int foodID)
	{
		try
		{
	FoodItem fobj = session.find(FoodItem.class, foodID);
	if (fobj == null)
		System.out.print("Record not found");
	else {
		Transaction tx = session.beginTransaction();
		session.remove(fobj);
		tx.commit();
		System.out.println(fobj);
		System.out.println("Record deleted from FoodItem table");
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	public List<FoodItem> select()
	{
		Transaction tx = session.beginTransaction();
		Query qobj = session.createQuery("select fobj from FoodItem fobj");
		@SuppressWarnings("unchecked")
		List<FoodItem> list = (List<FoodItem>) qobj.getResultList();
		tx.commit();
		return list;
	}
	
	public void search(int foodID)
	{
		try
		{
		 FoodItem fobj = session.find(FoodItem.class, foodID);
		if (fobj == null)
			System.out.print("Record not found");
		else
			System.out.println(fobj);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	// Other methods
	@Override
	public FoodItem getFoodById(int foodID1) {
		// TODO Auto-generated method stub
		return null;
	}

	public FoodItem getFoodById1(int foodID) {
		Session session = sessionFactory.getCurrentSession();
        FoodItem foodItem = session.get(FoodItem.class, foodID);
        return foodItem;
    }
	}



