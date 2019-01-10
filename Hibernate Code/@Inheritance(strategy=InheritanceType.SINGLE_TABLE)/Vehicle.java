package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle_Inheritance")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //by default hibernate will adopt the single table strategy for inheritance so if we don't mentioned @Inheritance(strategy=InheritanceType.SINGLE_TABLE) then also hibernate will go for single table strategy
	//so only 1 table Vehicle will be created. TwoWheeler & FourWheeler table won't be created separately
@DiscriminatorColumn(
			name="VEHICLE_TYPE", discriminatorType=DiscriminatorType.STRING //by default the Discriminator column name is DTYPE which contains the class name
		)
//@DiscriminatorValue("Vehicle_Discriminator_Name") // we can change Vehicle discriminatory column name also
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
