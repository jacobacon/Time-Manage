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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.services.PunchService;
import com.jacobacon.timemanage.client.services.PunchServiceAsync;
import com.jacobacon.timemanage.client.ui.resources.AppResources;
import com.jacobacon.timemanage.shared.UserData;
import com.jacobacon.timemanage.shared.WorkDay;

public class AppView extends Composite {

	/*
	 * 
	 * Tabs: Home - 0 TimeLog - 1 Reports - 2 Settings - 3 Admin - 4
	 */

	Logger log = LoggerFactory.getLogger(AppView.class);

	private static HomeUiBinder uiBinder = GWT.create(HomeUiBinder.class);
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);
	private static PunchServiceAsync punchService = GWT.create(PunchService.class);

	@UiTemplate("AppView.ui.xml")
	interface HomeUiBinder extends UiBinder<Widget, AppView> {
	}

	@UiField(provided = true)
	final AppResources res;

	@UiField
	static AnchorListItem homeTab;

	@UiField
	static AnchorListItem timeTab;

	@UiField
	static AnchorListItem reportTab;

	@UiField
	static AnchorListItem settingsTab;

	@UiField
	static AnchorListItem adminTab;

	@UiField
	NavbarLink nameLink;

	@UiField
	FlowPanel vp;

	@UiField
	AnchorListItem logoutButton;

	@UiField
	static HorizontalPanel appPanel;

	private static UserData userData;


	public AppView() {
		this.res = GWT.create(AppResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));

		loginService.getUserData(new AsyncCallback<UserData>() {

			@Override
			public void onFailure(Throwable arg0) {
				userData = new UserData();

			}

			@Override
			public void onSuccess(UserData result) {
				userData = result;
				nameLink.setText(result.getName());

			}
		});
	

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

	public static void setActiveView(int activate) {

		switch (activate) {
		case 0:
			homeTab.setActive(true);
			timeTab.setActive(false);
			reportTab.setActive(false);
			settingsTab.setActive(false);
			adminTab.setActive(false);
			appPanel.clear();
			appPanel.add(new HomeView());
			break;
		case 1:
			homeTab.setActive(false);
			timeTab.setActive(true);
			reportTab.setActive(false);
			settingsTab.setActive(false);
			adminTab.setActive(false);
			appPanel.clear();
			appPanel.add(new TimeView());
			break;
		case 2:
			homeTab.setActive(false);
			timeTab.setActive(false);
			reportTab.setActive(true);
			settingsTab.setActive(false);
			adminTab.setActive(false);
			appPanel.clear();
			appPanel.add(new ReportView());
			break;
		case 3:
			homeTab.setActive(false);
			timeTab.setActive(false);
			reportTab.setActive(false);
			settingsTab.setActive(true);
			adminTab.setActive(false);
			appPanel.clear();
			appPanel.add(new SettingsView());
			break;
		case 4:
			homeTab.setActive(false);
			timeTab.setActive(false);
			reportTab.setActive(false);
			settingsTab.setActive(false);
			adminTab.setActive(true);
			appPanel.clear();
			appPanel.add(new AdminView());
			break;

		}

	}

	public static UserData getUserData(){
		return userData;
	}
}
