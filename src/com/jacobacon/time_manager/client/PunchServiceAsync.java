package com.jacobacon.time_manager.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jacobacon.time_manager.shared.WorkDay;

public interface PunchServiceAsync {
	public void saveWork(WorkDay work, AsyncCallback<Void> async);

	public void addWork(WorkDay work, AsyncCallback<String> async);

	public void getWork(String id, AsyncCallback<WorkDay> async);

	public void getWorkQuery(AsyncCallback<WorkDay> async);

	public void getWorkDayBulk(AsyncCallback<List<WorkDay>> async);

}
