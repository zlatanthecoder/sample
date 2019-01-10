package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TwoWheeler extends Vehicle {

	//we don't have an @Id here because this class is inheriting @Id from its parent class Vehicle. id will be generated based on @GeneratedValue(strategy = GenerationType.TABLE) used in parent class
	
	//in case of strategy=InheritanceType.TABLE_PER_CLASS, the table created is TwoWheeler (Vehicle_Name, Steering_Handle, Vehicle_Id)
	
	@Column(name="Steering_Handle")
	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
	
}
