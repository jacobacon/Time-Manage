package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceOAuthAsync {
	public void login(String authCode, String state, AsyncCallback<String> async);
	public void getAuthUrl(AsyncCallback<String> async);

}
