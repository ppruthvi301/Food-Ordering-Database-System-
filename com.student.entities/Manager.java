package com.student.entities;

import java.util.*;
import javax.persistence.*;
import org.hibernate.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	@Table(name = "Manager")
	public class Manager {
		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "managerID")
	    private int managerID;

	    @Column(name = "managerName")
	    private String managerName;

	    @Column(name = "managerEmail")
	    private String managerEmail;

	    @Column(name = "managerPWD")
	    private String managerPWD;

	    @Column(name = "managerPhone")
	    private String managerPhone;

	    public Manager(int managerID,String managerName,String managerEmail, String managerPWD, String managerPhone) {
	        this.managerID = managerID;
	        this.managerName = managerName;
	        this.managerEmail = managerEmail;
	        this.managerPWD = managerPWD;
	        this.managerPhone = managerPhone;
	    }
	    
	    // Define the getters and setters for each attribute
	    public int getManagerID() {
	        return managerID;
	    }

	    public void setManagerID(int managerID) {
	        this.managerID = managerID;
	    }

	    public String getManagerName() {
	        return managerName;
	    }

	    public void setManagerName(String managerName) {
	        this.managerName = managerName;
	    }

	    public String getManagerEmail() {
	        return managerEmail;
	    }

	    public void setManagerEmail(String managerEmail) {
	        this.managerEmail = managerEmail;
	    }

	    public String getManagerPWD() {
	        return managerPWD;
	    }

	    public void setManagerPWD(String managerPWD) {
	        this.managerPWD = managerPWD;
	    }

	    public String getManagerPhone() {
	        return managerPhone;
	    }

	    public void setManagerPhone(String managerPhone) {
	        this.managerPhone = managerPhone;
	    }

	    // Define the toString method for displaying the object
	    @Override
	    public String toString() {
	        return "Manager{" +
	                "managerID='" + managerID + '\'' +
	                ", managerName='" + managerName +
	                ", managerEmail='" + managerEmail + '\'' +
	                ", managerPWD ='" +managerPWD + '\'' +
	                ", managerPhone='" + managerPhone + '\'' +
	                '}';
	}
}