package com.jacobacon.time_manager.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;

public class ReportView extends Composite {

	private final HorizontalPanel mainPanel = new HorizontalPanel();

	private PieChart pieChart;
	private SimpleLayoutPanel layoutPanel = new SimpleLayoutPanel();

	public ReportView() {

		// RootLayoutPanel.get().add(getSimpleLayoutPanel());

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				layoutPanel.setWidget(pieChart);
				drawPieChart();

			}
		});

		mainPanel.add(layoutPanel);

	}

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
		pieChart.draw(dataTable);

	}

	public HorizontalPanel getMainPanel() {
		return mainPanel;
	}

}
