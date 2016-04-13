package com.jacobacon.time_manager.client;

import com.jacobacon.time_manager.shared.FieldVerifier;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tractionsoftware.gwt.user.client.ui.UTCTimeBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Time_Manager implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		TabLayoutPanel p = new TabLayoutPanel(1.5, Unit.EM);
		
		
		p.add(new HTML("this"), "[this]");
		p.add(new HTML("that"), "[that]");
		p.add(new HTML("the other"), "[the other]");
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		final Button sendButton = new Button("Send");
		final Label errorLabel = new Label();



		// Initialize Arrays
		String names[] = { "Jacob", "Peter", "Megan", "Jeff" };

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element

		// RootPanel.get("sendButtonContainer").add(sendButton);
		// RootPanel.get("errorLabelContainer").add(errorLabel);
		// RootPanel.get("nameListContainer").add(nameList);
		// RootPanel.get("punchInTimeContainer").add(timeIn);
		// RootPanel.get("punchOutTimeContainer").add(timeOut);

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
		
		
		//lb.setName("listBoxFormElement");
		//lb.setTitle("Punch");
		//lb.addItem("foo", "fooValue");
		//lb.addItem("bar", "barValue");
		//panel.add(lb);
		
		panel.add(timeIn);
		panel.add(timeOut);
		panel.add(hiddenPunchIn);
		panel.add(hiddenPunchOut);

		panel.add(new Button("Submit", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				//Hacky way to send value of punch to server
				String hiddenIn = String.valueOf(timeIn.getValue());
				hiddenPunchIn.setValue(hiddenIn);
				String hiddenOut = String.valueOf(timeOut.getValue());
				hiddenPunchOut.setValue(hiddenOut);
				
				
				Window.alert("Your Manual Punch Has Been Submitted.");
				
				form.submit();

			}
		}));

		// Focus the cursor on the name field when the app loads
		nameList.setFocus(true);

		/*
		 * // Create the popup dialog box final DialogBox dialogBox = new
		 * DialogBox(); dialogBox.setText("");
		 * dialogBox.setAnimationEnabled(true); final Button closeButton = new
		 * Button("Close"); // We can set the id of a widget by accessing its
		 * Element closeButton.getElement().setId("closeButton"); final Label
		 * textToServerLabel = new Label(); final HTML serverResponseLabel = new
		 * HTML(); VerticalPanel dialogVPanel = new VerticalPanel();
		 * dialogVPanel.addStyleName("dialogVPanel"); dialogVPanel.add(new HTML(
		 * "<b>Sending name to the server:</b>"));
		 * dialogVPanel.add(textToServerLabel); dialogVPanel.add(new HTML(
		 * "<br><b>Server replies:</b>"));
		 * dialogVPanel.add(serverResponseLabel);
		 * dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		 * dialogVPanel.add(closeButton); dialogBox.setWidget(dialogVPanel);
		 * 
		 * // Add a handler to close the DialogBox
		 * closeButton.addClickHandler(new ClickHandler() { public void
		 * onClick(ClickEvent event) { dialogBox.hide();
		 * sendButton.setEnabled(true); sendButton.setFocus(true); } });
		 * 
		 * 
		 * 
		 * // Create a handler for the sendButton and nameField class MyHandler
		 * implements ClickHandler, KeyUpHandler { /** Fired when the user
		 * clicks on the sendButton.
		 * 
		 * public void onClick(ClickEvent event) { sendNameToServer(); }
		 * 
		 * /** Fired when the user types in the nameField.
		 * 
		 * public void onKeyUp(KeyUpEvent event) { if (event.getNativeKeyCode()
		 * == KeyCodes.KEY_ENTER) { sendNameToServer(); } }
		 * 
		 * /** Send the name from the nameField to the server and wait for a
		 * response.
		 * 
		 * private void sendNameToServer() { // First, we validate the input.
		 * errorLabel.setText(""); // String textToServer = nameField.getText();
		 * 
		 * String textToServer =
		 * createMessage(nameList.getValue(nameList.getSelectedIndex()),
		 * timeIn.getValue(), timeOut.getValue()); if
		 * (!FieldVerifier.isValidName(textToServer)) { errorLabel.setText(
		 * "Please enter at least four characters"); return; }
		 * 
		 * // Then, we send the input to the server.
		 * sendButton.setEnabled(false);
		 * textToServerLabel.setText(nameList.getValue(nameList.getSelectedIndex
		 * ())); serverResponseLabel.setText("");
		 * greetingService.greetServer(textToServer, new AsyncCallback<String>()
		 * { public void onFailure(Throwable caught) { // Show the RPC error
		 * message to the user dialogBox.setText(
		 * "Remote Procedure Call - Failure");
		 * serverResponseLabel.addStyleName("serverResponseLabelError");
		 * serverResponseLabel.setHTML(SERVER_ERROR); dialogBox.center();
		 * closeButton.setFocus(true); }
		 * 
		 * public void onSuccess(String result) { dialogBox.setText(
		 * "Remote Procedure Call");
		 * serverResponseLabel.removeStyleName("serverResponseLabelError");
		 * serverResponseLabel.setHTML(result); dialogBox.center();
		 * closeButton.setFocus(true); } }); } }
		 * 
		 * // Add a handler to send the name to the server MyHandler handler =
		 * new MyHandler(); sendButton.addClickHandler(handler);
		 */
		p.add(panel, "Manual Punch");
		
		RootLayoutPanel rp = RootLayoutPanel.get();
		rp.add(p);
		RootPanel.get().add(form);

	}

}
