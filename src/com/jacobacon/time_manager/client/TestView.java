package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;



public class TestView extends Composite{
	
	/** Main panel of the login view */
	private VerticalPanel mainpanel = new VerticalPanel();

	/** The logo image */
	private Image logo = new Image("resources/images/tigertech-logo.jpg");

	/** The headline below the logo */
	private HTML secondoHeadline = new HTML("<h1>Example GWT Application to navigate between pages</h1>");

	/** Decorator panel for the login form */
	private DecoratorPanel decPanel = new DecoratorPanel();

	/** Grid for login form elements */
	private FlexTable loginLayout = new FlexTable();

	// elements in login grid
	private String headline = "Log into the application";
	private String usernameLabel = "Username: ";
	private String passwordLabel = "Password: ";
	private String ipLabel = "Server-URL: ";
	private String portLabel = "Port: ";
	private TextBox username = new TextBox();
	private PasswordTextBox password = new PasswordTextBox();
	private TextBox ipadresse = new TextBox();
	private TextBox port = new TextBox();
	private Button loginbutton = new Button("Login");

	public TestView() {

		int windowHeight = Window.getClientHeight();
		int windowWidth = Window.getClientWidth();

		loginLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = loginLayout.getFlexCellFormatter();

		// Add a title to the form
		loginLayout.setHTML(0, 0, this.headline);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Add username and password fields
		username.setWidth("150px");
		password.setWidth("150px");
		loginLayout.setHTML(1, 0, this.usernameLabel);
		loginLayout.setWidget(1, 1, username);
		loginLayout.setHTML(2, 0, passwordLabel);
		loginLayout.setWidget(2, 1, password);

		// Add the loginbutton to the form
		loginLayout.setWidget(3, 0, loginbutton);
		cellFormatter.setColSpan(3, 0, 2);
		cellFormatter.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// Create some advanced options
		Grid advancedOptions = new Grid(2, 2);
		advancedOptions.setCellSpacing(6);
		advancedOptions.setHTML(0, 0, ipLabel);
		advancedOptions.setWidget(0, 1, ipadresse);
		advancedOptions.setHTML(1, 0, portLabel);
		advancedOptions.setWidget(1, 1, port);

		// Add advanced options to form in a disclosure panel
		DisclosurePanel advancedDisclosure = new DisclosurePanel("Advanced Settings");
		advancedDisclosure.setAnimationEnabled(true);
		advancedDisclosure.ensureDebugId("DisclosurePanel");
		advancedDisclosure.setContent(advancedOptions);

		loginLayout.setWidget(4, 0, advancedDisclosure);
		cellFormatter.setColSpan(4, 0, 2);

		// Wrap the content in a DecoratorPanel
		decPanel.setWidget(loginLayout);

		//mainpanel.setWidth(windowWidth / 2 + "px");
		//mainpanel.setHeight(windowHeight * 0.6 + "px");
		mainpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		mainpanel.add(logo);
		mainpanel.add(secondoHeadline);
		mainpanel.add(decPanel);
	}

	/**
	 * Returns the textbox for the username
	 * 
	 * @return The textbox for the username
	 */
	public TextBox getUsername() {
		return username;
	}

	/**
	 * Returns the password textbox for the password
	 * 
	 * @return The password textbox for the password
	 */
	public PasswordTextBox getPassword() {
		return password;
	}

	/**
	 * Returns the login button
	 * 
	 * @return The login button
	 */
	public Button getLoginbutton() {
		return loginbutton;
	}


	public TextBox getIpadresse() {
		return ipadresse;
	}


	public TextBox getPort() {
		return port;
	}


	public VerticalPanel getMainPanel() {
		return mainpanel;
	}
	
	

}
