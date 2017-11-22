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

/**
 * Created by kyleobrien on 11/10/17.
 */
public class GasDataPoint
{
    private String date = "";
    private double usage = 0.0;

    public GasDataPoint(String date, double usage)
    {
        this.date = date;
        this.usage = usage;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getUsage()
    {
        return usage;
    }

    public void setUsage(double usage)
    {
        this.usage = usage;
    }

    @Override
    public String toString()
    {
        return "GasDataPoint{" +
                "date='" + date + '\'' +
                ", usage=" + usage +
                '}';
    }

}
