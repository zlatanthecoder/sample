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

		//sessionfactory will be created using hibernate.cfg.xml
		Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg); //SessionFactory will be created only once per application
		Session session = sf.openSession();
		session.getTransaction().begin();

	  //Creating new User Object. C of CRUD
  		for(int i=1;i<=10;i++) {
			UserDetails user = new UserDetails();
			user.setUserName("User "+i);
			session.save(user);  //Creating new User Object. C of CRUD
		}
		
		
		
		//Retrieving/Reading the User value based on the primary key. R of CRUD
			UserDetails user = (UserDetails) session.get(UserDetails.class, 7);
		session.getTransaction().commit();
		session.close();
		System.out.println("UserDetails::"+user);
		
		
		//Deleting the User Details by identifying the object based on primary key. D of CRUD
		UserDetails user = (UserDetails) session.get(UserDetails.class, 7);
		session.delete(user);
		
		
		//Updating the User Details. U of CRUD
  		UserDetails user = (UserDetails) session.get(UserDetails.class, 6);
		user.setUserName("User 9");
		session.update(user);
		
		
		session.getTransaction().commit();
		session.close();

		
		
		
		
		
		
	}

}
