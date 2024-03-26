package com.foodEntity;

import java.util.*;

import javax.persistence.*;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;
	private String customerName;
	private String customerEmail;
	private String customerPassword;
	private String customerAddress;
	private String customerPhone;
	
	


	@OneToMany(mappedBy = "customer")
	private List<Orders> order ;
	
	public Customer() {
		
	}
	
	 public Customer(int customerID,String customerName,String customerEmail,String customerpassword, String customerAddress,String customerPhone)
	    {
	    	super();
	    	this.customerID = customerID;
	    	this.customerName = customerName;
	    	this.customerEmail = customerEmail;
	    	this.customerPassword = customerpassword;
	    	this.customerAddress = customerAddress;
	    	this.customerPhone=customerPhone;
	    }



	public int getCustomerID() {
		return customerID;
	}



	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getCustomerEmail() {
		return customerEmail;
	}



	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}



	public String getCustomerPassword() {
		return customerPassword;
	}



	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}



	public String getCustomerAddress() {
		return customerAddress;
	}



	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}



	public String getCustomerPhone() {
		return customerPhone;
	}



	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}
	 
	 
	
}