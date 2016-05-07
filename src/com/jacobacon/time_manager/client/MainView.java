package com.jacobacon.time_manager.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.tractionsoftware.gwt.user.client.ui.UTCTimeBox;

public class MainView extends Composite {

	//private final HorizontalPanel mainPanel = new HorizontalPanel();
	private final FlowPanel mainPanel = new FlowPanel();
	private final Label label = new Label("Punch In / Punch Out");
	private final ListBox taskList = new ListBox();
	private final String[] taskListArray = { "Film", "Edit", "Agenda Work", "Clips", "Video Production", "Other" };
	
	private final UTCTimeBox timeIn = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
	private final UTCTimeBox timeOut = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
	
	//private final CheckBox enabled = new CheckBox("Include Break");
	
	
	private final UTCTimeBox mealStart = new UTCTimeBox();
	private final UTCTimeBox mealEnd = new UTCTimeBox();
	
	public final TextBox notes = new TextBox();
	public final Button button = new Button("Test");

	public MainView() {

		addTasks();
		taskList.setTitle("Job");
		notes.setTitle("Notes");

		//mainPanel.setSpacing(5);
		// mainPanel.getElement().getStyle().setBackgroundColor("green");
		

		mainPanel.getElement().getStyle().setProperty("float", "center");
		
		//label.getElement().getStyle().setProperty("float", "center");

		mainPanel.add(label);
		
		timeIn.setValue(UTCTimeBox.getValueForNextHour());
		timeIn.setTitle("Time In");
		timeOut.setValue(UTCTimeBox.getValueForNextHour());
		timeOut.setTitle("Time Out");
		
		mainPanel.add(timeIn);
		mainPanel.add(timeOut);
		mainPanel.add(taskList);

		notes.setValue("Enter Notes Here");

		mainPanel.add(notes);
		mainPanel.add(button);
		
		notes.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				notes.setValue("");
				
			}
		});
		

	}

	//Return the Main Panel. Used with the setContent method.
	public FlowPanel getMainPanel() {
		return mainPanel;
	}

	
	//Used to setup the Task List. I am lazy, and don't want to type it all.
	private void addTasks() {
		for (int i = 0; i < taskListArray.length; i++) {
			taskList.addItem(taskListArray[i]);
		}
	}

}
