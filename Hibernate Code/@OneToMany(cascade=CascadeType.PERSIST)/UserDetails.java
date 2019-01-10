package com.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	//One User can have Many Vehicle
	@OneToMany(cascade=CascadeType.PERSIST) //persist this collection when you persist this entity
		//CascadeType.PERSIST tells hibernate that if it sees any new entities inside vehicle collection which have not been saved, when the user been saved go ahead & save these vehicle objects as well.
		//suppose this collection has 50 vehicle so by using Cascade we are avoiding saving all these 50 vehicle object separately. now with cascade feature when we persist that user entity then it will save all those 50 vehicle objects automatically
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
