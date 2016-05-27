package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface LoginServiceOAuth extends RemoteService {
	
	public void login();

	public String getAuthUrl();

}
