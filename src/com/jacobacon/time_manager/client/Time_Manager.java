package com.jacobacon.time_manager.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jacobacon.time_manager.shared.WorkDay;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Time_Manager implements EntryPoint {

	/**
	 * This is the entry point method.
	 */

	final MainView mainView = new MainView();
	final ManualPunchView manualPunchView = new ManualPunchView();
	final ReportView reportView = new ReportView();
	
	final TestView testView = new TestView();

	// Setup Login
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please Sign in to your Google Account to Access this Application");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	final String url = "punch";

	private WorkDay work;

	private final Grid grid = new Grid(5, 5);

	private PunchServiceAsync punchService;

	private static List<WorkDay> WORKDAYS = Arrays.asList(new WorkDay(), new WorkDay(), new WorkDay());

	private static List<WorkDay> days;

	public void onModuleLoad() {

		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					setUp();
					showApp();
				} else {
					loadLogin();
				}

			}

		});

	}

	// Shows the login page if the user needs to login.
	public void loadLogin() {
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(loginPanel);
	}

	// Method that shows the actual app after login.
	public void showApp() {

		// Create RequestBuilder for POST

		punchService = GWT.create(PunchService.class);
		
		

		final HorizontalPanel headerPanel = new HorizontalPanel();
		headerPanel.setWidth("100%");
		headerPanel.setSpacing(5);
		final PushButton mainViewButton = new PushButton("Home", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(0);

			}

		});
		final PushButton manualPunchViewButton = new PushButton("Edit Time", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(1);

			}
		});
		final PushButton reportsViewButton = new PushButton("Reporting", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(2);

			}
		});
		mainViewButton.setSize("150px", "50px");
		manualPunchViewButton.setSize("150px", "50px");
		reportsViewButton.setSize("150px", "50px");

		Image clockImage = new Image("images/clock-small.png");
		clockImage.setPixelSize(74, 96);
		headerPanel.add(clockImage);
		headerPanel.add(mainViewButton);
		headerPanel.add(manualPunchViewButton);
		headerPanel.add(reportsViewButton);
		headerPanel.add(new Label(loginInfo.getNickname()));
		signOutLink.setHref(loginInfo.getLogoutUrl());
		headerPanel.add(signOutLink);
		headerPanel.setBorderWidth(5);

		Button b = new Button("Click Me to Get Work", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				punchService.getWork("jacob", new AsyncCallback<WorkDay>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(WorkDay result) {
						Window.alert("You completed: " + result.getTaskCompleted() + "\nYour Notes are: "
								+ result.getNotes() + "\nYou clocked in at: " + result.getStartTime()
								+ "\nAnd clocked out at: " + result.getEndTime());

					}

				});

			}
		});

		Button b4 = new Button("Test", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setContent(3);

			}

		});

		RootPanel.get("header").add(headerPanel);

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

		grid.setVisible(false);

		RootPanel.get("content").add(grid);

		setContent(0);

		// Temporary Buttons for Testing
		RootPanel.get("content").add(b);
		RootPanel.get("content").add(b4);

	}

	// Changes the content of the page to the different views.
	public void setContent(int viewNumber) {
		switch (viewNumber) {
		case 0:
			System.out.println("Switching to the main view!");
			RootPanel.get("content").clear();
			RootPanel.get("content").add(mainView.getMainPanel());

			Window.setTitle("Home");
			break;

		case 1:
			System.out.println("Switching to the 2nd view!");
			RootPanel.get("content").clear();
			RootPanel.get("content").add(manualPunchView.getMainPanel());
			Window.setTitle("Edit Time");
			break;

		case 2:
			System.out.println("Switching to the 3rd view");
			RootPanel.get("content").clear();
			RootPanel.get("content").add(reportView.getMainPanel());

			reportView.table.setRowCount(15);

			// reportView.table.setRowData(0, days);

			reportView.table.flush();

			Window.setTitle("Reports");
			break;
		case 3:
			RootPanel.get("content").clear();
			RootPanel.get("content").add(testView.getMainPanel());
			
			Window.setTitle("Test");
			break;

		}

	}

	public void setUp() {

		// Add Submit Button Login

		mainView.submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("Hello");

				Date dateIn = new Date(mainView.timeIn.getValue());
				Date dateOut = new Date(mainView.timeOut.getValue());

				work = new WorkDay("jacob", dateIn, dateOut, mainView.taskList.getSelectedItemText(),
						mainView.notes.getValue());

				punchService.addWork(work, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						Window.alert("Work Saved");

					}
				});

			}
		});

	}

}
