package com.jacobacon.time_manager.client;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.tractionsoftware.gwt.user.client.ui.UTCTimeBox;

public class MainView extends Composite {

	// private final HorizontalPanel mainPanel = new HorizontalPanel();
	private final VerticalPanel mainPanel = new VerticalPanel();
	private final FlexTable appLayout = new FlexTable();
	private final Label label = new Label("Punch In / Punch Out");
	public final ListBox taskList = new ListBox();
	private final String[] taskListArray = { "Film", "Edit", "Agenda Work", "Clips", "Video Production", "Other" };

	public final UTCTimeBox timeIn = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
	public final UTCTimeBox timeOut = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));

	private final CheckBox enabled = new CheckBox("Break Included");

	private final UTCTimeBox mealStart = new UTCTimeBox();
	private final UTCTimeBox mealEnd = new UTCTimeBox();

	public final TextBox notes = new TextBox();

	public final Button submit = new Button("Submit");

	public MainView() {

		addTasks();
		
		
		
		appLayout.setWidth("100%");
		
		
		taskList.setTitle("Job");
		notes.setTitle("Notes");

		appLayout.setCellSpacing(6);
		
		
		
		
		

		

		appLayout.setWidget(0, 1, label);

		timeIn.setValue(UTCTimeBox.getValueForNextHour());
		timeIn.setTitle("Time In");
		timeOut.setValue(UTCTimeBox.getValueForNextHour());
		timeOut.setTitle("Time Out");

		appLayout.setWidget(0, 2, timeIn);
		appLayout.setWidget(0, 3, timeOut);
		appLayout.setWidget(0, 4, taskList);

		
		notes.setValue("Enter Notes Here");

		appLayout.setWidget(1, 0, enabled);
		enabled.getElement().getStyle().setPaddingRight(5, Unit.PX);
		
		
		
		
		

		//mainPanel.add(notes);
		
		

		notes.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				notes.setValue("");

			}
		});
		

		
		
		Grid advancedOptions = new Grid(2, 2);
		advancedOptions.setCellSpacing(6);
		advancedOptions.setHTML(0, 0, "Hello 1");
		advancedOptions.setWidget(0, 1, new TextBox());
		advancedOptions.setHTML(1, 0, "Hello 2");
		advancedOptions.setWidget(1, 1, new TextBox());
		
		DisclosurePanel advancedDisclosure = new DisclosurePanel("Advanced Settings");
		advancedDisclosure.setAnimationEnabled(true);
		advancedDisclosure.ensureDebugId("DisclosurePanel");
		advancedDisclosure.setContent(advancedOptions);
		
		
		
		appLayout.setWidget(1, 5, advancedDisclosure);
		

		appLayout.setWidget(0, 5, submit);
		
		
		
		mainPanel.setWidth(Window.getClientWidth() / 2 + "px");
		mainPanel.setBorderWidth(2);
		mainPanel.add(appLayout);

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
