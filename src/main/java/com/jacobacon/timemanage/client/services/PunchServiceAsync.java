package com.jacobacon.timemanage.client.services;

import java.util.ArrayList;
import java.util.Date;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jacobacon.timemanage.shared.WorkDay;

public interface PunchServiceAsync {

	void saveWorkDay(WorkDay workDay, AsyncCallback<Void> callback);

	void getWorkDay(Date date, AsyncCallback<WorkDay> callback);
	
	void getWorkDay(String name, AsyncCallback<WorkDay> callback);

	void getWorkDays(AsyncCallback<ArrayList<WorkDay>> callback);

}
