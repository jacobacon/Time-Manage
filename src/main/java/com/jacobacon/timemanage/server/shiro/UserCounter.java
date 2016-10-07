package com.jacobacon.timemanage.server.shiro;

import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Cache
@Entity
public class UserCounter {

	public static final String COUNTER_ID = "counter";

	@Id
	private String id;

	private int count;

	private Date lastModified;

	private UserCounter() {
		this(COUNTER_ID);
	}

	public UserCounter(String id) {
		this.id = id;
		lastModified = new Date(0L);

	}

	public int getCount() {
		return count;
	}

	public void changeCount(long change) {
		this.count += change;
		this.lastModified = new Date();
	}

	public Date getLastModified() {
		return lastModified;
	}

}
