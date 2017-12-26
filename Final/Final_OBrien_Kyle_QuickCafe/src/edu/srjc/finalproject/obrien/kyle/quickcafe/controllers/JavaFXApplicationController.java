// File Header
// Kyle O'Brien
// Email: kyledevinobrien1@gmail.com
// Date: 12/24/17
// QuickCafe: Course Final Project
// Course: CS 17.11

// File Summary: This file serves as the controller for the entire application. Here the "XML document.fxml"
// for the view is loaded, the scene is set, and the application is launched.

package edu.srjc.finalproject.obrien.kyle.quickcafe.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.ProgressIndicator;

public class JavaFXApplicationController extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("../views/FXMLDocument.fxml"));
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
    }
    
}
