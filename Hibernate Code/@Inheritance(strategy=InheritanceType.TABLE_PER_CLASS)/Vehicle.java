package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle_Inheritance")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) //in this case we dont need Discriminator type column & there is no column which is not applicable for a particular record
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE) //GenerationType.TABLE strategy should be used as we have underlying tables as well in this case TwoWheeler & FourWheeler so the generated Id value will be inherited to these child classes
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
