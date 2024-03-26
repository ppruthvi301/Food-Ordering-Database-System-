package com.student.dao;
	import java.util.List;
	import com.student.entities.Customer;

	public interface CustomerDao {
		void insert( Customer cobj );
		 void delete(int customerID);
		 void update(int customerID,String customerName,String customerEmail, String customerPWD, String customerAddress, String customerPhone);
		 void search(int customerID);
		 List<Customer>select();
		Customer getCustomerByEmailAndPWD(String customerName, String customerPWD);
		
	}

