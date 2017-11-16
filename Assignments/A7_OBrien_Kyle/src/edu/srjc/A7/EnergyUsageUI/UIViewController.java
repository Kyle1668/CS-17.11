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
        app();
        launch(args);
    }

    private static void app() {

        ArrayList<GasDataPoint> dailyGasUsage = CSVParseOperations.parseGasData(gasFile);
        ArrayList<ElectricDataPoint> dailyPowerUsage = CSVParseOperations.parseElectricData(electricFile);
        ArrayList<TemperatureDataPoint> dailyTemperatureData = CSVParseOperations.parseTemperatureData(temperatureFile);

        ArrayList<HomeDataPoint> dailyHomeData = getHomeData(dailyGasUsage, dailyPowerUsage, dailyTemperatureData);

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

    private static String getCommonStartDate(String date1, String date2, String date3)
    {
        String latestDate = "";

        try {
            Long epocDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1).getTime();
            Long epocDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2).getTime();
            Long epocDate3 = new SimpleDateFormat("yyyy-MM-dd").parse(date3).getTime();
            Long largestEpocTime = Math.max(epocDate1, Math.max(epocDate2, epocDate3));

            latestDate = new SimpleDateFormat("yyyy-MM-dd").format(largestEpocTime);
            System.out.print("latestDate: " + latestDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return latestDate;
    }

    private static ArrayList<HomeDataPoint> getHomeData(ArrayList<GasDataPoint> dailyGasUsage,
                                                        ArrayList<ElectricDataPoint> dailyElectricUsage,
                                                        ArrayList<TemperatureDataPoint> dailyTemperatureData)
    {
        ArrayList<HomeDataPoint> dailyHomeData = new ArrayList<>();

        int upperBound = Math.min(dailyGasUsage.size(), Math.min(dailyElectricUsage.size(), dailyTemperatureData.size()));
        int index = 0;

        String gasDate = dailyGasUsage.get(0).getDate();
        String electricDate = dailyElectricUsage.get(0).getDate();
        String temperatureDate = dailyTemperatureData.get(0).getDate();
        String startDate = getCommonStartDate(gasDate, electricDate, temperatureDate);

        for (int i = 0; i < dailyGasUsage.size(); i++)
        {
            if (!dailyGasUsage.get(i).getDate().equals(startDate))
            {
                dailyGasUsage.remove(i);
                i--;
            }
            else
            {
                break;
            }
        }

        for (int i = 0; i < dailyElectricUsage.size(); i++)
        {
            if (!dailyElectricUsage.get(i).getDate().equals(startDate))
            {
                dailyElectricUsage.remove(i);
                i--;
            }
            else
            {
                break;
            }
        }

        for (int i = 0; i < dailyTemperatureData.size(); i++)
        {
            if (!dailyTemperatureData.get(i).getDate().equals(startDate))
            {
                dailyTemperatureData.remove(i);
                i--;
            }
            else
            {
                break;
            }
        }

        while (index < upperBound)
        {
            gasDate = dailyGasUsage.get(index).getDate();
            electricDate = dailyElectricUsage.get(index).getDate();
            temperatureDate = dailyTemperatureData.get(index).getDate();

            if (sameDate(gasDate, electricDate, temperatureDate))
            {
                float highestTemp = dailyTemperatureData.get(index).getHighestTemperature();
                float lowestTemp = dailyTemperatureData.get(index).getLowestTemperature();
                double electricUsage = dailyElectricUsage.get(index).getCumulativeUsage();
                double gasUsage = dailyGasUsage.get(index).getUsage();

                dailyHomeData.add(new HomeDataPoint(gasDate, lowestTemp, highestTemp, gasUsage, electricUsage));
            }

            index++;
        }

        return dailyHomeData;
    }
    
}
