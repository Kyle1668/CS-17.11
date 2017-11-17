package edu.srjc.A7.EnergyUsageUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.swing.*;

/**
 *
 * @author
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private MenuItem mnuFileOpen;
    @FXML
    private MenuItem mnuFileExit;

    @FXML private LineChart<String, Number> bpChart;
    private CategoryAxis dateAxis = new CategoryAxis();
    private NumberAxis valueAxis = new NumberAxis();

    public static final String temperatureFile = "KCASONOM43.csv";
    public static final String gasFile = "pge_gas_interval_data_2016-01-01_to_2016-03-01.csv";
    public static final String electricFile = "pge_electric_interval_data_2016-01-01_to_2016-02-28.csv";


    class DataPopup extends StackPane
    {
        public DataPopup(String content)
        {
            setPrefSize(10,10);
            Label lblContent = createBpDataLabel(content);

            setOnMouseEntered(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    getChildren().setAll(lblContent);
                    setCursor(Cursor.NONE);
                    toFront();
                }
            });
            setOnMouseExited(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    getChildren().clear();
                }
            });
        }

        private Label createBpDataLabel(String content)
        {
            Label label = new Label(content);
            label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
            label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
            label.setTextFill(Color.FIREBRICK);
            label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            return label;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
