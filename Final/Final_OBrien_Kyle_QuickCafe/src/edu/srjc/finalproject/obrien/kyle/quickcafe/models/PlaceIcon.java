// File Header
// Kyle O'Brien
// Email: kyledevinobrien1@gmail.com
// Date: 12/24/17
// QuickCafe: Course Final Project
// Course: CS 17.11

// File Summary: This class serves as a model for the icon element of the Place class.
// The image attribute in the JSON is returned in multiple components that must
// be concatenated together in order to build a image url that can be loaded.
// In some cases a image is not given by the API for a place, hence the
// "placeholderImage" member holds a url to a local placeholder image.

package edu.srjc.finalproject.obrien.kyle.quickcafe.models;

public class PlaceIcon
{
    private String photoReference = "";
    private String apiKeyParam = "&key=AIzaSyC_KZyErDtZ42CuFscO2l5YseWaV8MCHrQ";
    private final String placeholderImage = "edu/srjc/finalproject/obrien/kyle/quickcafe/assets/placeholder-image.gif";

    public PlaceIcon()
    {
    }

    public PlaceIcon(String alternativeAPIKey)
    {
        this.apiKeyParam = "&key=" + alternativeAPIKey;
    }

    public String getPhotoURL()
    {
        String photoURL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&photoreference=";
        final String placePhotoURL = photoURL + photoReference + apiKeyParam;
        return !photoReference.equals("") ? placePhotoURL : placeholderImage;
    }

    public String getPhotoReference()
    {
        return photoReference;
    }

    public void setPhotoReference(String photoReference)
    {
        this.photoReference = photoReference;
    }

    public String getPlaceholderImage()
    {
        return placeholderImage;
    }

}
