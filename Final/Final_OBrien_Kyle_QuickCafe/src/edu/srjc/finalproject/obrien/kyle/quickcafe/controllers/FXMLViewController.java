/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.finalproject.obrien.kyle.quickcafe.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.srjc.finalproject.obrien.kyle.quickcafe.models.APIRequest;
import edu.srjc.finalproject.obrien.kyle.quickcafe.models.Place;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

/**
 * @author
 */
public class FXMLViewController implements Initializable
{

    @FXML
    private Label label;

    @FXML
    private TextField txtName;

    @FXML
    private GridPane gridPaneList;

    @FXML
    private ScrollPane scrollPaneArea;

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws Exception
    {
        if (txtName.getText().length() > 0)
        {
            String targetLocation = txtName.getText();
            ArrayList<Place> places = getPlacesFromAPI(targetLocation);
            Insets padding = new Insets(10, 10, 10, 10);

            initFirstGridRow(padding);

            for (int rowIndex = 0; rowIndex < places.size(); rowIndex++)
            {
                int gridColSize = gridPaneList.getColumnCount();
                double newScrollDimensions = scrollPaneArea.getVvalue() + (scrollPaneArea.getVvalue() * .5);

                scrollPaneArea.setVvalue(newScrollDimensions);
                gridPaneList.addRow(rowIndex + 1);

                for (int colIndex = 0; colIndex < gridColSize; colIndex++)
                {
                    initGridCell(places, rowIndex, colIndex, padding);
                }
            }

        }
        else
        {
            label.setText("Error");
        }
    }

    private void initFirstGridRow(Insets padding)
    {
        String[] titles = {"Name", "Address", "Type", "Google Rating", "Currently Open"};

        for (int i = 0; i < 5; i++)
        {
            Label nameLabel = new Label(titles[i]);
            nameLabel.setTextAlignment(TextAlignment.JUSTIFY);
            nameLabel.setWrapText(true);
            nameLabel.setPadding(padding);
            gridPaneList.add(nameLabel, i, 0);
        }

    }

    private void initGridCell(ArrayList<Place> places, int rowIndex, int colIndex, Insets padding)
    {
        Label newCellLabel = new Label();
        newCellLabel.setTextAlignment(TextAlignment.JUSTIFY);
        newCellLabel.setPadding(padding);
        newCellLabel.setWrapText(true);

        switch (colIndex)
        {
            case 0:
                newCellLabel.setText(places.get(rowIndex).getName());
                break;
            case 1:
                newCellLabel.setText(places.get(rowIndex).getAddress());
                break;
            case 2:
                newCellLabel.setText(places.get(rowIndex).getCategory());
                break;
            case 3:
                newCellLabel.setText(places.get(rowIndex).getRating());
                break;
            case 4:
                newCellLabel.setText(places.get(rowIndex).getIsOpenNow());
                break;
        }

        gridPaneList.add(newCellLabel, colIndex, rowIndex + 1);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    static private ArrayList<Place> getPlacesFromAPI(String target) throws Exception
    {
        final String request = APIRequest.formatAPIRequest(target);

        System.out.println("\n" + "HTTP API Request: " + request + "\n");

//        APIRequest.printAPIResponse(getAPIRequest(request));

        ArrayList<Place> places = APIRequest.parsePlacesResponse(request);

        for (Place place : places)
        {
            System.out.println(place.toString());
        }

        return places;

    }

}
