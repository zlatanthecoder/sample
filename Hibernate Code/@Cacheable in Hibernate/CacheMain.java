package com.hibernate.main.jb;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.hibernate.model.jb.UserDetails;

public class CacheMain {
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
					
		//First Level Cache= Session
		// Second Level Cache-> Across different sessions in an application

		//sessionfactory will be created using hibernate.cfg.xml
		Configuration configuration = new Configuration().configure().addAnnotatedClass(UserDetails.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry); //SessionFactory will be created only once per application
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();		

		UserDetails user = (UserDetails) session.get(UserDetails.class, 7); //select query run to fetch user details
		
		UserDetails user2 = (UserDetails) session.get(UserDetails.class, 7); //fetching the same primary key data so hibernate doesn't run another select query as object is present in the session cache
		
		session.getTransaction().commit();
		session.close(); //First Level Cache for session is also close now

		System.out.println(user);
		System.out.println(user2);
		

		Session session2 = sessionFactory.openSession();
		session2.getTransaction().begin();
		
		user = (UserDetails) session2.get(UserDetails.class, 7); //this will run a select query because required data is not present in the First Level Cache of session2
		//after making cacheable configurations, select query won't be fired
		
		session2.getTransaction().commit();
		session2.close();
	
		System.out.println(user);
	
		
		System.out.println("\n Using Query method. making Query Cacheable");
		Session session3 = sessionFactory.openSession();
		session3.getTransaction().begin();
		
		Query query = session3.createQuery("from UserDetailsEntity");
			
			query.setCacheable(true); //making this query cacheable
			
		query.list().forEach(System.out :: println);
		
		session3.getTransaction().commit();
		session3.close();
		
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		
		query = session4.createQuery("from UserDetailsEntity"); //Query level cache is different from second level cache. so we need to enable query level cache separately
		
			query.setCacheable(true); //making this query cacheable
		
		query.list().forEach(System.out :: println);
		
		session4.getTransaction().commit();
		session4.close();
		
		
		
		
	}

}

