package com.jacobacon.time_manager.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tractionsoftware.gwt.user.client.ui.UTCTimeBox;

public class MainView extends Composite {

	// private final HorizontalPanel mainPanel = new HorizontalPanel();
	private final FlowPanel layoutPanel = new FlowPanel();
	private final Label label = new Label("Punch In / Punch Out");
	public final ListBox taskList = new ListBox();
	private final String[] taskListArray = { "Film", "Edit", "Agenda Work", "Clips", "Video Production", "Other" };

	public final UTCTimeBox timeIn = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
	public final UTCTimeBox timeOut = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));

	private final CheckBox breakCheck = new CheckBox();
	private final CheckBox notesCheck = new CheckBox();

	public final UTCTimeBox mealStart = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
	public final UTCTimeBox mealEnd = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));

	public final TextBox notes = new TextBox();

	public final Button submit = new Button("Submit");

	public final Grid grid = new Grid(5, 5);

	VerticalPanel mainPanel = new VerticalPanel();

	private DisclosurePanel advanced = new DisclosurePanel("Advanced Options");

	public MainView() {

		addTasks();
		taskList.setTitle("Job");
		notes.setTitle("Notes");

		// mainPanel.setSpacing(5);
		// mainPanel.getElement().getStyle().setBackgroundColor("green");

		layoutPanel.getElement().getStyle().setProperty("float", "center");

		// label.getElement().getStyle().setProperty("float", "center");

		layoutPanel.add(label);

		timeIn.setValue(UTCTimeBox.getValueForNextHour());
		timeIn.setTitle("Time In");
		timeOut.setValue(UTCTimeBox.getValueForNextHour());
		timeOut.setTitle("Time Out");

		mealStart.setValue(UTCTimeBox.getValueForNextHour());
		mealStart.setTitle("Break Start");
		mealEnd.setValue(UTCTimeBox.getValueForNextHour());
		mealEnd.setTitle("Break End");

		layoutPanel.add(timeIn);
		layoutPanel.add(timeOut);
		layoutPanel.add(taskList);

		layoutPanel.add(new HTML("</br>"));
		notes.setValue("Enter Notes Here");

		layoutPanel.add(notes);
		layoutPanel.add(mealStart);
		layoutPanel.add(mealEnd);
		notes.setVisible(false);
		mealStart.setVisible(false);
		mealEnd.setVisible(false);

		notes.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				notes.setValue("");

			}
		});

		layoutPanel.add(submit);

		// Grid of widgets used in the Disclosure Panel for Advanced Punch
		// items. (I.E. Lunch, Notes, Etc.)
		Grid advancedGrid = new Grid(2, 2);

		advancedGrid.setWidget(0, 0, new Label("Add Notes"));
		advancedGrid.setWidget(0, 1, notesCheck);
		advancedGrid.setWidget(1, 0, new Label("Add Break / Meal"));
		advancedGrid.setWidget(1, 1, breakCheck);
		advancedGrid.setBorderWidth(1);

		notesCheck.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				notes.setVisible(event.getValue());

			}
		});

		breakCheck.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				mealStart.setVisible(event.getValue());
				mealEnd.setVisible(event.getValue());
			}
		});

		advanced.add(advancedGrid);
		advanced.setAnimationEnabled(true);

		layoutPanel.add(advanced);

		// Put some values in the grid cells.
		for (int row = 0; row < 5; ++row) {
			for (int col = 0; col < 5; ++col)
				grid.setText(row, col, "" + row + ", " + col);
		}

		// Just for good measure, let's put a button in the center.
		grid.setWidget(2, 2, new Button("Does nothing, but could"));

		// You can use the CellFormatter to affect the layout of the grid's
		// cells.
		grid.getCellFormatter().setWidth(0, 2, "256px");
		grid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		layoutPanel.add(grid);

		grid.setVisible(false);

		mainPanel.setWidth("100%");
		mainPanel.setHeight("100%");
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		mainPanel.add(layoutPanel);

	}

	// Return the Main Panel. Used with the setContent method.
	public VerticalPanel getMainPanel() {
		return mainPanel;
	}

	// Used to setup the Task List. I am lazy, and don't want to type it all.
	private void addTasks() {
		for (int i = 0; i < taskListArray.length; i++) {
			taskList.addItem(taskListArray[i]);
		}
	}

}
