package com.student.dao;

import java.util.List;


import com.student.entities.Manager;
import com.student.entities.Orders;


public interface ManagerDao {
	
	void insert(Manager mObj);
	
	void update(int managerID,String managerName,String managerEmail,String managerPWD,String managerPhone);
	
	void delete(int managerID);
	
	void search(int managerID);
	
	List<Manager>select();

}
