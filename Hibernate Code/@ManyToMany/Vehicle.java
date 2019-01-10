package com.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Vehicle_Id")
	private int vehicleId;

	
	@Column(name="Vehicle_Name")
	private String vehicleName;
	
	//@ManyToMany //Many Vehicle can have Many User
		//hibernate will create a mapping table for this entity. Vehicle_User_Details_Mapping (Vehicle_Vehicle_Id, userlist_User_Id) 
	@ManyToMany(mappedBy="vehicle") //mapping is defined to Vehicle table by the property name defined in UserDetails class so hibernate will not create a separate table for Vehicle class.Vehicle_User_Details_Mapping table will not be created
	private Collection<UserDetails> userlist = new ArrayList<>();

	
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

	public Collection<UserDetails> getUserlist() {
		return userlist;
	}

	public void setUserlist(Collection<UserDetails> userlist) {
		this.userlist = userlist;
	}





}
