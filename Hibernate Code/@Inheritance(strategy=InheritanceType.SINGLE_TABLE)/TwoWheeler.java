package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bike") //by default hibernate will enter the class name as a value in the discriminator column. so using this annotation we can change that name. 
	//now all objects of TwoWheeler class that are persisted will have discriminator column value as "Bike"
public class TwoWheeler extends Vehicle {

	//we don't have an @Id here because this class is inheriting @Id from its parent class Vehicle
	
	@Column(name="Steering_Handle")
	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
	
}
