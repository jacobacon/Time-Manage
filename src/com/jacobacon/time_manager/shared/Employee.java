package com.jacobacon.time_manager.shared;

import java.util.Date;

public class Employee {

	private String name;
	private String id;
	private String passHash;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private Date lastWorkedDay;
	private boolean isAdmin;

	public Employee() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastWorkedDay() {
		return lastWorkedDay;
	}

	public void setLastWorkedDay(Date lastWorkedDay) {
		this.lastWorkedDay = lastWorkedDay;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
