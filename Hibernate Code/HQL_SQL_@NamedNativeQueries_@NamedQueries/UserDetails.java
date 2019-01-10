package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name="UserDetailsEntity")
@Table(name = "User_Details_HQL")
@NamedQueries({  
	//named queries let us to group all the queries related to a single entity at a single place. named queries allows us to write queries at entity level
	//using NamedQueries we can list out all the common queries that one can used for this UserDetailsEntity which then can be used anywhere in the application
	//@NamedQueries & @NamedQuery annotation is for writing HQL
	
	@NamedQuery(name = "findUserById", query = "from UserDetailsEntity where userId = :id") //HQL
	
})
@NamedNativeQueries({
		//@NamedNativeQueries @NamedNativeQuery annotations is for writing SQL
		//with @NamedNativeQuery we have to use resultClass property as in native sql query hibernate don't know as what type of results this sql query will gonna generate so that hibernate will cast the resultset to that class type object
		//in case of @NamedQuery it is using HQL so no need to mentioned the class name as Entity name is binded with class name
		//we can run stored procedures also using this annotation
	
	@NamedNativeQuery(name = "findUserByName", query = "select * from User_Details_HQL where User_Name = :name", resultClass=UserDetails.class) //SQL
})
public class UserDetails {

	@Id
	@GeneratedValue
	@Column(name = "User_Id")
	private int userId;

	
	@Column(name = "User_Name")
	private String userName;
	
	
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + "]";
	}




}
