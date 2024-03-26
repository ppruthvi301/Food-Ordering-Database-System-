package com.student.dao;

import java.util.List;
import com.student.entities.Payment;


public interface PaymentDao {

		//insert,delete,update,select
		void insert(Payment pobj);
		void update(String status,double amount);
		void delete(int paymentID);
		void search(int paymentID);
		List<Payment> select();
}