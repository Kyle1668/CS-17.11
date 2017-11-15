package edu.srjc.A7.EnergyUsageUI;

import edu.srjc.A7.EnergyUsageUI.Models.GasDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.ElectricDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.TemperatureDataPoint;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileNotFoundException;

public class CSVParseOperations
{
    static public ArrayList<ElectricDataPoint> parseElectricData(String fileName)
    {
        ArrayList<ElectricDataPoint> dailyPowerUsage = new ArrayList<ElectricDataPoint>();
        File powerUsageFile = new File(System.getProperty("user.dir") + "/" + fileName);

        if (powerUsageFile.exists())
        {
            Scanner csvReader = null;

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

        return dailyPowerUsage;
    }

    private static ArrayList<ElectricDataPoint> getElectricUsageSummary(Scanner fReader)
    {
        ArrayList<ElectricDataPoint> dailyPowerUsage = new ArrayList<>();
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

    public static void printElectricUsageSummary(ArrayList<ElectricDataPoint> dailyPowerUsage)
    {
        for (ElectricDataPoint day : dailyPowerUsage)
        {
            if (!day.getDate().equals("N/A"))
            {
                System.out.println(day.toString());
            }
        }
    }

    static public ArrayList<GasDataPoint> parseGasData(String fileName)
    {
        ArrayList<GasDataPoint> data = new ArrayList<>();
        File gasUsageFile = new File(System.getProperty("user.dir") + "/" + fileName);

        if (gasUsageFile.exists())
        {
            Scanner csvReader = null;

            try
            {
                csvReader = new Scanner(gasUsageFile);
                data = getGasUsageSummary(csvReader);
                printGasUsageSummary(data);
                csvReader.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Cannot open that file.");
                System.out.println("Exception: " + e.getMessage());
            }
        }

        return data;
    }

    public static void printGasUsageSummary(ArrayList<GasDataPoint> dailyGasUsage)
    {
        for (GasDataPoint day : dailyGasUsage)
        {
            if (!day.getDate().equals("N/A"))
            {
                System.out.println(day.toString());
            }
        }
    }

    private static ArrayList<GasDataPoint> getGasUsageSummary(Scanner fReader)
    {
        ArrayList<GasDataPoint> data = new ArrayList<>();
        double lineUsageData = 0.0;
        String lineDate = "N/A";

        while (fReader.hasNextLine())
        {
            String[] inputDataLine = fReader.nextLine().split(",");

            if (inputDataLine[0].equals("Natural gas usage"))
            {
                lineDate = inputDataLine[1];
                lineUsageData = Double.parseDouble(inputDataLine[2]);
                data.add(new GasDataPoint(lineDate, lineUsageData));
            }

        }

        return data;
    }

    static public ArrayList<TemperatureDataPoint> parseTemperatureData(String fileName)
    {
        Scanner csvReader = null;
        File csvWeatherDataFile = new File(System.getProperty("user.dir") + "/" + fileName);

        ArrayList<TemperatureDataPoint> allWeatherData = new ArrayList<>();

        if (csvWeatherDataFile.exists())
        {
            try
            {
                csvReader = new Scanner(csvWeatherDataFile);
                String output = "";

                while (csvReader.hasNextLine())
                {
                    output = csvReader.nextLine();

                    if (!output.equals("") && output.charAt(0) != '#' && output.split(",").length >= 7)
                    {

                        Float outTemp = Float.parseFloat(formatData(output.split(",")[7]));
                        Float inTemp = Float.parseFloat(formatData(output.split(",")[6]));

                        if (allWeatherData.size() != 0)
                        {
                            TemperatureDataPoint newData = new TemperatureDataPoint(output);
                            TemperatureDataPoint lastData = allWeatherData.get(allWeatherData.size() - 1);

                            String lastDay = lastData.getDate().split(" ")[0];
                            String currentDay = newData.getDate().split(" ")[0];

                            if (!lastDay.equals(currentDay))
                            {
                                System.out.println(lastData.getDate());
                                System.out.println(newData.getDate());

                                allWeatherData.add(newData);

                                System.out.println("Outside: " + outTemp);
                                System.out.println("Inside: " + inTemp);
                            }
                            else
                            {
                                if (outTemp < lastData.getLowestTemperature())
                                {
                                    lastData.setLowestTemperature(outTemp);
                                }
                                if (outTemp > lastData.getLowestTemperature())
                                {
                                    lastData.setLowestTemperature(outTemp);
                                }
                            }
                        }
                        else
                        {
                            TemperatureDataPoint newDataPoint = new TemperatureDataPoint(output);
                            allWeatherData.add(newDataPoint);
                        }

                    }

                }

                csvReader.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Cannot open that file.");
                System.out.println("Exception: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("That file doesn't exist. Try again");
        }

        return allWeatherData;
    }

    public static void printTemperatureSummary(ArrayList<TemperatureDataPoint> dailyPowerUsage)
    {
        for (TemperatureDataPoint day : dailyPowerUsage)
        {
            day.printData();
        }
    }

    private static String formatData(String arg)
    {
        // Removes extra quotations from csv data to avoid type casting errors.
        return !arg.equals("") ? arg.substring(1, arg.length() - 1) : arg;
    }

}
