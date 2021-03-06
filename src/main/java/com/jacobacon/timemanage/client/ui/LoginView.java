package com.jacobacon.timemanage.client.ui;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.TimeManage;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;
import com.jacobacon.timemanage.client.ui.resources.LoginResources;

public class LoginView extends Composite {

	Logger log = LoggerFactory.getLogger(LoginView.class);

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);

	@UiTemplate("LoginView.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, LoginView> {
	}

	@UiField(provided = true)
	final LoginResources res;

	public LoginView() {
		this.res = GWT.create(LoginResources.class);
		res.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	TextBox loginBox;

	@UiField
	TextBox passwordBox;

	@UiField
	Label completionLabel1;

	@UiField
	Label completionLabel2;

	@UiField
	CheckBox myCheckBox;

	private Boolean tooShort = false;

	@UiHandler("buttonSubmit")
	void doClickSubmit(ClickEvent event) {
		tryLogin();
	}

	@UiHandler("loginBox")
	void handleLoginChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() < 3) {
			tooShort = true;
			completionLabel1.setText("Login is too short. Must be > 3");
		} else {
			completionLabel1.setText("");
			tooShort = false;
		}

	}

	@UiHandler("loginBox")
	void handleEnterUsername(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			tryLogin();
		}
	}

	@UiHandler("passwordBox")
	void handleEnterPassword(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			tryLogin();
		}
	}

	@UiHandler("passwordBox")
	void handlePasswordChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() < 3) {
			tooShort = true;
			completionLabel2.setText("Password is too short!");
		} else {
			tooShort = false;
			completionLabel2.setText("");
		}

	}

	void tryLogin() {
		if (!tooShort) {
			loginService.tryLogIn(loginBox.getValue().trim().toLowerCase(), passwordBox.getValue().trim(),
					myCheckBox.getValue(), new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onSuccess(Boolean result) {

							if (result) {
								Notify.notify("Logged in Successfully");
								History.newItem("home");
								TimeManage.showApp();
							} else {
								Notify.notify("Couldn't Log You In");
							}

						}
					});

		} else {
			Window.alert("Login or Password is too short!");
		}

	}

}
