package com.jacobacon.time_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jacobacon.time_manager.client.service.LoginService;
import com.jacobacon.time_manager.shared.User;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		User user = new User();
		return user;
	}
	
	
	

}
