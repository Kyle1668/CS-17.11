package edu.srjc.A7.EnergyUsageUI;

import edu.srjc.A7.EnergyUsageUI.Models.ElectricDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.GasDataPoint;
import edu.srjc.A7.EnergyUsageUI.Models.WeatherDataPoint;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class CSVParseOperations
{
    static public String[] returnData(String filePath)
    {
        String[] returnData = {};

        Scanner csvReader = null;
        File csvDataFile = new File(System.getProperty("user.dir") + "/" + filePath);

        if (csvDataFile.exists())
        {
            try
            {
                csvReader = new Scanner(csvDataFile);

                while (csvReader.hasNextLine())
                {
                    String output = csvReader.nextLine();

                    if (!output.equals("") && output.charAt(0) != '#')
                    {
                        WeatherDataPoint newData = new WeatherDataPoint(output);
                        allWeatherData.add(newData);
                    }

                }
            } catch (FileNotFoundException e)
            {
                System.out.println("Cannot open that file.");
                System.out.println("Exception: " + e.getMessage());
            }
        }

        return returnData;
    }

    static public ArrayList<ElectricDataPoint> parseElectricData(String filePath)
    {
        ArrayList<ElectricDataPoint> data = new ArrayList<>();

        String[] data = csvData.split(",");

        if (data.length >= 7) {

        }

    }

    static public ArrayList<GasDataPoint> parseGasData(String filePath)
    {
        ArrayList<GasDataPoint> data = new ArrayList<>();
    }

    static public ArrayList<WeatherDataPoint> parseWeatherData(String filePath)
    {
        ArrayList<WeatherDataPoint> data = new ArrayList<>();
    }

}
