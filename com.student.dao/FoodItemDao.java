package com.student.dao;

import java.util.List;
import com.student.entities.FoodItem;

public interface FoodItemDao {
      
	//insert,delete,update,select
	void insert(FoodItem Fobj);
	void update(int foodID,String foodName,double foodPrice);
	void delete(int foodID);
	void search(int foodID);
	List<FoodItem> select();
	FoodItem getFoodById(int foodID1);
	static FoodItem getFoodById1(int foodID) {
		// TODO Auto-generated method stub
		return null;
	}
}
