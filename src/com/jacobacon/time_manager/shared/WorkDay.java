package com.jacobacon.time_manager.shared;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class WorkDay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	public String id;
	Date startTime;
	Date endTime;
	Date mealStart;
	Date mealEnd;
	String taskCompleted;
	public String notes;

	// Default Constructor
	public WorkDay() {
		this.id = "Default";
		startTime = new Date();
		endTime = new Date();
		mealStart = new Date();
		mealEnd = new Date();
		taskCompleted = "Default";
		notes = "Default";
	}

	// Constructor with Meal
	public WorkDay(String id, Date startTime, Date endTime, Date mealStart, Date mealEnd, String taskCompleted, String notes) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.mealStart = mealStart;
		this.mealEnd = mealEnd;
		this.taskCompleted = taskCompleted;
		this.notes = notes;
	}

	// Constructor with No Meal
	public WorkDay(String id, Date startTime, Date endTime, String taskCompleted, String notes) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.taskCompleted = taskCompleted;
		this.notes = notes;
	}
	
	public Date getStartTime(){
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getMealStart() {
		return mealStart;
	}

	public void setMealStart(Date mealStart) {
		this.mealStart = mealStart;
	}

	public Date getMealEnd() {
		return mealEnd;
	}

	public void setMealEnd(Date mealEnd) {
		this.mealEnd = mealEnd;
	}

	public String getTaskCompleted() {
		return taskCompleted;
	}

	public void setTaskCompleted(String taskCompleted) {
		this.taskCompleted = taskCompleted;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	

}
