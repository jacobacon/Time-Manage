package com.jacobacon.time_manager.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.jacobacon.time_manager.client.service.PunchService;
import com.jacobacon.time_manager.client.service.PunchServiceAsync;
import com.jacobacon.time_manager.client.ui.MainView;
import com.jacobacon.time_manager.client.ui.ManualPunchView;
import com.jacobacon.time_manager.client.ui.MobileView;
import com.jacobacon.time_manager.client.ui.ReportView;
import com.jacobacon.time_manager.client.ui.Test;
import com.jacobacon.time_manager.shared.WorkDay;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Time_Manager implements EntryPoint {
	final MainView mainView = new MainView();
	final ManualPunchView manualPunchView = new ManualPunchView();
	final ReportView reportView = new ReportView();

	final String url = "punch";

	private WorkDay work;

	// private final Grid grid = new Grid(5, 5);

	private PunchServiceAsync punchService;
	private static List<WorkDay> WORKDAYS = Arrays.asList(new WorkDay(), new WorkDay(), new WorkDay());

	private static List<WorkDay> days;

	private static String userAgent;
	private static String platform;

	private static boolean mobile;

	public void onModuleLoad() {

		// Check if user is on mobile or desktop.
		userAgent = Navigator.getUserAgent();
		platform = Navigator.getPlatform();

		if (userAgent.toLowerCase().contains("android") || (userAgent.toLowerCase().contains("iphone"))
				|| (userAgent.toLowerCase().contains("mobile")))
			mobile = true;
		else
			mobile = false;

		setUp();
		if(!mobile)
			showApp();
		else
			RootPanel.get().add(new MobileView());

	}

	// Method that shows the actual app after login.
	public void showApp() {

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
				Window.alert(Cookies.getCookie("test"));

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
		headerPanel.add(new Label("You are using: " + platform + " Mobile: " + mobile));
		headerPanel.add(new Label("Sign Out Link Will Go Here."));
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
						// TODO Auto-generated method stub
						Window.alert("You completed: " + result.getTaskCompleted() + "\nYour Notes are: "
								+ result.getNotes() + "\nYou clocked in at: " + result.getStartTime()
								+ "\nAnd clocked out at: " + result.getEndTime());

					}

				});

			}
		});

		Button b4 = new Button("Batch Query", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				punchService.getWorkDayBulk(new AsyncCallback<List<WorkDay>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<WorkDay> result) {
						// TODO Auto-generated method stub
						String output = "";
						for (int i = 0; i < result.size(); i++) {
							WorkDay workDay = result.get(i);
							output += workDay.getNotes();
						}
						Window.alert(output);

					}
				});

			}

		});

		Button b5 = new Button("Test UIBinder", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				RootPanel.get("content").clear();
				Notify.notify("The Button Was Clicked!", "You clicked a button!");
				RootPanel.get("content").add(new Test());
			}

		});

		RootPanel.get("header").add(headerPanel);

		setContent(0);

		// Temporary Buttons for Testing
		RootPanel.get("content").add(b);
		RootPanel.get("content").add(b4);
		RootPanel.get("content").add(b5);

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

			Window.setTitle("Reports");
			break;

		}

	}

	public void setUp() {

		// Add Submit Button Logic

		mainView.submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Date dateIn = new Date(mainView.timeIn.getValue());
				Date dateOut = new Date(mainView.timeOut.getValue());

				Date breakStart = new Date(mainView.mealStart.getValue());
				Date breakEnd = new Date(mainView.mealEnd.getValue());

				// work = new WorkDay(loginInfo.getNickname(), dateIn, dateOut,
				// mainView.taskList.getSelectedItemText(),
				// mainView.notes.getValue());

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
