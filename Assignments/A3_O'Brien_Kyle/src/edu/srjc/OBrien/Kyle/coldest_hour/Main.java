// Kyle O'Brien
// 9-15-17
// CS 17.11
// A3 - Coldest Hour of the Day
// Summary: The program prompts a user to enter the filename of a CSV file that holds
// the data of outside temperatures in the Sonoma Valley in 5-minute intervals over
// a period of time. The program iterates through the file and stores each line of
// data in an ArrayList of time "WeatherDataPoint". The program then iterates
// through this structure and gets each hour and its average temperature.
// This data is stored in a second array list of type "WeatherDataPoint".
// Finally, the program iterates through this second data structure
// and prints the coldest hour and temperature for each day.

package edu.srjc.OBrien.Kyle.coldest_hour;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main {

    private static String getFileName()
    {
        System.out.print("Enter CSV File Name: ");
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    public static void main(String[] args)
    {
        Scanner csvReader = null;
        String fileName = getFileName();
        File csvWeatherDataFile = new File(System.getProperty("user.dir") + "/" + fileName);

        ArrayList<WeatherDataPoint> allWeatherData = new ArrayList<WeatherDataPoint>();
        ArrayList<WeatherDataPoint> hourlyWeatherData = new ArrayList<WeatherDataPoint>();

        if (csvWeatherDataFile.exists())
        {
            try
            {
                csvReader = new Scanner(csvWeatherDataFile);

                while (csvReader.hasNextLine())
                {
                    String output = csvReader.nextLine();

                    if (!output.equals("") && output.charAt(0) != '#')
                    {
                        WeatherDataPoint newData = new WeatherDataPoint(output);
                        allWeatherData.add(newData);
                    }

                }

                csvReader.close();

                System.out.println("\n");

                int numDataPointsRecentHour = 0;

                // Iterate through all the data and get the average for each hour.
                // Each hour and it's average temperature is stored in the "hourlyWeatherData" structure.

                for (WeatherDataPoint data : allWeatherData)
                {
                    String date = data.getDate().split(" ")[0];
                    String hour = data.getDate().split(" ")[1].split(":")[0];
                    String dateAndHour = date + " Hour: " + hour;

                    numDataPointsRecentHour++;

                    if (hourlyWeatherData.size() == 0)
                    {
                        WeatherDataPoint newHourData = new WeatherDataPoint(dateAndHour, data.getTemperature());
                        hourlyWeatherData.add(newHourData);
                    }
                    else
                    {
                        int lastIndex = hourlyWeatherData.size() - 1;
                        String lastDate = hourlyWeatherData.get(lastIndex).getDate();
                        Float lastTemp = hourlyWeatherData.get(lastIndex).getTemperature();

                        if (lastDate.equals(dateAndHour))
                        {
                            // If the hour has not changed, increment the total sum of temperatures.
                            Float newTemperature = lastTemp + data.getTemperature();
                            hourlyWeatherData.get(lastIndex).setTemperature(newTemperature);
                        }
                        else
                        {
                            // If the hour has changed, calculate the average temperature and assign that value.

                            float averageTemp = hourlyWeatherData.get(lastIndex).getTemperature() / numDataPointsRecentHour;
                            hourlyWeatherData.get(lastIndex).setTemperature(averageTemp);

                            WeatherDataPoint newHourData = new WeatherDataPoint(dateAndHour, data.getTemperature());
                            hourlyWeatherData.add(newHourData);

                            numDataPointsRecentHour = 0;
                        }

                    }

                }

                // Determine and print the coldest hour from each day.

                if (hourlyWeatherData.size() > 0)
                {
                   String coldestHourDate = hourlyWeatherData.get(0).getDate();
                   Float coldestHourTemperature = hourlyWeatherData.get(0).getTemperature();

                    for (WeatherDataPoint hourData : hourlyWeatherData)
                    {
                        String currentDate = hourData.getDate();
                        Float currentTemperature = hourData.getTemperature();
                        boolean dayChanged = !hourData.getDate().split("/")[0].equals(coldestHourDate.split("/")[0]);

                        if (dayChanged)
                        {
                            // Print the coldest hour from the previous day then set values to first data from new day.

                            System.out.println(
                                    coldestHourDate.split(" ")[0] + ": The coldest hour " +
                                    "was hour " + coldestHourDate.split(" ")[2] +
                                    " at " + String.format("%.2f", coldestHourTemperature)
                                    + " degrees Fahrenheit"
                            );

                            coldestHourTemperature = currentTemperature;
                            coldestHourDate = currentDate;
                        }

                        if (currentTemperature < coldestHourTemperature)
                        {
                            coldestHourTemperature = currentTemperature;
                            coldestHourDate = currentDate;
                        }

                    }

                }

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

    }

}
