package com.student.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


	@Entity
	@Table(name = "FoodItem")
	public class FoodItem {
		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "foodID")
	    private int foodID;

	    @Column(name = "foodName")
	    private String foodName;

	    @Column(name = "foodPrice")
	    private double foodPrice;
	    
	    @ManyToMany(cascade=CascadeType.ALL)
		@JoinTable(name="Food_Orders",
		joinColumns={@JoinColumn(name="FoodID")},
		inverseJoinColumns= {@JoinColumn(name="OrderID")}
		)
	    private List<Orders> orderObj =new ArrayList<Orders>();
		

		
		

//		@ManyToMany(mappedBy="foodObj",cascade=CascadeType.ALL)
//		private List<Orders> orderObj = new ArrayList<Orders>();
	    
		
	

	    
	    public FoodItem(int foodID,String foodName,double foodPrice) {
	    	this.foodID = foodID;
	        this.foodName = foodName;
	        this.foodPrice= foodPrice;
	        
	    }
	    
	    public FoodItem(String foodName, double foodPrice) {
	        this.foodName = foodName;
	        this.foodPrice = foodPrice;
	    }

	 // Default constructor
	    public FoodItem() {
	    }

		// Define the getters and setters for each attribute
	    public int getFoodID() {
	        return foodID;
	    }

	    public void setFoodID(int foodID) {
	        this.foodID = foodID;
	    }

	    public String getFoodName() {
	        return foodName;
	    }

	    public void setFoodName(String foodName) {
	        this.foodName = foodName;
	    }

	    public double getFoodPrice() {
	        return foodPrice;
	    }

	    public void setFoodPrice(double foodPrice) {
	        this.foodPrice = foodPrice;
	    }

	    // Define the toString method for displaying the object
	    @Override
	    public String toString() {
	        return "FoodItem {" +
	                "foodID='" + foodID + '\'' +
	                ", foodName='" + foodName +
	                ", foodPrice='" + foodPrice + '\'' +
	                '}';
	    }

		public List<Orders> getOrderObj() {
			// TODO Auto-generated method stub
			return orderObj;

		}

		public void setOrderObj(List<Orders> orderList) {
			// TODO Auto-generated method stub
			this.orderObj = orderObj;

		}


	}