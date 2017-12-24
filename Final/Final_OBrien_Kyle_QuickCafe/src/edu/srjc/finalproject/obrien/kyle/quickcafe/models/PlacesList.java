package edu.srjc.finalproject.obrien.kyle.quickcafe.models;

import java.util.ArrayList;

public class PlacesList extends ArrayList<Place>
{
    private String errorMessage = "";

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        boolean metAPILimit = errorMessage.equals("You have exceeded your daily request quota for this API.");
        this.errorMessage = metAPILimit ? "Met Daily API Limit" : errorMessage;
    }

}
