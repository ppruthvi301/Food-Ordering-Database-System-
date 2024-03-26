package com.student.dao;

import java.sql.Date;
import java.util.List;

import com.student.entities.Customer;
import com.student.entities.FoodItem;
import com.student.entities.Orders;

public interface OrderDao {
	//insert,delete,update,select
	static void insert(Orders robj) {
		// TODO Auto-generated method stub
		
	}
	void update(int orderID,Date date,double amount);
	void delete(int orderID);
	void search(int orderID);
	List<Orders> select();
	static Orders getOrderDetails(int orderID) {
		return null;
	}
	List<Orders> getCartByCustomerId(int customerID);
	List<Orders> getOrdersByCustomerId(int customerID);
	void addOrder(Orders oObj1);
	static List<Orders> getOrdersByCustomerAndFood(Customer customer, FoodItem foodItem) {
		// TODO Auto-generated method stub
		return null;
	}
}
