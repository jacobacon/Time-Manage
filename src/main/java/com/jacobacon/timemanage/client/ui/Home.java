package com.jacobacon.timemanage.client.ui;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.NavbarLink;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.services.PunchService;
import com.jacobacon.timemanage.client.services.PunchServiceAsync;
import com.jacobacon.timemanage.client.ui.resources.HomeResources;
import com.jacobacon.timemanage.shared.UserData;
import com.jacobacon.timemanage.shared.WorkDay;

public class Home extends Composite {

	/*
	 * 
	 * Tabs: Home - 0 TimeLog - 1 Reports - 2 Settings - 3 Admin - 4
	 */

	Logger log = LoggerFactory.getLogger(Home.class);

	private static HomeUiBinder uiBinder = GWT.create(HomeUiBinder.class);
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);
	private static PunchServiceAsync punchService = GWT.create(PunchService.class);

	@UiTemplate("Home.ui.xml")
	interface HomeUiBinder extends UiBinder<Widget, Home> {
	}

	@UiField(provided = true)
	final HomeResources res;

	@UiField
	AnchorListItem homeTab;

	@UiField
	AnchorListItem timeTab;

	@UiField
	AnchorListItem reportTab;

	@UiField
	AnchorListItem settingsTab;

	@UiField
	AnchorListItem adminTab;

	@UiField
	NavbarLink nameLink;

	@UiField
	VerticalPanel vp;

	@UiField
	AnchorListItem logoutButton;

	private UserData userData;

	public Home() {
		this(0);
	}

	public Home(int tabNumber) {
		this.res = GWT.create(HomeResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));

		switch (tabNumber) {
		// Home
		case 0:
			setActiveTab(0);
			vp.add(new Label("Home Page"));
			break;
		//
		case 1:
			setActiveTab(1);
			vp.add(new Label("Time Page"));
			break;

		case 2:
			setActiveTab(2);
			vp.add(new Label("Reports Page"));
			break;
		case 3:
			setActiveTab(3);
			vp.add(new Label("Settings Page"));
			break;
		case 4:
			setActiveTab(4);
			vp.add(new Label("Admin Page"));
			break;
		}

		loginService.getUserData(new AsyncCallback<UserData>() {

			@Override
			public void onFailure(Throwable arg0) {
				userData = new UserData();

			}

			@Override
			public void onSuccess(UserData result) {
				Notify.notify(result.getName());
				nameLink.setText(result.getName());

			}
		});

		// nameLink.setText(userData.getName());

	}

	@UiHandler("testButton")
	public void testButton(ClickEvent event) {

		loginService.checkPermission("admin", new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Boolean result) {
				if (result == true) {

					loginService.test(new AsyncCallback<Long>() {

						@Override
						public void onSuccess(Long result) {
							Window.alert(result.toString());

						}

						@Override
						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub

						}
					});

				} else {
					Window.alert("You Don't Have Permission to do that!");
				}

			}

		});

		homeTab.setActive(false);

		homeTab.setEnabled(false);

		homeTab.removeFromParent();

	}

	@UiHandler("logoutButton")
	public void logoutButton(ClickEvent event) {
		loginService.logout(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				Window.Location.reload();
				Notify.notify("Logged out");

			}

		});
	}

	@UiHandler("registerButton")
	public void registerButton(ClickEvent event) {

		Set<String> permissions = new HashSet<String>();
		permissions.add("admin");
		loginService.register("test", "test", "Testy McTestUser", permissions, permissions, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Registered Successfully");

			}
		});
	}

	@UiHandler("saveWorkButton")
	public void saveWorkButton(ClickEvent event) {

		punchService.saveWorkDay(new WorkDay("Jacob Beneski", "jacobacon", "000.000.000.000", new Date(), new Date()),
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable arg0) {
						Window.alert("Couldn't Save Work Day");

					}

					@Override
					public void onSuccess(Void arg0) {
						Notify.notify("Saved Work Day Successfully.");

					}

				});
	}

	@UiHandler("getWorkButton")
	public void getWorkButton(ClickEvent event) {

		punchService.getWorkDay("Jacob Beneski", new AsyncCallback<WorkDay>() {

			@Override
			public void onFailure(Throwable arg0) {
				Notify.notify("Couln't Do It.");

			}

			@Override
			public void onSuccess(WorkDay result) {
				Notify.notify("Got Work Day For: " + result.getName() + "with un: " + result.getUsername());

			}
		});

	}

	public void setActiveTab(int activate) {

		switch (activate) {
		case 0:
			homeTab.setActive(true);
			timeTab.setActive(false);
			reportTab.setActive(false);
			settingsTab.setActive(false);
			adminTab.setActive(false);
			break;
		case 1:
			homeTab.setActive(false);
			timeTab.setActive(true);
			reportTab.setActive(false);
			settingsTab.setActive(false);
			adminTab.setActive(false);
			break;
		case 2:
			homeTab.setActive(false);
			timeTab.setActive(false);
			reportTab.setActive(true);
			settingsTab.setActive(false);
			adminTab.setActive(false);
			break;
		case 3:
			homeTab.setActive(false);
			timeTab.setActive(false);
			reportTab.setActive(false);
			settingsTab.setActive(true);
			adminTab.setActive(false);
			break;
		case 4:
			homeTab.setActive(false);
			timeTab.setActive(false);
			reportTab.setActive(false);
			settingsTab.setActive(false);
			adminTab.setActive(true);
			break;

		}

	}

}
