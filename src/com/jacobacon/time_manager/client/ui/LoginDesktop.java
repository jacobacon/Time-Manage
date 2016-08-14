package com.jacobacon.time_manager.client.ui;

import org.apache.shiro.guice.web.ShiroWebModule;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.jacobacon.time_manager.client.service.LoginService;
import com.jacobacon.time_manager.client.service.LoginServiceAsync;

public class LoginDesktop extends Composite implements HasText {

	private static LoginDesktopUiBinder uiBinder = GWT.create(LoginDesktopUiBinder.class);
	
	private static LoginServiceAsync loginService = GWT.create(LoginService.class);

	interface LoginDesktopUiBinder extends UiBinder<Widget, LoginDesktop> {
	}

	public LoginDesktop() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Login");
	}
	
	private AsyncCallback<String> loginCallback = new AsyncCallback<String>(){
		
		@Override
		public void onFailure(Throwable caught){
			Window.alert("Something went wrong! The message was: " + caught.getMessage());
		}

		@Override
		public void onSuccess(String result) {
			// TODO Auto-generated method stub
			Window.alert(result);
		}


		
	};

	@UiField
	Button button;
	
	@UiField
	TextBox username;
	
	@UiField
	PasswordTextBox password;

	public LoginDesktop(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		Window.setTitle("Login");
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		//Temporary while testing.
		Window.alert("Trying to login!");
		
		loginService.login(getUsername(), getPassword(), loginCallback);
		
		
		
	}
	
	public String getUsername(){
		return username.getValue();
	}
	
	public String getPassword(){
		return password.getValue();
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

}
