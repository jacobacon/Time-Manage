package com.jacobacon.time_manager.shared;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Punch {
	@Id
	public Long id;

	public String name;
	public String notes;
	@Index
	public Date punchTime;

	public Punch(String name, String notes) {
		this.name = name;
		this.notes = notes;
		this.punchTime = new Date();

	}

}
