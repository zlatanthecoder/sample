package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle_Inheritance")
@Inheritance(strategy=InheritanceType.JOINED) //in this strategy the data is much normalized form. the data which is inherited from the parent table doesn't go into child table.
	//so the child will contain the child class specific properties & parent table will contains all the common properties that are defined in parent class
	//to get the full child data we have to use join with the parent table
	//select * from vehicle_inheritance inner join  twowheeler on vehicle_inheritance.vehicle_id=twowheeler.vehicle_id;
public class Vehicle {

	@Id
	@GeneratedValue //in this hibernate will choose the best strategy itself
	@Column(name="Vehicle_Id")
	private int vehicleId;

	
	@Column(name="Vehicle_Name")
	private String vehicleName;

	
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

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleName=" + vehicleName + "]";
	}


}
