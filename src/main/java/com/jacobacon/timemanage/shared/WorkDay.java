package com.jacobacon.timemanage.shared;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class WorkDay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2201052316367433008L;

	@Id
	private String name;

	private String username;

	private String loggedIp;

	private Date timeIn;

	private Date timeOut;

	private Date day;

	public WorkDay() {

		this("default", "default", "000.000.000.000", new Date(), new Date());

	}

	public WorkDay(String name, String username, String ip, Date in, Date out) {
		this.name = name;
		this.username = username;
		this.loggedIp = ip;
		this.timeIn = in;
		this.timeOut = out;
		day = new Date();
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getIp() {
		return loggedIp;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public Long getTimeInLong() {
		return timeIn.getTime();
	}

	public Long getTimeOutLong() {
		return timeOut.getTime();
	}

	public Date getDay() {
		return day;
	}

	public Long getDayLong() {
		return day.getTime();
	}

}
