/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.A7.EnergyUsageUI;

import edu.srjc.A7.EnergyUsageUI.Models.ElectricDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.GasDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.TemperatureDataPoint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


public class UIViewController extends Application
{
    public final String electricData = "pge_electric_interval_data_2016-01-01_to_2016-02-28.csv";
    public final String gascData = "pge_gas_interval_data_2016-01-01_to_2016-03-01.csv";
    public final String temperatureData = "KCASONOM43.csv";
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Program Start");



        app();
//        launch(args);
        System.out.println("Program End");
    }

    private static void app() {

        final String temperatureFile = "KCASONOM43.csv";
        final String gasFile = "pge_gas_interval_data_2016-01-01_to_2016-03-01.csv";
        final String electricFile = "pge_electric_interval_data_2016-01-01_to_2016-02-28.csv";

        ArrayList<GasDataPoint> dailyGasUsage = CSVParseOperations.parseGasData(gasFile);
        ArrayList<ElectricDataPoint> dailyPowerUsage = CSVParseOperations.parseElectricData(electricFile);
        ArrayList<TemperatureDataPoint> dailyTemperatureData = CSVParseOperations.parseTemperatureData(gasFile);

        CSVParseOperations.printGasUsageSummary(dailyGasUsage);
        CSVParseOperations.printElectricUsageSummary(dailyPowerUsage);
        CSVParseOperations.printTemperatureSummary(dailyTemperatureData);


    }
    
}
