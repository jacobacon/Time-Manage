package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("oauth")
public interface LoginServiceOAuth extends RemoteService {
	
	public void login();

	public String getAuthUrl();
}
