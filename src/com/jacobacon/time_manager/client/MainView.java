package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class MainView extends Composite {

	private final HorizontalPanel mainPanel = new HorizontalPanel();
	private final Label label = new Label("Punch In / Punch Out");
	private final ListBox taskList = new ListBox();
	private final String[] taskListArray = { "Film", "Edit", "Agenda Work", "Clips", "Video Production", "Other" };
	

	public final TextBox notes = new TextBox();
	public final Button button = new Button();

	public MainView() {

		addTasks();
		taskList.setTitle("Job");
		notes.setTitle("Notes");

		mainPanel.setSpacing(5);
		// mainPanel.getElement().getStyle().setBackgroundColor("green");
		mainPanel.setSize("300px", "300px");

		mainPanel.getElement().setAttribute("align", "center");

		mainPanel.add(label);
		mainPanel.add(taskList);

		notes.setValue("Enter Notes Here");
		button.setText("Test");

		mainPanel.add(notes);
		mainPanel.add(button);

	}

	public HorizontalPanel getMainPanel() {
		return mainPanel;
	}

	private void addTasks() {
		for (int i = 0; i < taskListArray.length; i++) {
			taskList.addItem(taskListArray[i]);
		}
	}

}
