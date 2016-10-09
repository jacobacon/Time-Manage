package com.jacobacon.timemanage.server;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class WorkDay {

	@Id
	private String user;

	private Date timeIn;

	private Date timeOut;

	public WorkDay() {

	}

}
