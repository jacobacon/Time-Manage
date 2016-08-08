package com.jacobacon.time_manager.client.ui;

import java.util.List;
import java.util.Arrays;
import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jacobacon.time_manager.client.service.ReportService;
import com.jacobacon.time_manager.client.service.ReportServiceAsync;
import com.jacobacon.time_manager.shared.WorkDay;

public class ReportView extends Composite {

	private final HorizontalPanel mainPanel = new HorizontalPanel();

	// private PieChart pieChart;
	// private SimpleLayoutPanel layoutPanel = new SimpleLayoutPanel();

	private ReportServiceAsync reportService = GWT.create(ReportService.class);

	private Button getPunch = new Button("Clicke Me to Get Punch");

	// TO-DO Load the WorkDays from Objectify.
	private static final List<WorkDay> WORKDAYS = Arrays.asList(new WorkDay(), new WorkDay(), new WorkDay());

	CellTable<WorkDay> table = new CellTable<WorkDay>();

	public ReportView() {

		/*
		 * CellTable<Contact> table = new CellTable<Contact>();
		 * table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		 * 
		 * TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
		 * 
		 * @Override public String getValue(Contact object) { return
		 * object.name; } }; table.addColumn(nameColumn, "Name");
		 * 
		 * // Add a date column to show the birthday. DateCell dateCell = new
		 * DateCell(); Column<Contact, Date> dateColumn = new Column<Contact,
		 * Date>(dateCell) {
		 * 
		 * @Override public Date getValue(Contact object) { return
		 * object.birthday; } }; table.addColumn(dateColumn, "Birthday");
		 * 
		 * // Add a text column to show the address. TextColumn<Contact>
		 * addressColumn = new TextColumn<Contact>() {
		 * 
		 * @Override public String getValue(Contact object) { return
		 * object.address; } }; table.addColumn(addressColumn, "Address");
		 * 
		 * // Add a selection model to handle user selection. final
		 * SingleSelectionModel<Contact> selectionModel = new
		 * SingleSelectionModel<Contact>();
		 * table.setSelectionModel(selectionModel);
		 * selectionModel.addSelectionChangeHandler(new
		 * SelectionChangeEvent.Handler() { public void
		 * onSelectionChange(SelectionChangeEvent event) { Contact selected =
		 * selectionModel.getSelectedObject(); if (selected != null) {
		 * Window.alert("You selected: " + selected.name); } } });
		 * 
		 * // Set the total row count. This isn't strictly necessary, but it //
		 * affects // paging calculations, so its good habit to keep the row
		 * count up to // date. table.setRowCount(CONTACTS.size(), true);
		 * 
		 * // Push the data into the widget. table.setRowData(0, CONTACTS);
		 * 
		 * // Add it to the root panel. table.setTitle("User Report");
		 * mainPanel.add(table);
		 * 
		 */

		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<WorkDay> nameColumn = new TextColumn<WorkDay>() {

			@Override
			public String getValue(WorkDay object) {
				// TODO Auto-generated method stub
				return object.employeeName;
			}

		};

		table.addColumn(nameColumn, "Name");

		DateCell dateCell = new DateCell();
		Column<WorkDay, Date> dateColumn = new Column<WorkDay, Date>(dateCell) {

			@Override
			public Date getValue(WorkDay object) {
				// TODO Auto-generated method stub
				return object.getStartTime();
			}

		};

		table.addColumn(dateColumn, "Time In");

		TextColumn<WorkDay> notesColumn = new TextColumn<WorkDay>() {

			@Override
			public String getValue(WorkDay object) {
				// TODO Auto-generated method stub
				return object.getNotes();
			}

		};

		table.addColumn(notesColumn, "Notes");

		final ListDataProvider<WorkDay> dataProvider = new ListDataProvider<WorkDay>();

		dataProvider.addDataDisplay(table);

		List<WorkDay> list = dataProvider.getList();
		for (WorkDay workDay : WORKDAYS) {
			list.add(workDay);
		}

		// Add a selection model to handle user selection.
		final SingleSelectionModel<WorkDay> selectionModel = new SingleSelectionModel<WorkDay>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				WorkDay selected = selectionModel.getSelectedObject();
				if (selected != null) {
					Window.alert("You selected: " + selected.employeeName);
				}
			}
		});

		// table.setRowCount(WORKDAYS.size(), true);

		// table.setRowData(0, WORKDAYS);
		// table.setTitle("User Reports");

		mainPanel.add(table);

		Button testButton = new Button("Click Me", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				dataProvider.getList().get(0).notes = "Hello";
				dataProvider.refresh();
			}

		});

		
		Button testButton2 = new Button("Query Data", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				reportService.getWorkDayBulk(new AsyncCallback<List<WorkDay>>() {

					@Override
					public void onSuccess(List<WorkDay> result) {
						String output = "";
						for (int i = 0; i < result.size(); i++) {
							WorkDay workDay = result.get(i);
							output += workDay.getNotes();
						}
						Window.alert(output);

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

			}
		});

		mainPanel.add(testButton);
		mainPanel.add(testButton2);
		

	}



	public HorizontalPanel getMainPanel() {
		return mainPanel;
	}

}
