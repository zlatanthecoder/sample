package com.hibernate.main.jb;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.hibernate.model.jb.UserDetails;

public class CriteriaAPIMain {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
					

		//sessionfactory will be created using hibernate.cfg.xml
		Configuration configuration = new Configuration().configure().addAnnotatedClass(UserDetails.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry); //SessionFactory will be created only once per application
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();		
			String uName = "HQLUser5"; //using table name=User_Details_HQL
		
		System.out.println("------Criteria API------");	
		System.out.println("\n Restrictions.eq(userName, uName)");
			Criteria criteria = session.createCriteria(UserDetails.class);	//here we are creating a criteria for UserDetails.class . criteria is like a where clause means we specified conditions here that need to be fulfilled
			criteria.add(Restrictions.eq("userName", uName)); //creating a Restriction here where property userName equals uName variable value
			criteria.list().forEach(System.out :: println);
			
			
		System.out.println("\n Restrictions.gt(\"userId\", 5) means userId>5");	
		criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.gt("userId", 5)); //this means we are pulling data in which userId>5
		criteria.list().forEach(System.out :: println);
		
		
		System.out.println("\n Restrictions.between(\"userId\", 5, 10) means userId between 5 & 10");	
		criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.between("userId", 5, 10)); //between means pull data from 5 to 10. 5 is included here
		criteria.list().forEach(System.out :: println);
		
		
		//by default chaining of add() method work as a and operator
		System.out.println("\n implementing OR condition in Restrictions");
		criteria = session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.or(
				Restrictions.between("userId", 2, 4),
				Restrictions.between("userId", 8, 10)) );
		criteria.list().forEach(System.out :: println);

		
		
		System.out.println("\n implementing LIKE condition in Restrictions:: Restrictions.like(\"userName\", \"HQLUser1%\")");
		criteria = session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.like("userName", "HQLUser1%")); //userName is property name. HQLUser1 & HQLUser10 will be returned
		criteria.list().forEach(System.out :: println);
		
		
		System.out.println("\n-----------PROJECTIONS-----------");
		System.out.println(" Projections.min(\"userId\")");
		criteria = session.createCriteria(UserDetails.class);
		criteria.setProjection(Projections.min("userId")); //"userId" is the property of the entity
		criteria.list().forEach(System.out :: println);
		
		
		System.out.println(" Projections.count(\"userId\")");
		session.createCriteria(UserDetails.class)
			   .setProjection(Projections.count("userId")) //"userId" is the property of the entity
			   .list().forEach(System.out :: println);
		
		
		
		System.out.println(" addOrder(Order.desc(\"userId\"))");
		session.createCriteria(UserDetails.class)
			   .addOrder(Order.desc("userId"))
			   .list().forEach(System.out :: println);
		
		
		System.out.println("\n-----QueryByExample-----");
		//creating a UserDetails object & setting the values which Example criteria will gonna match
		UserDetails exampleUser = new UserDetails();
		//exampleUser.setUserId(5); not required as this is the primary key so hibernate will not consider this when pulling out the data
		exampleUser.setUserName("HQLUser5");
		
		//hibernate ignores two things when it comes to taking an Example. 1) any property which has a value of null, its not gonna consider that 2) if a property happens to be a primary key then also it ignores that
		//so apart from Null properties & primary key properties, if we have set any values in any other properties then that is what hibernate will consider when pulling up the data
			Example example = Example.create(exampleUser); //creating Example out of this object & pass it onto the criteria which pulls up all the objects
		//example.excludeProperty("userName") we can exclude any no of property if we want hibernate not to consider that property
		session.createCriteria(UserDetails.class)
				.add(example)
				.list().forEach(System.out :: println);
		
		
		
		System.out.println(" QueryByExample with like operation :: Example.create(exampleUser).enableLike()");
		exampleUser.setUserName("HQLUser1%");
			example = Example.create(exampleUser).enableLike();
		
		session.createCriteria(UserDetails.class)
		.add(example)
		.list().forEach(System.out :: println);
		
		
		session.getTransaction().commit();
		session.close(); 


		
	}

}
