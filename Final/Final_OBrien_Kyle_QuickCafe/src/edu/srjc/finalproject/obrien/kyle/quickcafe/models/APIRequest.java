package edu.srjc.finalproject.obrien.kyle.quickcafe.models;

import java.net.URL;
import java.io.BufferedReader;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.net.HttpURLConnection;

public class APIRequest
{

    static public String formatAPIRequest(String target) throws UnknownHostException
    {
        String[] apiKeys = {"AIzaSyDkaFm1KmYTToZTX8Z2S-Mn9rdblJOk1YY", "AIzaSyC_KZyErDtZ42CuFscO2l5YseWaV8MCHrQ"};
        String request = "https://maps.googleapis.com/maps/api/place/textsearch/json?";
        String query = "query=Cafe+coffee+near+" + target.replace(" ", "+");
        String apiKey = "&key=" + apiKeys[0] + "&sensor=false";
        String apiURL = request + query + apiKey;
        boolean validURL = testInternetConnection(apiURL);
        return validURL ? apiURL : "No Connection";
    }

    static public void printAPIResponse(String httpGetRequest) throws Exception
    {
        if (!httpGetRequest.equals("No Connection"))
        {
            URL apiURL = new URL(httpGetRequest);
            URLConnection apiConnection = apiURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

            while (in.readLine() != null)
            {
                System.out.println(in.readLine());
            }

            in.close();
        }
        else
        {
            System.out.println("API Is Unreachable.");
        }
    }

    static public String returnStringAPIResponse(String httpGetRequest) throws Exception
    {
        if (!httpGetRequest.equals("No Connection"))
        {
            URL apiURL = new URL(httpGetRequest);
            StringBuilder returnString = new StringBuilder();
            URLConnection apiConnection = apiURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

            while (in.readLine() != null)
            {
                returnString.append(in.readLine());
            }

            in.close();

            return returnString.toString();
        }
        else
        {
            System.out.println("API Is Unreachable.");
            return "";
        }
    }

    static public PlacesList parsePlacesResponse(String httpGetRequest) throws Exception
    {
        PlacesList places = new PlacesList();

        if (!httpGetRequest.equals("No Connection"))
        {
            String inputLine = new String();
            URL apiURL = new URL(httpGetRequest);
            URLConnection apiConnection = apiURL.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

            places.add(new Place());

            while ((inputLine = in.readLine()) != null)
            {
                if (inputLine.split(":").length > 1)
                {
                    handleInput(places, inputLine);
                }
            }

            in.close();
            cleanPlacesData(places);
        }
        else
        {
            places.setErrorMessage("API Is Unreachable");
            System.out.println(places.getErrorMessage());
        }

        return places;
    }

    static private void handleInput(PlacesList places, String inputLine)
    {
        String attribute = inputLine.split(":")[0].split("\"")[1];

        switch (attribute)
        {
            case "formatted_address":
                String address = inputLine.split(":")[1].split("\"")[1];
                assignAddressData(places, address);
                break;
            case "name":
                String name = inputLine.split(":")[1].split(",")[0].split("\"")[1];
                assignNameData(places, name);
                break;
            case "rating":
                String[] attributeComponents = inputLine.split(":")[1].split(",")[0].split(" ");
                boolean hasRating = attributeComponents.length >= 2;
                String rating = hasRating ? attributeComponents[1] : "";
                assignRatingData(places, rating);
                break;
            case "open_now":
                String[] hoursDataArray = inputLine.split("[\\p{Punct}\\s]+");
                String isOpenValue = hoursDataArray.length >= 3 ? hoursDataArray[3] : "";
                assignIsOpenDataData(places, isOpenValue);
                break;
            case "photo_reference":
                String[] photoDataArray = inputLine.split("\"");
                String photoReference = photoDataArray.length >= 3 ? photoDataArray[3] : "";
                assignImageData(places, photoReference);
                break;
            case "types":
                String[] typesArray = inputLine.split("[^a-zA-Z0-9_]");
                String category = parseCategoryInput(typesArray);
                assignCategoryData(places, category);
                break;
            case "error_message":
                String[] erorLine = inputLine.split(" : ");
                String message = erorLine[1].split("\"")[1].split(",")[0];
                places.setErrorMessage(message);
                break;
        }
    }

    static private void assignAddressData(PlacesList places, String address)
    {
        if (!places.get(places.size() - 1).getAddress().equals(""))
        {
            Place newPlace = new Place();
            newPlace.setAddress(address);
            places.add(newPlace);
        }
        else
        {
            places.get(places.size() - 1).setAddress(address);
        }
    }

    static private void assignNameData(PlacesList places, String name)
    {
        if (!places.get(places.size() - 1).getName().equals(""))
        {
            Place newPlace = new Place();
            newPlace.setName(name);
            places.add(newPlace);
        }
        else
        {
            places.get(places.size() - 1).setName(name);
        }
    }

    static private void assignIsOpenDataData(PlacesList places, String isOpenValue)
    {
        if (!places.get(places.size() - 1).getIsOpenNow().equals(""))
        {
            Place newPlace = new Place();
            newPlace.setIsOpenNow(isOpenValue);
            places.add(newPlace);
        }
        else
        {
            Place last = places.get(places.size() - 1);
            last.setIsOpenNow(isOpenValue);
        }
    }

    static private void assignImageData(PlacesList places, String photoReference)
    {
        if (!places.get(places.size() - 1).getImage().getPhotoReference().equals(""))
        {
            Place newPlace = new Place();
            newPlace.setImage(photoReference);
            places.add(newPlace);
        }
        else
        {
            Place last = places.get(places.size() - 1);
            last.setImage(photoReference);
        }
    }

    static private void assignRatingData(PlacesList places, String rating)
    {
        if (!places.get(places.size() - 1).getRating().equals(""))
        {
            Place newPlace = new Place();
            newPlace.setRating(rating);
            places.add(newPlace);
        }
        else
        {
            Place last = places.get(places.size() - 1);
            last.setRating(rating);
        }
    }

    static private String parseCategoryInput(String[] categoryInput)
    {
        for (String element : categoryInput)
        {
            if (!element.equals("") && !element.equals("types"))
            {
                return element;
            }
        }
        return "N/A";
    }

    static private void assignCategoryData(PlacesList places, String category)
    {
        if (!places.get(places.size() - 1).getCategory().equals(""))
        {
            Place newPlace = new Place();
            newPlace.setCategory(category);
            places.add(newPlace);
        }
        else
        {
            Place last = places.get(places.size() - 1);
            last.setCategory(category);
        }
    }

    private static boolean testInternetConnection(String apiPath)
    {
        try
        {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) new URL(apiPath).openConnection();
            connection.setRequestMethod("HEAD");

            boolean isGoodConnection = (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
            HttpURLConnection.setFollowRedirects(true);

            return isGoodConnection;
        } catch (Exception e)
        {
            return false;
        }
    }

    static private void cleanPlacesData(PlacesList places)
    {
        Place currentPlace = new Place();

        for (int index = 0; index < places.size(); index++)
        {
            currentPlace = places.get(index);
            if (currentPlace.getName().equals(""))
            {
                places.remove(index);
                index--;
            }
            else if (currentPlace.getAddress().equals(""))
            {
                places.remove(index);
                index--;
            }
        }

    }

}
