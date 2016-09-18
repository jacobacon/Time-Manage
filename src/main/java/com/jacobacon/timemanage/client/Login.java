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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Login extends Composite {

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

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
	
	private Boolean tooShort = false;
	
	@UiHandler("buttonSubmit")
	void doClickSubmit(ClickEvent event){
		if (!tooShort) {
			Window.alert("Login Successful!");
			Notify.notify("Login Successful");
		} else {
			Window.alert("Login or Password is too short!");
		}	
		
	}
	
	@UiHandler("loginBox")
	void handleLoginChange(ValueChangeEvent<String> event) {
		if (event.getValue().length() < 6) {
			tooShort = true;
			completionLabel1.setText("Login is too short. Must be > 6");
		} else {
			completionLabel1.setText("");
			tooShort = false;
		}
		
	}
	
	@UiHandler("passwordBox")
	void handlePasswordChange(ValueChangeEvent<String> event){
		if (event.getValue().length() < 6){
			tooShort = true;
			completionLabel2.setText("Password is too short!");
		} else {
			tooShort = false;
			completionLabel2.setText("");
		}
		
	}
	
	

}
