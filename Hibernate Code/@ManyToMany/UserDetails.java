package com.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="UserDetails")
@Table(name = "User_Details_Mapping")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "User_Id")
	private int userId;

	
	@Column(name = "User_Name")
	private String userName;

	
	@ManyToMany //Many User can have Many Vehicle
		//hibernate will create a mapping table for this entity. User_Details_Mapping_Vehicle (User_Details_Mapping_User_Id, vehicle_Vehicle_Id). now we can alter the JoinTable name & JoinColumn names. inversejoinColumn can also be changed
	@JoinTable(name="User_Vehicle", joinColumns=@JoinColumn(name="User_ID"),
				inverseJoinColumns=@JoinColumn(name="Vehicle_ID"))  //insert into User_Vehicle (User_ID, Vehicle_ID) values (?, ?)	
	private Collection<Vehicle> vehicle = new ArrayList<>(); 
	
	
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Collection<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setVehicle(Collection<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", vehicle=" + vehicle + "]";
	}	



}
