package com.jacobacon.time_manager.client.service;

import org.apache.shiro.subject.Subject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jacobacon.time_manager.shared.User;

public interface LoginServiceAsync {
	public void saveUser(User user, AsyncCallback<Void> async);
	
	public void getUser(String id, AsyncCallback<User> async);
	
	public void login(String username, String password, AsyncCallback<String> async);

	

}
