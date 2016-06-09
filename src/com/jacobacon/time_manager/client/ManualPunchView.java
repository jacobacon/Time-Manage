package com.jacobacon.time_manager.client;


import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class ManualPunchView extends Composite {

	private final HorizontalPanel mainPanel = new HorizontalPanel();

	public final TextBox tb = new TextBox();
	public final Button button = new Button();

	private static List<Employee> EMPLOYEES = Arrays.asList(new Employee("", ""), new Employee("" , ""), new Employee("", ""));

	public ManualPunchView() {

		CellTable<Employee> timeTable = new CellTable<Employee>();
		
		TextColumn<Employee> nameColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee employee) {
				return employee.name;
				
			}
			
			
			
		};
		
		nameColumn.setSortable(true);
		
		TextColumn<Employee> addressColumn = new TextColumn<Employee>() {

			@Override
			public String getValue(Employee employee) {
				// TODO Auto-generated method stub
				return employee.address;
			}
		};
		
		
		addressColumn.setSortable(true);
		
		timeTable.addColumn(nameColumn, "Name");
		timeTable.addColumn(addressColumn, "Address");
		
		
		ListDataProvider<Employee> dataProvider = new ListDataProvider<Employee>();
		
		dataProvider.addDataDisplay(timeTable);
		
		List<Employee> list = dataProvider.getList();
		for(Employee employee : EMPLOYEES) {
			list.add(employee);
		}
		
		
		timeTable.getColumnSortList().push(nameColumn);
		
		mainPanel.add(timeTable);
		

	}

	public HorizontalPanel getMainPanel() {
		return mainPanel;
	}

	private static class Employee {

		private final String name;
		private final String address;

		public Employee(String name, String address) {

			this.name = name;
			this.address = address;

		}

	}

}
