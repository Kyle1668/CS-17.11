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

public class HomeDataPoint
{

    private String date = "";
    private float lowestTemperature = 0;
    private float highestTemperature = 0;
    private double cumulativeGasUsage = 0.0;
    private double cumulativeElectricUsage = 0.0;

    public HomeDataPoint(String date, float lowestTemperature, float highestTemperature, double cumulativeGasUsage, double cumulativeElectricUsage)
    {
        this.date = date;
        this.lowestTemperature = lowestTemperature;
        this.highestTemperature = highestTemperature;
        this.cumulativeGasUsage = cumulativeGasUsage;
        this.cumulativeElectricUsage = cumulativeElectricUsage;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
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

    public double getCumulativeGasUsage()
    {
        return cumulativeGasUsage;
    }

    public void setCumulativeGasUsage(double cumulativeGasUsage)
    {
        this.cumulativeGasUsage = cumulativeGasUsage;
    }

    public double getCumulativeElectricUsage()
    {
        return cumulativeElectricUsage;
    }

    public void setCumulativeElectricUsage(double cumulativeElectricUsage)
    {
        this.cumulativeElectricUsage = cumulativeElectricUsage;
    }

    @Override
    public String toString()
    {
        return "HomeDataPoint{" +
                "date='" + date + '\'' +
                ", lowestTemperature='" + lowestTemperature + '\'' +
                ", highestTemperature='" + highestTemperature + '\'' +
                ", cumulativeGasUsage='" + cumulativeGasUsage + '\'' +
                ", cumulativeElectricUsage='" + cumulativeElectricUsage + '\'' +
                '}';
    }

}
