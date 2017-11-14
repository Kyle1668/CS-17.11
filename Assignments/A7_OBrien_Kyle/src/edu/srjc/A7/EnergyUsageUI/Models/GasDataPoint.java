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
