package com.hibernate.main.jb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.hibernate.model.jb.FourWheeler;
import com.hibernate.model.jb.TwoWheeler;
import com.hibernate.model.jb.UserDetails;
import com.hibernate.model.jb.Vehicle;

import net.sf.ehcache.search.expression.Not;

public class ObjectStateMain {
	
	public static void main(String[] args) {
			
		//before an object is handed over to hibernate, it is a Transient object. Hibernate doesn't track this object here. transient state means hibernate not even looked at that object yet
		UserDetails user = new UserDetails(); 
		user.setUserName("Test User");
		 

		//sessionfactory will be created using hibernate.cfg.xml
		Configuration configuration = new Configuration().configure().addAnnotatedClass(UserDetails.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry); //SessionFactory will be created only once per application
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		user.setUserName("Updated User before Calling Save"); 

		//Now i am giving the responsibility to hibernate to make sure that Object State matches the Database State so no matter what update i make to this persisted object it is gonna reflected in the database
			session.save(user); //now user object is become a Persistent Object(once i handed over to hibernate it become a persistent object)
			
		user.setUserName("Updated User After Save"); //any changes made to the Persistent object will get reflected in the database automatically
		user.setUserName("Updated User Before Commit"); //the last update before the commit will get reflected in the database
		
		
		session.getTransaction().commit();
		session.close(); //Detach Object. means hibernate is not going to track the changes. so no matter what changes or update we make to the object, it is not going to be persisted in the database

		user.setUserName("Updated User After Session Close"); //after session.close() object goes into Detach state means hibernate will not gonna track any changes made in that object
		
		
/*  ********Object State Notes******
  	-----Object State-Create------
	new()-> Transient state
	session.save()-> Persistent state
	session.close()-> Detached state

	----Object State-Read----
	get()-> Persistent state
	session.close()-> Detached state

	----Object State-Delete----
	Persistent state object->session.delete()-> Transient state
	Persistent state object->session.close()-> Detached state

	
 */
		/* Detached State to Persistent State Code
		  ---------------------------------------- */
		System.out.println();
		System.out.println("------------Detached State to Persistent State Code------------------");
		session = sessionFactory.openSession();
		session.getTransaction().begin();
		user = (UserDetails) session.get(UserDetails.class, 1); //get() method always returns the super class object (Object). so we need to typecast it
		
		//user is performing some operations after fetching data from database. so it will take some time for user to make the data ready for updation.so closing the session
		session.getTransaction().commit();
		session.close();
		
			user.setUserName("User object is moving from Detached State to Persistent State");
		
		session = sessionFactory.openSession();
		session.getTransaction().begin();
		
			//Object is moving from Detached State to Persistent State
			session.update(user); //hibernate will check what are the update that are not saved in the database & will persist those in the database
			//user.setUserName("Updated value after save"); this would be saved as this is the last updated value in the session
			
			/*
			 Hibernate: select userdetail0_.User_Id as User1_0_0_, userdetail0_.User_Name as User2_0_0_ from User_Details_ObjectState userdetail0_ where userdetail0_.User_Id=?
			 Hibernate: select userdetail_.User_Id, userdetail_.User_Name as User2_0_ from User_Details_ObjectState userdetail_ where userdetail_.User_Id=?
 			 Hibernate: update User_Details_ObjectState set User_Name=? where User_Id=?
			 */
			
		session.getTransaction().commit();
		session.close();
		 //User object is in Detached State
		
		/*
		    selectBeforeUpdate example
		 */
		System.out.println();
		System.out.println("------------selectBeforeUpdate example------------------");		
		session = sessionFactory.openSession();
		session.getTransaction().begin();

			//Object is moving from Detached State to Persistent State
			session.update(user);
		/*here we are not modifying any changes to the user object so update query should not run. but update query will run as hibernate don't know as if any modification is done or not
		  so hibernate should do a select before any update just to check that any modification done on user object or Not.
		  so to enable this we have to use a hibernate specific annotation @SelectBeforeUpdate(value=true)	in our Entity class
		  Hibernate: select userdetail_.User_Id, userdetail_.User_Name as User2_0_ from User_Details_ObjectState userdetail_ where userdetail_.User_Id=?
		*/
		session.getTransaction().commit();
		session.close();
	}

}
