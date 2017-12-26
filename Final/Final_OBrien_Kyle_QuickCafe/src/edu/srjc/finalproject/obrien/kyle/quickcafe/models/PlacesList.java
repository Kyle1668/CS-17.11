// File Header
// Kyle O'Brien
// Email: kyledevinobrien1@gmail.com
// Date: 12/24/17
// QuickCafe: Course Final Project
// Course: CS 17.11

// File Summary: This class serves as a model for the list of Places that are the result of
// parsing the output from the Google Places API. This class inherits from the
// ArrayList<Place> class. Thus, this type has all the functionality and
// behavior of the ArrayList<Place> class as well as adding additional
// functionality around storing API error messages.

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
