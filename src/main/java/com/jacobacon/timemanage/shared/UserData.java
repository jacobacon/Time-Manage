package com.jacobacon.timemanage.shared;

import java.io.Serializable;

public class UserData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6119078866642173602L;

	private String name;

	private String username;
	
	private String ipAddress;

	public UserData() {
		this.name = "default";
		this.username = "default";
		this.ipAddress = "000.000.000.000";
	}

	// This object is used to pass bulk data to the client from the server to
	// save RPC calls.
	public UserData(String name, String username, String ipAddress) {

		this.name = name;
		this.username = username;
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}
	
	public String getIpAddress(){
		return ipAddress;
	}

}
