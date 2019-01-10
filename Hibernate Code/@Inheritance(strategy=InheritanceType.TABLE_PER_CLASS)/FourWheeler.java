package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FourWheeler extends Vehicle {

	//we don't have an @Id here because this class is inheriting @Id from its parent class Vehicle. id will be generated based on @GeneratedValue(strategy = GenerationType.TABLE) used in parent class
	
	//in case of strategy=InheritanceType.TABLE_PER_CLASS, the table created is FourWheeler (Vehicle_Name, Steering_Wheel, Vehicle_Id)
	
	@Column(name="Steering_Wheel")
	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
