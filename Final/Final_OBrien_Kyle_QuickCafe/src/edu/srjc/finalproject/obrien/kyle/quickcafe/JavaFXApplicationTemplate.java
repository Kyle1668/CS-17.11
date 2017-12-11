/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.finalproject.obrien.kyle.quickcafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.ArrayList;
import edu.srjc.finalproject.obrien.kyle.quickcafe.models.*;

public class JavaFXApplicationTemplate extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        launch(args);
        launchApp();
    }

    static private void launchApp() throws Exception
    {
        // EXAMPLE REQUEST FOR PETALUMA, CA
        //"https://maps.googleapis.com/maps/api/place/textsearch/
        // json?query=Cafe+coffee+near+Petaluma&key=AIzaSyC_KZyErDtZ42CuFscO2l5YseWaV8MCHrQ&sensor=false"

        final String request = getAPIRequest();

        System.out.println(request);

        APIRequest.printAPIResponse(request);

        ArrayList<Place> places = APIRequest.parsePlacesResponse(request);

        for (Place place : places)
        {
            System.out.println(place.toString());
        }

    }

    static private String getAPIRequest()
    {
        String request = "https://maps.googleapis.com/maps/api/place/textsearch/json?";
        String query = "query=Cafe+coffee+near+" + getInput();
        String apiKey = "&key=AIzaSyC_KZyErDtZ42CuFscO2l5YseWaV8MCHrQ&sensor=false";
        return request + query + apiKey;
    }

    static private String getInput()
    {
        System.out.print("\n" + "Enter Location: ");
        return new Scanner(System.in).nextLine().replace(" ", "+");
    }
    
}
