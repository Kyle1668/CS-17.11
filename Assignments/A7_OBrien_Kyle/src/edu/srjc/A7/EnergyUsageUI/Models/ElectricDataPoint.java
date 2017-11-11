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

}
