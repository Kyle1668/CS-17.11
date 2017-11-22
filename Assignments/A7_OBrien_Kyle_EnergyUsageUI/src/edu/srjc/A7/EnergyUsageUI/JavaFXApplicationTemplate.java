//        Kyle O'Brien
//        11-16-17
//        CS 17.11
//        A7 - Energy Usage UI
//
//        This program parses the electric, gas, and temperature data from three CSV files and aggregates them
//        to a single data structure called a HomeDataPoint. There the aggregated data is visualized using a
//        line chart. The user is able to cycle through the months in the UI. The program is divided into
//        the model (Data Points), view (UI), and controller.

package edu.srjc.A7.EnergyUsageUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    public static void main(String[] args)
    {
        launch(args);
//        FXMLDocumentController.initJanuaryVisualization();
    }
    
}
