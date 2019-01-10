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
