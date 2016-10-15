package com.jacobacon.timemanage.client.ui;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.services.PunchService;
import com.jacobacon.timemanage.client.services.PunchServiceAsync;
import com.jacobacon.timemanage.client.ui.resources.HomeResources;
import com.jacobacon.timemanage.shared.WorkDay;

public class TimeView extends Composite {

	Logger log = LoggerFactory.getLogger(TimeView.class);

	private static HomeUiBinder uiBinder = GWT.create(HomeUiBinder.class);

	private static PunchServiceAsync punchService = GWT.create(PunchService.class);
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);

	@UiTemplate("TimeView.ui.xml")
	interface HomeUiBinder extends UiBinder<Widget, TimeView> {
	}

	@UiField(provided = true)
	final HomeResources res;

	public TimeView() {
		this.res = GWT.create(HomeResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Time-Log");
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