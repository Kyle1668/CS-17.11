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

public class ElectricDataPoint
{
    private String date = "";
    private double cumulativeUsage = 0;

    public ElectricDataPoint()
    {
    }

    public ElectricDataPoint(String date, double cumulativeUsage)
    {
        this.date = date;
        this.cumulativeUsage = cumulativeUsage;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getCumulativeUsage()
    {
        return cumulativeUsage;
    }

    public void setCumulativeUsage(double cumulativeUsage)
    {
        this.cumulativeUsage = cumulativeUsage;
    }

    @Override
    public String toString()
    {
        return "ElectricDataPoint{" +
                "date='" + date + '\'' +
                ", cumulativeUsage=" + cumulativeUsage +
                '}';
    }

}
