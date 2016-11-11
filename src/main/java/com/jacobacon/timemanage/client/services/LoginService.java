package com.jacobacon.timemanage.client.services;

import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jacobacon.timemanage.server.shiro.User;
import com.jacobacon.timemanage.shared.UserData;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	public Boolean isLoggedIn();

	public Boolean tryLogIn(String username, String password, Boolean remember);

	public void logout();

	public String register(String username, String password, String name, Set<String> roles, Set<String> permissions);

	public Long test();

	public Boolean checkPermission(String permission);

	public Boolean checkRole(String role);

	public UserData getUserData();

	//public List<User> getUsers();

}
