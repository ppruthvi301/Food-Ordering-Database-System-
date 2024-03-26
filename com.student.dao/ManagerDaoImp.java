package com.student.dao;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.student.entities.Manager;

public class ManagerDaoImp implements ManagerDao{

	private Session session;

	public ManagerDaoImp(Session session){
		this.session=session;
	}


	@Override
	public void insert(Manager mObj) {
		try {
			Transaction tx = session.beginTransaction();
			session.save(mObj);
			tx.commit();
			System.out.println(mObj);
			System.out.println("Record inserted into FoodItem table");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int managerID) {
		try
		{
			Manager mObj = session.find(Manager.class,managerID);
			if (mObj == null)
				System.out.print("Record not found");
			else {
				Transaction tx = session.beginTransaction();
				session.remove(mObj);
				tx.commit();
				System.out.println(mObj);
				System.out.println("Record deleted from FoodItem table");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void search(int managerID) {
		try{
			Manager mObj = session.find(Manager.class,managerID);
			if (mObj == null)
				System.out.print("Record not found");
			else {
				System.out.println(mObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public List<Manager> select() {
		Transaction tx = session.beginTransaction();
		Query qobj = session.createQuery("SELECT customer FROM Customer customer");
		@SuppressWarnings("unchecked")
		List<Manager> list = qobj.getResultList();
		tx.commit();
		return list;
	}



	@Override
	public void update(int managerID, String managerName, String managerEmail, String managerPWD, String managerPhone) {
		try {
			Manager mObj = session.find(Manager.class,managerID);
			if(mObj==null)
				System.out.println("Record not found");
			else {
				mObj.setManagerID(managerID);
				mObj.setManagerName(managerName);
				mObj.setManagerEmail(managerEmail);
				mObj.setManagerPWD(managerPWD);
				mObj.setManagerPhone(managerPhone);
				Transaction tx = session.beginTransaction();
				session.merge(mObj);
				tx.commit();
				System.out.println(mObj);
				System.out.println("Record updated into FoodItem table");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}



}