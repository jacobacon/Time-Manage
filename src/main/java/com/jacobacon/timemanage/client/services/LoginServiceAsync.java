package com.jacobacon.timemanage.client.services;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void isLoggedIn(AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	void tryLogIn(String username, String password, Boolean remember, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	void logout(AsyncCallback<Void> callback) throws IllegalArgumentException;
	void register(String username, String password,Set<String> roles, Set<String> permissions, AsyncCallback<Void> callback) throws IllegalArgumentException; 
	void test(AsyncCallback<Long> callback) throws IllegalArgumentException;
	void checkPermission(String permission, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
	void checkRole(String role, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

}
