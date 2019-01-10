package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.hibernate.model.UserDetails;
import com.hibernate.model.Vehicle;

public class JBHibMain {
	
	public static void main(String[] args) {

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVehicleName("Car");
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Jeep");
		
		UserDetails user = new UserDetails();		
		user.setUserName("First User");
		user.getVehicle().add(vehicle1);	
		user.getVehicle().add(vehicle2);

		vehicle1.getUserlist().add(user);
		vehicle2.getUserlist().add(user);

		//sessionfactory will be created using hibernate.cfg.xml
		Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class).addAnnotatedClass(Vehicle.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg); //SessionFactory will be created only once per application
		Session session = sf.openSession();
		session.getTransaction().begin();
			session.save(user);			
			session.save(vehicle1);
			session.save(vehicle2);
		session.getTransaction().commit();
		session.close();

/*		user=null;
		vehicle1=null;
		vehicle2=null;
		session = sf.openSession();
		session.getTransaction().begin();
		    user=(UserDetails) session.get(UserDetails.class, 1);
		System.out.println("Fetching User Details::"+user); 			
			vehicle1=(Vehicle) session.get(Vehicle.class, 1);
		System.out.println("Fetching Vehicle Details::"+vehicle1); 		
			vehicle2=(Vehicle) session.get(Vehicle.class, 2);
		System.out.println("Fetching Vehicle Details::"+vehicle2); 

		session.getTransaction().commit();
		session.close();
		*/
		
		
		
		
		
		
	}

}
