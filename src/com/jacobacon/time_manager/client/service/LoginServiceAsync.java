package com.jacobacon.time_manager.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jacobacon.time_manager.client.LoginInfo;

public interface LoginServiceAsync {
	public void login(String requestUri, AsyncCallback<LoginInfo> async);

}
