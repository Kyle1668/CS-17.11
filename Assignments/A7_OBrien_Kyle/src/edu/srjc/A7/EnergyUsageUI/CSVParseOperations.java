package edu.srjc.A7.EnergyUsageUI;

import edu.srjc.A7.EnergyUsageUI.Models.GasDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.ElectricDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.TemperatureDataPoint;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class CSVParseOperations
{
    static public ArrayList<ElectricDataPoint> parseElectricData(String fileName)
    {
        ArrayList<ElectricDataPoint> allElectricData = new ArrayList<ElectricDataPoint>();
        ArrayList<ElectricDataPoint> hourlyElectricData = new ArrayList<ElectricDataPoint>();
        File powerUsageFile = new File(System.getProperty("user.dir") + "/" + fileName);

        String lastData = "";

        if (powerUsageFile.exists())
        {
            Scanner csvReader = null;
            ArrayList<ElectricDataPoint> dailyPowerUsage = null;

            try
            {
                csvReader = new Scanner(powerUsageFile);
                dailyPowerUsage = getElectricUsageSummary(csvReader);
                printElectricUsageSummary(dailyPowerUsage);
                csvReader.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Cannot open that file.");
                System.out.println("Exception: " + e.getMessage());
            }
        }

        return allElectricData;
    }

    private static ArrayList<ElectricDataPoint> getElectricUsageSummary(Scanner fReader)
    {
        ArrayList<ElectricDataPoint> dailyPowerUsage = new ArrayList<ElectricDataPoint>();
        double lineUsageData = 0.0;
        String lineDate = "N/A";

        dailyPowerUsage.add(new ElectricDataPoint());

        while (fReader.hasNextLine())
        {
            // Receives a line from the CSV file, parses it and then assigns it to inputDataLine.

            ElectricDataPoint lastDateRead = dailyPowerUsage.get(dailyPowerUsage.size() - 1);
            String[] inputDataLine = fReader.nextLine().split(",");

            if (inputDataLine[0].equals("Electric usage"))
            {
                lineUsageData = Double.parseDouble(inputDataLine[4]);
                lineDate = inputDataLine[1];

                if (lineDate.equals(lastDateRead.getDate()))
                {
                    // Increment the most recent date's cumulative usage by the current line's (hour) usage.
                    double newValue = dailyPowerUsage.get(dailyPowerUsage.size() - 1).getCumulativeUsage() + lineUsageData;
                    dailyPowerUsage.get(dailyPowerUsage.size() - 1).setCumulativeUsage(newValue);
                }
                else
                {
                    // Creates new date object. Sets that new date's data to that of the current line.
                    // The new date is then appended to the ArrayList dailyPowerUsage.

                    ElectricDataPoint newDate = new ElectricDataPoint(lineDate, lineUsageData);
                    dailyPowerUsage.add(newDate);
                }
            }

        }

        return dailyPowerUsage;
    }

    private static void printElectricUsageSummary(ArrayList<ElectricDataPoint> dailyPowerUsage)
    {
        for (ElectricDataPoint day : dailyPowerUsage)
        {
            if (!day.getDate().equals("N/A"))
            {
                System.out.println(day.toString());
            }
        }
    }

    static public ArrayList<GasDataPoint> parseGasData(String filePath)
    {
        ArrayList<GasDataPoint> data = new ArrayList<>();
        return data;
    }

    private static void printGasUsageSummary(ArrayList<GasDataPoint> dailyPowerUsage)
    {
        for (GasDataPoint day : dailyPowerUsage)
        {
//            if (!day.getDate().equals("N/A"))
//            {
//                System.out.println(day.toString());
//            }
        }
    }

    static public ArrayList<TemperatureDataPoint> parseTemperatureData(String filePath)
    {
        ArrayList<TemperatureDataPoint> data = new ArrayList<>();
        return data;
    }

    private static void printTemperatureSummary(ArrayList<ElectricDataPoint> dailyPowerUsage)
    {
        for (ElectricDataPoint day : dailyPowerUsage)
        {
            if (!day.getDate().equals("N/A"))
            {
                System.out.println(day.toString());
            }
        }
    }

}
