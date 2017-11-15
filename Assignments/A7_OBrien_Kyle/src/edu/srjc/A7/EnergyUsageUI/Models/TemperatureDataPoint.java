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

            date = new SimpleDateFormat("dd/MM/yyyy k:m:s").format(d);
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

    public String printData() {
        System.out.println(
                date + ": The coldest hour " +
                        "was hour " + coldestHourDate.split(" ")[2] +
                        " at " + String.format("%.2f", coldestHourTemperature)
                        + " degrees Fahrenheit"
        );
    }

}