package com.jacobacon.time_manager.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.jacobacon.time_manager.client.mainview.MainView;
import com.tractionsoftware.gwt.user.client.ui.UTCTimeBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Time_Manager implements EntryPoint {
	/**
	 * This is the entry point method.
	 * 
	 *
	 */
	
	int number = 0;
	public void onModuleLoad() {

		
		MainView mainView = new MainView();
		
		
		
		
		
		
		
		
		new Label();

		// Initialize Arrays
		String names[] = { "Jacob", "Peter", "Megan", "Jeff" };


		// Create Form Panel, and set its attributes.
		final FormPanel form = new FormPanel();
		form.setAction("/login");
		form.setMethod(FormPanel.METHOD_POST);

		// Create a Horizontal Panel to hold the elements.
		HorizontalPanel panel = new HorizontalPanel();
		panel.getElement().setId("test");
		panel.getElement().setAttribute("align", "center");
		form.setWidget(panel);

		// Create name list.
		final ListBox nameList = new ListBox();
		nameList.setName("nameListElement");
		panel.add(nameList);

		// Create List of Names
		for (int i = 0; i < names.length; i++) {
			nameList.addItem(names[i], names[i]);
		}

		// Create Punch in and Punch out.
		final UTCTimeBox timeIn = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
		final UTCTimeBox timeOut = new UTCTimeBox(DateTimeFormat.getFormat("hh:mm a"));
		final Hidden hiddenPunchIn = new Hidden("hiddenPunchIn");
		final Hidden hiddenPunchOut = new Hidden("hiddenPunchOut");

		timeIn.setValue(UTCTimeBox.getValueForNextHour());
		timeIn.setTitle("Punch In");

		timeOut.setValue(UTCTimeBox.getValueForNextHour());
		timeOut.setTitle("Punch Out");

		panel.add(timeIn);
		panel.add(timeOut);
		panel.add(hiddenPunchIn);
		panel.add(hiddenPunchOut);

		panel.add(new Button("Submit", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// Hacky way to send value of punch to server
				String hiddenIn = String.valueOf(timeIn.getValue());
				hiddenPunchIn.setValue(hiddenIn);
				String hiddenOut = String.valueOf(timeOut.getValue());
				hiddenPunchOut.setValue(hiddenOut);

				Window.alert("Your Manual Punch Has Been Submitted." + hiddenPunchIn.getValue());

				form.submit();

			}
		}));

		// Focus the cursor on the name field when the app loads
		nameList.setFocus(true);

		
		
		RootPanel.get().add(form);
		
		
		Button b = new Button("Click Me!", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Changin page!");
				createApp();
			}
		});
		
		RootPanel.get().add(b);
		
		RootPanel.get().add(mainView.getMainPanel());
				
		
		
		
	}
	
	public void createApp(){
		
		Label testLabel = new Label();
		testLabel.setText("Hello!" + number);
		RootPanel.get("content").clear();
		RootPanel.get("content").add(testLabel);
		number++;
	}

}
