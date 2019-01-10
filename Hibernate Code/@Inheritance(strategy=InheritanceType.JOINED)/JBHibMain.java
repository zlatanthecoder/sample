package com.hibernate.main.jb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.hibernate.model.jb.FourWheeler;
import com.hibernate.model.jb.TwoWheeler;
import com.hibernate.model.jb.UserDetails;
import com.hibernate.model.jb.Vehicle;

public class JBHibMain {
	
	public static void main(String[] args) {

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Car");
		
		TwoWheeler bike = new TwoWheeler();
		bike.setVehicleName("Pulsar");
		bike.setSteeringHandle("Pulsar Steering Handle");
		
		FourWheeler car = new FourWheeler();
		car.setVehicleName("Porsche");
		car.setSteeringWheel("Porsche Steering Wheel");
		



		//sessionfactory will be created using hibernate.cfg.xml
		Configuration con = new Configuration().configure().addAnnotatedClass(TwoWheeler.class).addAnnotatedClass(Vehicle.class).addAnnotatedClass(FourWheeler.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg); //SessionFactory will be created only once per application
		Session session = sf.openSession();
		session.getTransaction().begin();

			session.save(vehicle);  	
			session.save(bike); 
			session.save(car); 

/*          session.save(user);
 *			session.save(vehicle1);
			session.save(vehicle2);*/
		session.getTransaction().commit();
		session.close();

/*		user=null;
		vehicle1=null;
		vehicle2=null;
		session = sf.openSession();
		session.getTransaction().begin();
		    user=(UserDetails) session.get(UserDetails.class, 1);
		System.out.println("Fetching User Details::"+user); 			
			vehicle2=(Vehicle) session.load(Vehicle.class, 2);
		System.out.println("Fetching Vehicle Details::"+vehicle2);		
        user =	vehicle2.getUser();
       System.out.println(user);
			vehicle2=(Vehicle) session.get(Vehicle.class, 2);
		System.out.println("Fetching Vehicle Details::"+vehicle2); 

		session.getTransaction().commit();
		session.close();*/
		
		
		
		
		
		
		
	}

}
