/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.finalproject.obrien.kyle.quickcafe.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import edu.srjc.finalproject.obrien.kyle.quickcafe.models.APIRequest;
import edu.srjc.finalproject.obrien.kyle.quickcafe.models.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author 
 */
public class FXMLViewController implements Initializable
{
    
    @FXML
    private Label label;

    @FXML
    private TextField txtName;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception
    {
        System.out.println("You clicked me!");
        if (txtName.getText().length() > 0)
        {
            String test = txtName.getText();
            label.setText("Hello there " + txtName.getText() + "!");
            launchApp(test);
        }
        else
        {
            label.setText("Can't you follow directions?");
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    static private void launchApp(String target) throws Exception
    {
        final String request = APIRequest.formatAPIRequest(target.replace(" ", "+"));

        System.out.println("\n" + "HTTP API Request: " + request + "\n");

//        APIRequest.printAPIResponse(getAPIRequest(request));

        ArrayList<Place> places = APIRequest.parsePlacesResponse(request);

        for (Place place : places)
        {
            System.out.println(place.toString());
        }

    }
    
}
