package com.gzb.UserDemo.vo;

import java.io.Serializable;

public class AddUserReqVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String userName;
	
	String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AddUserReqVo [userName=" + userName + ", password=" + password + "]";
	}
	
	

}
