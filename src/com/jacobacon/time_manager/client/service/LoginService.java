package com.jacobacon.time_manager.client.service;

import org.apache.shiro.subject.Subject;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jacobacon.time_manager.shared.User;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	public void saveUser(User user);
	
	public User getUser(String id);
	
	public String login(String username, String password);
	
	

}
