package gui.charts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class BarChartSample extends Application
{
    public void start(Stage stage)
    {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Monat");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Anzahl");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Lagerbest�nde 2014");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Produkt 1");
        series1.getData().add(new XYChart.Data<String, Number>("Jan", 23));
        series1.getData().add(new XYChart.Data<String, Number>("Feb", 14));
        series1.getData().add(new XYChart.Data<String, Number>("M�r", 15));
        series1.getData().add(new XYChart.Data<String, Number>("Apr", 24));
        series1.getData().add(new XYChart.Data<String, Number>("Mai", 34));
        series1.getData().add(new XYChart.Data<String, Number>("Jun", 36));
        series1.getData().add(new XYChart.Data<String, Number>("Jul", 22));
        series1.getData().add(new XYChart.Data<String, Number>("Aug", 45));
        series1.getData().add(new XYChart.Data<String, Number>("Sep", 43));
        series1.getData().add(new XYChart.Data<String, Number>("Okt", 17));
        series1.getData().add(new XYChart.Data<String, Number>("Nov", 29));
        series1.getData().add(new XYChart.Data<String, Number>("Dez", 25));
        barChart.getData().add(series1);

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Produkt 2");
        series2.getData().add(new XYChart.Data<String, Number>("Jan", 33));
        series2.getData().add(new XYChart.Data<String, Number>("Feb", 34));
        series2.getData().add(new XYChart.Data<String, Number>("M�r", 25));
        series2.getData().add(new XYChart.Data<String, Number>("Apr", 44));
        series2.getData().add(new XYChart.Data<String, Number>("Mai", 39));
        series2.getData().add(new XYChart.Data<String, Number>("Jun", 16));
        series2.getData().add(new XYChart.Data<String, Number>("Jul", 55));
        series2.getData().add(new XYChart.Data<String, Number>("Aug", 54));
        series2.getData().add(new XYChart.Data<String, Number>("Sep", 48));
        series2.getData().add(new XYChart.Data<String, Number>("Okt", 27));
        series2.getData().add(new XYChart.Data<String, Number>("Nov", 37));
        series2.getData().add(new XYChart.Data<String, Number>("Dez", 29));
        barChart.getData().add(series2);

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Produkt 3");
        series3.getData().add(new XYChart.Data<String, Number>("Jan", 44));
        series3.getData().add(new XYChart.Data<String, Number>("Feb", 35));
        series3.getData().add(new XYChart.Data<String, Number>("M�r", 36));
        series3.getData().add(new XYChart.Data<String, Number>("Apr", 33));
        series3.getData().add(new XYChart.Data<String, Number>("Mai", 31));
        series3.getData().add(new XYChart.Data<String, Number>("Jun", 26));
        series3.getData().add(new XYChart.Data<String, Number>("Jul", 22));
        series3.getData().add(new XYChart.Data<String, Number>("Aug", 25));
        series3.getData().add(new XYChart.Data<String, Number>("Sep", 43));
        series3.getData().add(new XYChart.Data<String, Number>("Okt", 44));
        series3.getData().add(new XYChart.Data<String, Number>("Nov", 45));
        series3.getData().add(new XYChart.Data<String, Number>("Dez", 44));
        barChart.getData().add(series3);

        Scene scene = new Scene(barChart, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Balkendiagramm");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}