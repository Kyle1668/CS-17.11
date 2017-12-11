package edu.srjc.finalproject.obrien.kyle.quickcafe.models;

public class PlaceIcon
{
    private String photoReference = "";
    private final String apiKeyParam = "&key=AIzaSyC_KZyErDtZ42CuFscO2l5YseWaV8MCHrQ";
    private final String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1000&photoreference=";

    public String getPhotoURL()
    {
        return url + photoReference + apiKeyParam;
    }

    public String getPhotoReference()
    {
        return photoReference;
    }

    public void setPhotoReference(String photoReference)
    {
        this.photoReference = photoReference;
    }
}
