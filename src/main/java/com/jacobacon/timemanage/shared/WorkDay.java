package com.jacobacon.timemanage.shared;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class WorkDay implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2201052316367433008L;

	@Index
	private String name;

	private String username;

	private String loggedIp;

	@Index
	private Date timeIn;

	@Index
	private Date timeOut;

	private Date day;
	
	private String job;
	
	@Id
	Long id;

	public WorkDay() {

		this.name = "Default";
		this.username = "Default";
		this.loggedIp = "123.123.123.123";
		this.timeIn = new Date();
		this.timeOut = new Date();
		this.day = new Date();
		this.job = "Work";

	}
	
	public WorkDay(String name, String username, String ip, Date in, Date out) {
		this.name = name;
		this.username = username;
		this.loggedIp = ip;
		this.timeIn = in;
		this.timeOut = out;
		day = new Date();
		this.job = "Work";
	}

	public WorkDay(String name, String username, String ip, Date in, Date out, String job) {
		this.name = name;
		this.username = username;
		this.loggedIp = ip;
		this.timeIn = in;
		this.timeOut = out;
		day = new Date();
		this.job = job;
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
	
	public String getJob() {
		return job;
	}

	public String getLoggedIp() {
		return loggedIp;
	}

	public void setLoggedIp(String loggedIp) {
		this.loggedIp = loggedIp;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
