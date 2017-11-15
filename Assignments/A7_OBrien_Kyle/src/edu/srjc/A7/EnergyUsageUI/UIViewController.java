/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.A7.EnergyUsageUI;

import edu.srjc.A7.EnergyUsageUI.Controllers.CSVParseOperations;
import edu.srjc.A7.EnergyUsageUI.Models.ElectricDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.GasDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.HomeDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.TemperatureDataPoint;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Date;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UIViewController extends Application
{

    public static final String temperatureFile = "KCASONOM43.csv";
    public static final String gasFile = "pge_gas_interval_data_2016-01-01_to_2016-03-01.csv";
    public static final String electricFile = "pge_electric_interval_data_2016-01-01_to_2016-02-28.csv";
    
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

        ArrayList<GasDataPoint> dailyGasUsage = CSVParseOperations.parseGasData(gasFile);
        ArrayList<ElectricDataPoint> dailyPowerUsage = CSVParseOperations.parseElectricData(electricFile);
        ArrayList<TemperatureDataPoint> dailyTemperatureData = CSVParseOperations.parseTemperatureData(temperatureFile);

//        CSVParseOperations.printGasUsageSummary(dailyGasUsage);
//        CSVParseOperations.printElectricUsageSummary(dailyPowerUsage);
//        CSVParseOperations.printTemperatureSummary(dailyTemperatureData);

        ArrayList<HomeDataPoint> dailyHomeData = getHomeData(dailyGasUsage, dailyPowerUsage, dailyTemperatureData);

//        System.out.println("dailyGasUsage.size: " + dailyGasUsage.size());
//        System.out.println("dailyPowerUsage.size: " + dailyPowerUsage.size());
//        System.out.println("dailyTemperatureData.size: " + dailyTemperatureData.size());

        for (HomeDataPoint day : dailyHomeData)
        {
            System.out.println("---------------------------");
            System.out.println(day.toString());
            System.out.println("---------------------------");
        }


    }

    private static boolean sameDate(String date1, String date2, String date3)
    {
        return date1.equals(date2) && date2.equals(date3);
    }

    private static String latestStartDate(String date1, String date2, String date3)
    {
        String latestDate = "";
        try {
            Long epocDate1 = new SimpleDateFormat("yyyy/MM/dd").parse(date1).getTime();
            Long epocDate2 = new SimpleDateFormat("yyyy/MM/dd").parse(date2).getTime();
            Long epocDate3 = new SimpleDateFormat("yyyy/MM/dd").parse(date3).getTime();
            Long largestEpocTime = Math.max(epocDate1, Math.max(epocDate2, epocDate3);


            latestDate = new SimpleDateFormat("yyyy/MM/dd k:m:s").format(largestEpocTime));


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return latestDate;

    }

    private static ArrayList<HomeDataPoint> getHomeData(ArrayList<GasDataPoint> dailyGasUsage,
                                                        ArrayList<ElectricDataPoint> dailyPowerUsage,
                                                        ArrayList<TemperatureDataPoint> dailyTemperatureData)
    {
        ArrayList<HomeDataPoint> dailyHomeData = new ArrayList<>();

        int upperBound = Math.min(dailyGasUsage.size(), Math.min(dailyPowerUsage.size(), dailyTemperatureData.size()));
        boolean sameDate = true;
        int index = 0;

//        int gasMonth = Integer.parseInt(CSVParseOperations.formatData(dailyGasUsage.get(index).getDate().split("-")[1]));
//        int electricMonth = Integer.parseInt(CSVParseOperations.formatData(dailyPowerUsage.get(index).getDate().split("-")[1]));
//        int temperatureMonth = Integer.parseInt(CSVParseOperations.formatData(dailyTemperatureData.get(index).getDate().split("-")[1]));
//
//        if (gasMonth == electricMonth && electricMonth == temperatureMonth)
//        {
//            int gasDay = Integer.parseInt(dailyGasUsage.get(index).getDate().split("-")[2]);
//            int electricDay = Integer.parseInt(dailyPowerUsage.get(index).getDate().split("-")[2]);
//            int temperatureDay = Integer.parseInt(dailyTemperatureData.get(index).getDate().split("-")[2]);
//
//            int highestDay = Math.max(gasDay, Math.max(electricDay, temperatureDay));
//
//            int i = 0;
//
//        }

        while (index < upperBound)
        {

            String date1 = dailyGasUsage.get(index).getDate();
            String date2 = dailyPowerUsage.get(index).getDate();
            String date3 = dailyTemperatureData.get(index).getDate();

            if (sameDate(date1, date2, date3))
            {
                float highestTemp = dailyTemperatureData.get(index).getHighestTemperature();
                float lowestTemp = dailyTemperatureData.get(index).getLowestTemperature();
                double electricUsage = dailyPowerUsage.get(index).getCumulativeUsage();
                double gasUsage = dailyGasUsage.get(index).getUsage();

                dailyHomeData.add(new HomeDataPoint(date1, lowestTemp, highestTemp, gasUsage, electricUsage));

            }
            else
            {
                index++;
            }

            index++;

        }

        return dailyHomeData;
    }
    
}
