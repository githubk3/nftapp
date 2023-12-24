package com.application.controller;

import com.application.model.Bucket;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalysistController implements Initializable {

	@FXML
	private BarChart<String, Number> barChart;

//    private final Bucket bucket = new Bucket();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Bucket bucket = new Bucket();
		String[] listGateway = { "opensea", "binance", "rarible" };

		for (int i = 0; i < listGateway.length; i++) {
			XYChart.Series<String, Number> series1 = new XYChart.Series<>();
			series1.setName(listGateway[i]);
//			double d1 = bucket.calculateTotalVolume(listGateway[i], "12-12-2023");
//			double d2 = bucket.calculateTotalVolume(listGateway[i], "15-12-2023");
//			double d3 = bucket.calculateTotalVolume(listGateway[i], "17-12-2023");
//			double d4 = bucket.calculateTotalVolume(listGateway[i], "19-12-2023");
//			double d5 = bucket.calculateTotalVolume(listGateway[i], "22-12-2023");

			series1.getData().add(new XYChart.Data<>("15/12/2023", 2560.1000000));
			series1.getData().add(new XYChart.Data<>("16/12/2023", 2014.80199389));
			series1.getData().add(new XYChart.Data<>("17/12/2023", 10000.1));
			series1.getData().add(new XYChart.Data<>("18/12/2023", 35407.444));
			series1.getData().add(new XYChart.Data<>("22/12/2023", 12000.22));
//            
//            series1.getData().add(new XYChart.Data<>("12/12/2023", d1));
//            series1.getData().add(new XYChart.Data<>("15/12/2023", d2));
//            series1.getData().add(new XYChart.Data<>("17/12/2023", d3));
//            series1.getData().add(new XYChart.Data<>("19/12/2023", d4));
//            series1.getData().add(new XYChart.Data<>("22/12/2023", d5));

			barChart.getData().addAll(series1);
		}

	}
}