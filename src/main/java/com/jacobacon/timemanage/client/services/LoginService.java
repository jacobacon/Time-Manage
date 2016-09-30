package com.jacobacon.timemanage.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	public Boolean isLoggedIn();
	public Boolean tryLogIn(String username, String password, Boolean remember);
	public void logout();
	public void register(String username, String password);
	public Long test();
	
}
