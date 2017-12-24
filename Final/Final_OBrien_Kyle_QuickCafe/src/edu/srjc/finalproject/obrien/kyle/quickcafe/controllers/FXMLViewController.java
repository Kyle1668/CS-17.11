
package edu.srjc.finalproject.obrien.kyle.quickcafe.controllers;

import edu.srjc.finalproject.obrien.kyle.quickcafe.models.Place;
import edu.srjc.finalproject.obrien.kyle.quickcafe.models.PlacesList;
import edu.srjc.finalproject.obrien.kyle.quickcafe.models.APIRequest;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.application.*;
import javafx.geometry.Insets;

import java.util.ResourceBundle;

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
    private Label label;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField txtName;

    @FXML
    private GridPane gridPaneList;

    @FXML
    private ScrollPane scrollPaneArea;

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws Exception
    {
        Platform.runLater(() ->
        {
            if (txtName.getText().length() > 0)
            {
                statusLabel.setText("Searching");

                PlacesList places = new PlacesList();
                String targetLocation = txtName.getText();
                Insets padding = new Insets(10, 10, 10, 10);

                try
                {
                    places = getPlacesFromAPI(targetLocation);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                if (gridPaneList.getRowCount() > 1)
                {
                    // Clears each row excluding the first.
                    gridPaneList.getChildren().clear();
                }

                if (places != null && places.size() > 0)
                {
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
        final ImageView gridImageView = new ImageView();
        String imageSource = places.get(rowIndex).getImage().getPhotoURL();
        Image image1 = new Image(imageSource);

        gridImageView.setFitWidth(200);
        gridImageView.setFitHeight(200);
        gridImageView.setImage(image1);

        return gridImageView;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    static private PlacesList getPlacesFromAPI(String target) throws Exception
    {
        final String request = APIRequest.formatAPIRequest(target);

        System.out.println("\n" + "HTTP API Request: " + request + "\n");

        PlacesList places = APIRequest.parsePlacesResponse(request);

        for (Place place : places)
        {
            System.out.println(place.toString());
        }

        return places;

    }

}
