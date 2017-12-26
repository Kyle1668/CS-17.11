// File Header
// Kyle O'Brien
// Email: kyledevinobrien1@gmail.com
// Date: 12/24/17
// QuickCafe: Course Final Project
// Course: CS 17.11

// File Summary: This file handles the logic that controls and manipulates the view.
// The "handleSearchButtonAction" method is fired when the search button is clicked
// in the view. If the search text box is not empty, a request is made to the Google
// Places API via static methods in the "API request" class. The result of which
// is used to initialize a grid pane JavaFX node in the view. This is done
// asynchronously so that the view does not freeze while the background
// process is underway.

package edu.srjc.finalproject.obrien.kyle.quickcafe.controllers;

import edu.srjc.finalproject.obrien.kyle.quickcafe.models.Place;
import edu.srjc.finalproject.obrien.kyle.quickcafe.models.PlacesList;
import edu.srjc.finalproject.obrien.kyle.quickcafe.models.APIRequest;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.TextAlignment;

/**
 * @author
 */
public class FXMLViewController implements Initializable
{

    @FXML
    private Label statusLabel;

    @FXML
    private TextField locationSearchInput;

    @FXML
    private GridPane gridPaneList;

    @FXML
    private ScrollPane scrollPaneArea;

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws Exception
    {
        // Logic in Platform.runLater blocks are executed asynchronously.
        Platform.runLater(() ->
        {
            if (locationSearchInput.getText().length() > 0)
            {
                PlacesList places = new PlacesList();
                String targetLocation = locationSearchInput.getText();
                Insets padding = new Insets(10, 10, 10, 10);

                try
                {
                    places = getPlacesFromAPI(targetLocation);
                    boolean unreachableAPI = places.getErrorMessage().equals("API Is Unreachable");
                    String searchStatus = unreachableAPI ? places.getErrorMessage() : "Searching";

                    statusLabel.setText(searchStatus);

                    if (!unreachableAPI)
                    {
                        initGridPane(places, padding);
                    }

                } catch (Exception e)
                {
                    e.printStackTrace();
                    statusLabel.setText("API Error");
                }

            }
        });
    }

    private void initFirstGridRow(Insets padding)
    {
        String[] titles = {"Name", "Address", "Currently Open", "Google Rating", "Image"};

        for (int i = 0; i < 5; i++)
        {
            Label nameLabel = new Label(titles[i]);
            nameLabel.setTextAlignment(TextAlignment.JUSTIFY);
            nameLabel.setWrapText(true);
            nameLabel.setPadding(padding);
            gridPaneList.add(nameLabel, i, 0);
        }
    }

    private void initGridPane(PlacesList places, Insets padding)
    {
        if (gridPaneList.getRowCount() > 1)
        {
            gridPaneList.getChildren().clear();
        }

        if (places.size() > 0)
        {
            // Populates the grid pane by adding a row and increases the scroll area for each element in the PlacesList.
            // For each row, each column is initialized with it's corresponding data from the current element in places.

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
            boolean noAPIErrors = places.getErrorMessage().equals("");
            String status = noAPIErrors ? "0 Results Found" : places.getErrorMessage();
            statusLabel.setText(status);
        }
    }

    private void initGridCell(PlacesList places, int rowIndex, int colIndex, Insets padding)
    {
        Label newCellLabel = new Label();
        newCellLabel.setTextAlignment(TextAlignment.JUSTIFY);
        newCellLabel.setPadding(padding);
        newCellLabel.setWrapText(true);

        Platform.runLater(() ->
        {
            switch (colIndex)
            {
                case 0:
                    newCellLabel.setText(places.get(rowIndex).getName());
                    break;
                case 1:
                    newCellLabel.setText(places.get(rowIndex).getAddress());
                    break;
                case 2:
                    newCellLabel.setText(places.get(rowIndex).getIsOpenNow());
                    break;
                case 3:
                    newCellLabel.setText(places.get(rowIndex).getRating());
                    break;
                case 4:
                    final ImageView gridImageView = initImage(places, rowIndex);
                    gridPaneList.add(gridImageView, colIndex, rowIndex + 1);
                    break;
            }

            gridPaneList.add(newCellLabel, colIndex, rowIndex + 1);

            boolean fullyLoaded = (rowIndex - 1) * colIndex == places.size();

            if (fullyLoaded)
            {
                initFirstGridRow(padding);
                statusLabel.setText(places.size() + " Results Found");
            }

        });
    }

    private ImageView initImage(PlacesList places, int rowIndex)
    {
        String imageSource = places.get(rowIndex).getImage().getPhotoURL();
        final ImageView gridImageView = new ImageView();
        Image image1 = new Image(imageSource);

        gridImageView.setFitWidth(200);
        gridImageView.setFitHeight(200);
        gridImageView.setImage(image1);

        return gridImageView;
    }

    private PlacesList getPlacesFromAPI(String target) throws Exception
    {
        final String request = APIRequest.formatAPIRequest(target);
        PlacesList places = APIRequest.parsePlacesResponse(request);

        System.out.println("\n" + "HTTP API Request: " + request + "\n");

        for (Place place : places)
        {
            System.out.println(place.toString());
        }

        return places;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
