//        Kyle O'Brien
//        11-16-17
//        CS 17.11
//        A7 - Energy Usage UI
//
//        This program parses the electric, gas, and temperature data from three CSV files and aggregates them
//        to a single data structure called a HomeDataPoint. There the aggregated data is visualized using a
//        line chart. The user is able to cycle through the months in the UI. The program is divided into
//        the model (Data Points), view (UI), and controller.

package edu.srjc.A7.EnergyUsageUI.Models;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TemperatureDataPoint
{
    private float lowestTemperature = 0;
    private float highestTemperature = 0;
    private float temperature = 0;
    private String date = "";

    public TemperatureDataPoint(String csvData)
    {
        // The passed in data is parsed. The epoc data is casted to day/month/year format.

        String[] data = csvData.split(",");

        if (data.length >= 7) {
            long epoc = Long.parseLong(formatData(data[0]));
            Date d = new Date(epoc * 1000);

            date = new SimpleDateFormat("yyyy/MM/dd k:m:s").format(d).split(" ")[0];
            date = date.split("/")[0] + "-" + date.split("/")[1] + "-" + date.split("/")[2];
            temperature = java.lang.Float.parseFloat(formatData(data[7]));
        }

    }

    public TemperatureDataPoint(String time, float temperature)
    {
        this.date = time;
        this.temperature = temperature;
    }

    private String formatData(String arg)
    {
        // Removes extra quotations from csv data to avoid type casting errors.
        return !arg.equals("") ? arg.substring(1, arg.length() - 1) : arg;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public float getTemperature()
    {
        return temperature;
    }

    public void setTemperature(float temperature)
    {
        this.temperature = temperature;
    }

    public float getLowestTemperature()
    {
        return lowestTemperature;
    }

    public void setLowestTemperature(float lowestTemperature)
    {
        this.lowestTemperature = lowestTemperature;
    }

    public float getHighestTemperature()
    {
        return highestTemperature;
    }

    public void setHighestTemperature(float highestTemperature)
    {
        this.highestTemperature = highestTemperature;
    }

    @Override
    public String toString()
    {
        return "TemperatureDataPoint{" +
                "lowestTemperature=" + lowestTemperature +
                ", highestTemperature=" + highestTemperature +
                ", temperature=" + temperature +
                ", date='" + date + '\'' +
                '}';
    }

    public void printData() {
        System.out.println(
                date + ": The coldest temperature was " + lowestTemperature + " degrees Fahrenheit "
                + "and the highest was " + highestTemperature + " degrees Fahrenheit. "
        );
    }

}
