package com.jacobacon.timemanage.client.ui;

import java.util.HashSet;
import java.util.Set;

import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.services.PunchService;
import com.jacobacon.timemanage.client.services.PunchServiceAsync;
import com.jacobacon.timemanage.client.ui.resources.AppResources;
import com.jacobacon.timemanage.shared.UserData;
import com.jacobacon.timemanage.shared.WorkDay;

public class HomeView extends Composite {

	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

	private static LoginServiceAsync loginService = GWT.create(LoginService.class);
	private static PunchServiceAsync punchService = GWT.create(PunchService.class);

	interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
	}

	@UiField(provided = true)
	final AppResources res;

	@UiField
	DateTimePicker timeIn;

	@UiField
	DateTimePicker timeOut;

	public HomeView() {
		this.res = GWT.create(AppResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Home");
	}

	@UiHandler("saveWorkButton")
	public void saveWorkButton(ClickEvent event) {
		UserData userData = AppView.getUserData();
		Window.alert(userData.getIpAddress());
		if ((timeIn.getValue() != null) && (timeOut.getValue() != null)) {
			punchService.saveWorkDay(new WorkDay(userData.getName(), userData.getUsername(), userData.getIpAddress(),
					timeIn.getValue(), timeOut.getValue()), new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable arg0) {
							Window.alert("Couldn't Save Work Day");

						}

						@Override
						public void onSuccess(Void arg0) {
							Notify.notify("Saved Work Day Successfully.");

						}

					});
		} else {
			Notify.notify("You Must Enter a Value for Time In and Time Out");
		}
	}

	@UiHandler("getWorkButton")
	public void getWorkButton(ClickEvent event) {
		UserData userData = AppView.getUserData();
		punchService.getWorkDay(userData.getName(), new AsyncCallback<WorkDay>() {

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

}
