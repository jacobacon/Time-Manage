package com.jacobacon.time_manager.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jacobacon.time_manager.shared.User;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	public void saveUser(User user);
	
	public User getUser(String id);
	

}
