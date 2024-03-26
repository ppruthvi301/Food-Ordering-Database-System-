package com.foodEntity;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;




@Entity
@Table(name = "Orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderID;	

	private LocalDateTime date;

	private double amount;

	private String status;

	@ManyToOne
	@JoinColumn(name="customerID")
	private Customer customer;




	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToMany(mappedBy="orderObj",cascade=CascadeType.ALL)
	private Set<Food> foodObj = new HashSet<Food>();



	public int getOrderID() {
		return orderID;
	}



	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}



	public LocalDateTime getDate() {
		return date;
	}



	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public void setDate(LocalDate now) {

	}

}