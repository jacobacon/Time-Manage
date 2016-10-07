package com.jacobacon.timemanage.shared;

import java.io.Serializable;

public class UserData implements Serializable{

	private String name;

	private String username;

	public UserData() {
		this.name = "default";
		this.username = "default";
	}

	// This object is used to pass bulk data to the client from the server to
	// save RPC calls.
	public UserData(String name, String username) {

		this.name = name;
		this.username = username;

	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

}
