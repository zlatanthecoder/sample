package com.hibernate.model.jb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="UserDetails")
@Table(name = "User_Details_CRUD")
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
