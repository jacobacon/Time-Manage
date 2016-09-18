package com.jacobacon.timemanage.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void isLoggedIn(AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	void tryLogIn(String username, String password, Boolean remember, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	void logout(AsyncCallback<Void> callback) throws IllegalArgumentException;
	void register(String username, String password, AsyncCallback<Void> callback) throws IllegalArgumentException; 

}
