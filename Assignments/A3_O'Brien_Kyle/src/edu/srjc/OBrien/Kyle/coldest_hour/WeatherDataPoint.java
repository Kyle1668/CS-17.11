// Kyle O'Brien
// 9-15-17
// CS 17.11
// A3 - Coldest Hour of the Day
// Summary: This class holds weather data. The class has two data members.
// "temperature" and "date" are initialized by two constructors.
// The first constructor takes in a line of data, parses it,
// and assigns the two members with the corresponding
// elements. The second constructor takes explicit
// arguments that are immediately assigned without
// any parsing. Getter and Setter methods control
// access to the private data members.

package edu.srjc.OBrien.Kyle.coldest_hour;

import java.util.Date;
import java.text.SimpleDateFormat;

public class WeatherDataPoint
{
    private float temperature = 0;
    private String date = "";

    WeatherDataPoint(String csvData)
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

    public WeatherDataPoint(String time, float temperature)
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

}
