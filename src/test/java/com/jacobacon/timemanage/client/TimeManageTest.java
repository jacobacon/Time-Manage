package com.jacobacon.timemanage.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.jacobacon.timemanage.client.services.LoginService;
import com.jacobacon.timemanage.client.services.LoginServiceAsync;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class TimeManageTest extends GWTTestCase {

	/**
	 * Must refer to a valid module that sources this class.
	 */
	public String getModuleName() {
		return "com.jacobacon.timemanage.TimeManageJUnit";
	}

	/*
	 *Tests are disabled in the POM file.
	 *To enable them uncomment the test goal. 
	 */

	public void testLoginServiceLogin() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		ServiceDefTarget target = (ServiceDefTarget) loginService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "timemanage/login");

		delayTestFinish(10000);

		loginService.tryLogIn("jacob", "test", false, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				fail("Request Failure: " + caught.getMessage());

			}

			@Override
			public void onSuccess(Boolean result) {
				assertTrue(result == true);
				finishTest();
			}
		});

	}
}