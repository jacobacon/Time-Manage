package com.jacobacon.timemanage.client.ui;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.gwtbootstrap3.client.ui.AnchorListItem;
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
import com.jacobacon.timemanage.client.ui.resources.HomeResources;
import com.jacobacon.timemanage.server.shiro.User;

public class Home extends Composite {

	Logger log = LoggerFactory.getLogger(Home.class);

	private static HomeUiBinder uiBinder = GWT.create(HomeUiBinder.class);
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);

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
	AnchorListItem adminTab;

	public Home() {
		this.res = GWT.create(HomeResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		adminTab.removeFromParent();
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
<<<<<<< HEAD

		homeTab.setEnabled(false);

		homeTab.removeFromParent();
=======
		
		homeTab.setEnabled(false);
>>>>>>> ddd5227f3d0cfdab6ee393585bf852a077bf85f8
	}

	@UiHandler("registerButton")
	public void registerButton(ClickEvent event) {

		Set<String> permissions = new HashSet<String>();
		permissions.add("admin");
		loginService.register("test", "test", permissions, permissions, new AsyncCallback<Void>() {

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

	@UiField
	AnchorListItem homeTab;
	
	
	
}