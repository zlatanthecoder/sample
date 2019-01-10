package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Vehicle_Id")
	private int vehicleId;

	
	@Column(name="Vehicle_Name")
	private String vehicleName;
	
	
	@ManyToOne //Many Vehicle can have One User. now we have mapped user to the vehicle so separate table will not be required as a separate column user_User_Id will be created in Vehicle table
		//this line not required if you want hibernate to create a new mapping table
	@JoinColumn(name="User_ID") //naming the mapping/joining column is creating inside Vehicle table itself. now hibernate knows mapping for this user which is defined using mappedBy attribute in UserDetails class & JoinColumn defined here so it will not create new table.
	private UserDetails user;

	
	public int getVehicleId() {
		return vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleName=" + vehicleName + ", user=" + user + "]";
	}



}
