package com.jacobacon.timemanage.client;

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;

public class Login extends Composite {

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);

	@UiTemplate("Login.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	@UiField(provided = true)
	final LoginResources res;

	public Login() {
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
		if (!tooShort) {
			loginService.tryLogIn(loginBox.getValue().trim().toLowerCase(), passwordBox.getValue().trim().toLowerCase(), myCheckBox.getValue(), new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Boolean result) {
					
					if(result){
						Notify.notify("You logged in Successfully");
					}
					else{
						Notify.notify("Couldn't log you in");
					}
					
				}
			});
			
		} else {
			Window.alert("Login or Password is too short!");
		}

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

}
