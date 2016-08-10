package com.jacobacon.time_manager.shared;

import java.io.Serializable;

public class User implements Serializable{
	
	private String name;
	private boolean loggedIn;
	
	public User(){
		this.name = "Jacob";
		this.loggedIn = false;
		
	}
	

}
