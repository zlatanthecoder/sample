package com.hibernate.main.jb;

import java.util.List;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.hibernate.model.jb.UserDetails;

public class HQLMain {
	
	public static void main(String[] args) {
					

		//sessionfactory will be created using hibernate.cfg.xml
		Configuration configuration = new Configuration().configure().addAnnotatedClass(UserDetails.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry); //SessionFactory will be created only once per application
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();		
					
		/*for(int i=1;i<=10;i++) {
				UserDetails user = new UserDetails(); 
				user.setUserName("HQLUser"+i);
				session.save(user);
			}*/

			
			Query query = session.createQuery("from UserDetailsEntity"); //createQuery() method will return the query object. UserDetailsEntity is Entity Name
			List<UserDetails> users = query.list(); //instead of ResultSet, hibernate will return list of UserDetails objects
			users.forEach(System.out :: println);
	
			
			System.out.println();
			System.out.println("------HQL from UserDetailsEntity where userId>5------");
			query = session.createQuery("from UserDetailsEntity where userId>5"); //userId is the property name not the column name
			query.list().forEach(System.out :: println);
			
			
						
			System.out.println("\n--------PAGINATION in Hibernate-------");
			
			System.out.println("\n query.setFirstResult(5) go 5 records below & start from there. configuring Starting Point");
			query = session.createQuery("from UserDetailsEntity");									
				//using this method we can configure Starting Point
				query.setFirstResult(5); //it means from all the result that query got from above statement, it will go 5 result below & that would be the First Result means we will get result from 6th position
			query.list().forEach(System.out :: println);	
			
			
			System.out.println("\n query.setMaxResults(4) fetch max of 4 records. configuring Ending Point");
			query = session.createQuery("from UserDetailsEntity");			
				//using this method we can configure Ending Point
				query.setMaxResults(4); //it tells hibernate to fetch the maximum no of records that needed to be pull up from query.list(). here it is pulling maximum 4 results.
			query.list().forEach(System.out :: println);
				
			
			System.out.println("\n select userName from UserDetailsEntity");
			query = session.createQuery("select userName from UserDetailsEntity");
			query.list().forEach(System.out :: println); //here we are getting List<String> objects
			
			
			
			System.out.println("\n select new Map(userId,userName) from UserDetailsEntity");
			query = session.createQuery("select new Map(userId,userName) from UserDetailsEntity");
			query.list().forEach(System.out::println); //here we are getting List<Map> objects
			
			
			System.out.println("\n select max(userId) from UserDetailsEntity");
			query = session.createQuery("select max(userId) from UserDetailsEntity");
			query.list().forEach(System.out::println);
			
			
			
			System.out.println("\n--------Passing Dynamic Values in HQL= Positional Parameters & Named Parameters-------");
			int uID=7;
			String uName="HQLUser9";
			/*
  			this approach is avoided as this is SQL injection which is not recommended as hacker can bypass the where clause by passing its own values to get the results like hacker can pass 0 after > i.e. userId>0 will give all the results
			query = session.createQuery("from UserDetailsEntity where userId>"+uID);
			query.list().forEach(System.out::println);*/
			
			System.out.println("\n using Positional parameter ? :: from UserDetailsEntity where userId > ?");
				query = session.createQuery("from UserDetailsEntity where userId > ?"); //? is placeholder
				query.setInteger(0, uID); //1st argument is the position of placeholder like 0 means substitute 1st placeholder & 2nd argument is the value to be substituted			
			query.list().forEach(System.out::println);

			
			System.out.println("\n using Positional parameter ? :: from UserDetailsEntity where userId > ? and userName = ?");
				query = session.createQuery("from UserDetailsEntity where userId > ? and userName = ?"); //? is placeholder
				query.setInteger(0, uID); //1st argument is the position of placeholder like 0 means substitute 1st placeholder & 2nd argument is the value to be substituted			
				query.setString(1, uName); //1 means substitute 2nd placeholder & uName is the value of the placeholder
			query.list().forEach(System.out::println);
			
			
			System.out.println("\n using NAMED PARAMETER :name :: from UserDetailsEntity where userId > :id");
				query = session.createQuery("from UserDetailsEntity where userId > :id"); //:userID is named parameter. after : give the name to the parameter
				query.setInteger("id", uID); //1st argument is the String which is the name of the named parameter which you want to substituted with the value uID			
			query.list().forEach(System.out::println);

			
			System.out.println("\n using NAMED PARAMETER :name :: from UserDetailsEntity where userId > :id and userName = :name");
				query = session.createQuery("from UserDetailsEntity where userId > :id and userName = :name"); //:id & :name are named parameters
				query.setInteger("id", uID); //1st argument is the String which is the name of the named parameter which you want to substituted with the value uID			
				query.setString("name", uName); //1st argument is the String which is the name of the named parameter which you want to substituted with the value uName
			query.list().forEach(System.out::println);
			
			
			
			
			System.out.println("\n--------Named Queries-------");
			System.out.println(" Named Queries for HQL :: @NamedQueries & @NamedQuery annotation");
			
				query = session.getNamedQuery("findUserById"); //the String argument is the name of the @NamedQuery
			query.setInteger("id", uID); //setting the Named Parameter
			query.list().forEach(System.out::println);
			
			
			System.out.println("\n Named Queries for SQL :: @NamedNativeQueries & @NamedNativeQuery annotation");
				query = session.getNamedQuery("findUserByName"); //the String argument is the name of the @NamedNativeQuery
			query.setString("name", uName); //setting the Named Parameter
			query.list().forEach(System.out::println);
			
			
			
		session.getTransaction().commit();
		session.close(); 


		
	}

}
