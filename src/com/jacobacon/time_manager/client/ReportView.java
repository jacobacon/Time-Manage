package com.jacobacon.time_manager.client;

import java.util.List;
import java.util.Arrays;
import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;

public class ReportView extends Composite {

	private final HorizontalPanel mainPanel = new HorizontalPanel();

	// private PieChart pieChart;
	// private SimpleLayoutPanel layoutPanel = new SimpleLayoutPanel();

	private static final List<Contact> CONTACTS = Arrays.asList(new Contact("Jose", new Date(80, 4, 12), "123 Main Street"),
			new Contact("Jacob", new Date(80, 4, 12), "1514 Baker Street"), new Contact("Peter", new Date(80, 4, 12), "1045, MLK Jr. Dr")

	);

	public ReportView() {

		CellTable<Contact> table = new CellTable<Contact>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.name;
			}
		};
		table.addColumn(nameColumn, "Name");

		// Add a date column to show the birthday.
		DateCell dateCell = new DateCell();
		Column<Contact, Date> dateColumn = new Column<Contact, Date>(dateCell) {
			@Override
			public Date getValue(Contact object) {
				return object.birthday;
			}
		};
		table.addColumn(dateColumn, "Birthday");

		// Add a text column to show the address.
		TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.address;
			}
		};
		table.addColumn(addressColumn, "Address");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Contact> selectionModel = new SingleSelectionModel<Contact>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Contact selected = selectionModel.getSelectedObject();
				if (selected != null) {
					Window.alert("You selected: " + selected.name);
				}
			}
		});

		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		table.setRowCount(CONTACTS.size(), true);

		// Push the data into the widget.
		table.setRowData(0, CONTACTS);

		// Add it to the root panel.
		table.setTitle("User Report");
		mainPanel.add(table);

	}

	// RootLayoutPanel.get().add(getSimpleLayoutPanel());

	// ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
	// chartLoader.loadApi(new Runnable() {

	// @Override
	// public void run() {
	// layoutPanel.setWidget(pieChart);
	// drawPieChart();

	// }
	// });

	// mainPanel.add(layoutPanel);

	private void drawPieChart() {
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Subject");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Students");
		dataTable.addRows(4);
		dataTable.setValue(0, 0, "History");
		dataTable.setValue(1, 0, "Computers");
		dataTable.setValue(2, 0, "Managment");
		dataTable.setValue(3, 0, "Politics");
		dataTable.setValue(0, 1, 20);
		dataTable.setValue(1, 1, 25);
		dataTable.setValue(2, 1, 30);
		dataTable.setValue(3, 1, 35);
		// pieChart.draw(dataTable);

	}

	public HorizontalPanel getMainPanel() {
		return mainPanel;
	}

	private static class Contact {
		private final String address;
		private final Date birthday;
		private final String name;

		public Contact(String name, Date birthday, String address) {
			this.address = address;
			this.birthday = birthday;
			this.name = name;
		}

	}

}
