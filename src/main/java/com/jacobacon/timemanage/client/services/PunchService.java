package com.jacobacon.timemanage.client.services;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jacobacon.timemanage.shared.WorkDay;

@RemoteServiceRelativePath("punch")
public interface PunchService extends RemoteService {

	public void saveWorkDay(WorkDay workDay);

	public WorkDay getWorkDay(Date date);
	
	public WorkDay getWorkDay(String name);

	public ArrayList<WorkDay> getWorkDays();

}
