package edu.srjc.A7.EnergyUsageUI.Models;

public class HomeDataPoint
{
    private String data = "";
    private String cumulativeGasData = "";
    private String cumulativeElectricData = "";
    private String cumulativeWeatherData = "";

    public HomeDataPoint()
    {
    }

    public HomeDataPoint(String data, String cumulativeGasData, String cumulativeElectricData, String cumulativeWeatherData)
    {
        this.data = data;
        this.cumulativeGasData = cumulativeGasData;
        this.cumulativeElectricData = cumulativeElectricData;
        this.cumulativeWeatherData = cumulativeWeatherData;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getCumulativeGasData()
    {
        return cumulativeGasData;
    }

    public void setCumulativeGasData(String cumulativeGasData)
    {
        this.cumulativeGasData = cumulativeGasData;
    }

    public String getCumulativeElectricData()
    {
        return cumulativeElectricData;
    }

    public void setCumulativeElectricData(String cumulativeElectricData)
    {
        this.cumulativeElectricData = cumulativeElectricData;
    }

    public String getCumulativeWeatherData()
    {
        return cumulativeWeatherData;
    }

    public void setCumulativeWeatherData(String cumulativeWeatherData)
    {
        this.cumulativeWeatherData = cumulativeWeatherData;
    }

    @Override
    public String toString()
    {
        return "HomeDataPoint{" +
                "data='" + data + '\'' +
                ", cumulativeGasData='" + cumulativeGasData + '\'' +
                ", cumulativeElectricData='" + cumulativeElectricData + '\'' +
                ", cumulativeWeatherData='" + cumulativeWeatherData + '\'' +
                '}';
    }

}
