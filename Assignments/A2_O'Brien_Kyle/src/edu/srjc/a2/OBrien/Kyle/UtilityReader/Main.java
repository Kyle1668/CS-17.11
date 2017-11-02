// Kyle O'Brien
// 9-15-17
// CS 17.11
// Assignment 3: Post-process Utility Data
// Summary: The program prompts a user to enter the filename of a CSV file that
// holds the data of Post-process Utility Data over a period of time. If
// the file is found and opened, the program iterates through the file
// parsing each line and finding the cumulative power usage for each
// individual day. Each day's data is held in a "DatePowerUsage"
// class. All the days are stored in an ArrayList. It the file
// is not found or opened, an exception
// is thrown and the program exits.

package edu.srjc.a2.OBrien.Kyle.UtilityReader;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main
{

    // The DatePowerUsage stores the date and cumulative energy usage for a specific day.

    private static class DatePowerUsage
    {
        private double cumulativeUsage = 0.0;
        private String date = "N/A";

        public DatePowerUsage()
        {
            cumulativeUsage = 0.0;
            date = "N/A";
        }

        public DatePowerUsage(double cumulativeUsage, String date)
        {
            this.cumulativeUsage = cumulativeUsage;
            this.date = date;
        }

        public double getCumulativeUsage()
        {
            return cumulativeUsage;
        }

        public String getDate()
        {
            return date;
        }

        public void setCumulativeUsage(double cumulativeUsage)
        {
            this.cumulativeUsage = cumulativeUsage;
        }

        public void setDate(String date)
        {
            this.date = date;
        }

        void printPowerUsage()
        {
            System.out.print(date + ": ");
            System.out.printf("%.2f\n", cumulativeUsage);
        }

    }

    private static String getFileName()
    {
        System.out.print("Enter CSV File Name: ");
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    private static ArrayList<DatePowerUsage> getUsageSummary(Scanner fReader)
    {
        ArrayList<DatePowerUsage> dailyPowerUsage = new ArrayList<DatePowerUsage>();
        double lineUsageData = 0.0;
        String lineDate = "N/A";

        dailyPowerUsage.add(new DatePowerUsage());

        while (fReader.hasNextLine())
        {
            // Receives a line from the CSV file, parses it and then assigns it to inputDataLine.

            DatePowerUsage lastDateRead = dailyPowerUsage.get(dailyPowerUsage.size() - 1);
            String[] inputDataLine = fReader.nextLine().split(",");

            if (inputDataLine[0].equals("Electric usage"))
            {
                lineUsageData = Double.parseDouble(inputDataLine[4]);
                lineDate = inputDataLine[1];

                if (lineDate.equals(lastDateRead.date))
                {
                    // Increment the most recent date's cumulative usage by the current line's (hour) usage.
                    dailyPowerUsage.get(dailyPowerUsage.size() - 1).cumulativeUsage += lineUsageData;
                }
                else
                {
                    // Creates new date object. Sets that new date's data to that of the current line.
                    // The new date is then appended to the ArrayList dailyPowerUsage.

                    DatePowerUsage newDate = new DatePowerUsage();
                    newDate.date = lineDate;
                    newDate.cumulativeUsage = lineUsageData;
                    dailyPowerUsage.add(newDate);
                }
            }

        }

        return dailyPowerUsage;
    }

    private static void readPowerUsage(File powerUsageFile)
    {
        Scanner csvReader = null;
        ArrayList<DatePowerUsage> dailyPowerUsage = null;

        try
        {
            csvReader = new Scanner(powerUsageFile);
            dailyPowerUsage = getUsageSummary(csvReader);
            printUsageSummary(dailyPowerUsage);
            csvReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot open that file.");
            System.out.println("Exception: " + e.getMessage());
        }

    }

    private static void printUsageSummary(ArrayList<DatePowerUsage> dailyPowerUsage)
    {
        for (DatePowerUsage day : dailyPowerUsage)
        {
            if (!day.date.equals("N/A"))
            {
                day.printPowerUsage();
            }
        }
    }

    public static void main(String[] args)
    {
        String fileName = getFileName();
        File csvUtilityData = new File(System.getProperty("user.dir") + "/" + fileName);

        if (csvUtilityData.exists())
        {
            readPowerUsage(csvUtilityData);
        }
        else
        {
            System.out.println("That file doesn't exist. Try again");
        }

    }

}
