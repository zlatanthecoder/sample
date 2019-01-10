package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Car") //all objects of FourWheeler class that are persisted will have discriminator column value as "Car"
public class FourWheeler extends Vehicle {

	//we don't have an @Id here because this class is inheriting @Id from its parent class Vehicle
	
	@Column(name="Steering_Wheel")
	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
