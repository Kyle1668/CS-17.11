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

import edu.srjc.A7.EnergyUsageUI.Models.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private LineChart<String, Number> dataVisualizationChart;

    public static final String temperatureFile = "KCASONOM43.csv";
    public static final String gasFile = "pge_gas_interval_data_2016-01-01_to_2016-03-01.csv";
    public static final String electricFile = "pge_electric_interval_data_2016-01-01_to_2016-02-28.csv";

    @FXML
    public void initJanuaryVisualization()
    {
        if (dataVisualizationChart.getData() != null)
        {
            dataVisualizationChart.getData().clear();
        }
        dataVisualizationChart.setTitle("Home Data: January");
        initVisualization("01");

    }

    @FXML
    public void initFebruaryVisualization()
    {
        if (dataVisualizationChart.getData() != null)
        {
            dataVisualizationChart.getData().clear();
        }
        dataVisualizationChart.setTitle("Home Data: February");
        initVisualization("02");
    }

    private void initVisualization(String targetMonth)
    {
        // Parse CSV files and init array lists.
        ArrayList<GasDataPoint> dailyGasUsage = CSVParseOperations.parseGasData(gasFile);
        ArrayList<ElectricDataPoint> dailyPowerUsage = CSVParseOperations.parseElectricData(electricFile);
        ArrayList<TemperatureDataPoint> dailyTemperatureData = CSVParseOperations.parseTemperatureData(temperatureFile);
        ArrayList<HomeDataPoint> dailyHomeData = getHomeData(dailyGasUsage, dailyPowerUsage, dailyTemperatureData);

        XYChart.Series electricUsageSeries = new XYChart.Series();
        XYChart.Series naturalGasUsageSeries = new XYChart.Series();
        XYChart.Series lowestTemperatureSeries = new XYChart.Series();
        XYChart.Series highestTemperatureSeries = new XYChart.Series();

        naturalGasUsageSeries.setName("Gas");
        electricUsageSeries.setName("Electric");
        lowestTemperatureSeries.setName("Lowest Temperature");
        highestTemperatureSeries.setName("Highest Temperature");

        for (HomeDataPoint homeData : dailyHomeData)
        {
            String currentMonth = homeData.getDate().split("-")[1];

            if (currentMonth.equals(targetMonth))
            {
                String gasLabel = String.format("Date: %s\nGas: %s\n", homeData.getDate(), homeData.getCumulativeGasUsage());
                String electricLabel = String.format("Date: %s\nGas: %s\n", homeData.getDate(), homeData.getCumulativeElectricUsage());
                String lowestTempLabel = String.format("Date: %s\nGas: %s\n", homeData.getDate(), homeData.getLowestTemperature());
                String highestTempLabel = String.format("Date: %s\nGas: %s\n", homeData.getDate(), homeData.getHighestTemperature());

                String currentDate = homeData.getDate();

                XYChart.Data<String, Double> gasVisualizationDataPoint = new XYChart.Data<>(currentDate, homeData.getCumulativeGasUsage());
                XYChart.Data<String, Double> electricVisualizationDataPoint = new XYChart.Data<>(currentDate, homeData.getCumulativeElectricUsage());
                XYChart.Data<String, Float> lowestTempVisualizationDataPoint = new XYChart.Data<>(currentDate, homeData.getLowestTemperature());
                XYChart.Data<String, Float> highestTempVisualizationDataPoint = new XYChart.Data<>(currentDate, homeData.getHighestTemperature());

                naturalGasUsageSeries.setNode(new DataPopup(gasLabel));
                electricUsageSeries.setNode(new DataPopup(electricLabel));
                lowestTemperatureSeries.setNode(new DataPopup(lowestTempLabel));
                highestTemperatureSeries.setNode(new DataPopup(highestTempLabel));

                naturalGasUsageSeries.getData().add(gasVisualizationDataPoint);
                electricUsageSeries.getData().add(electricVisualizationDataPoint);
                lowestTemperatureSeries.getData().add(lowestTempVisualizationDataPoint);
                highestTemperatureSeries.getData().add(highestTempVisualizationDataPoint);
            }

        }

        dataVisualizationChart.getData().add(naturalGasUsageSeries);
        dataVisualizationChart.getData().add(electricUsageSeries);
        dataVisualizationChart.getData().add(lowestTemperatureSeries);
        dataVisualizationChart.getData().add(highestTemperatureSeries);
    }

    private static boolean sameDate(String date1, String date2, String date3)
    {
        return date1.equals(date2) && date2.equals(date3);
    }

    private static String getCommonStartDate(String date1, String date2, String date3)
    {
        // If all the dates are the same the first one will be returned.
        String latestDate = date1;

        try {
            // Converts String date to epoc.
            Long epocDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1).getTime();
            Long epocDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2).getTime();
            Long epocDate3 = new SimpleDateFormat("yyyy-MM-dd").parse(date3).getTime();

            // Finds largest epoc time.
            Long largestEpocTime = Math.max(epocDate1, Math.max(epocDate2, epocDate3));

            // Converts the eopoc back to string date format.
            latestDate = new SimpleDateFormat("yyyy-MM-dd").format(largestEpocTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return latestDate;
    }

    // Removes all elements with dates before the desired start date.
    private static ArrayList<GasDataPoint> setCommonGasStartDate(ArrayList<GasDataPoint> dailyGasUsage, String startDate)
    {
        for (int i = 0; i < dailyGasUsage.size(); i++)
        {
            if (!dailyGasUsage.get(i).getDate().equals(startDate))
            {
                dailyGasUsage.remove(i);
                i--;
            }
            else
            {
                break;
            }
        }

        return dailyGasUsage;
    }

    // Removes all elements with dates before the desired start date.
    private static ArrayList<ElectricDataPoint> setCommonElectricStartDate(ArrayList<ElectricDataPoint> dailyElectricUsage, String startDate)
    {
        for (int i = 0; i < dailyElectricUsage.size(); i++)
        {
            if (!dailyElectricUsage.get(i).getDate().equals(startDate))
            {
                dailyElectricUsage.remove(i);
                i--;
            }
            else
            {
                break;
            }
        }

        return dailyElectricUsage;
    }

    // Removes all elements with dates before the desired start date.
    private static ArrayList<TemperatureDataPoint> setCommonTemperatureStartDate(ArrayList<TemperatureDataPoint> dailyTemperatureData, String startDate)
    {
        for (int i = 0; i < dailyTemperatureData.size(); i++)
        {
            if (!dailyTemperatureData.get(i).getDate().equals(startDate))
            {
                dailyTemperatureData.remove(i);
                i--;
            }
            else
            {
                break;
            }
        }

        return dailyTemperatureData;
    }

    // Gets an ArrayList of HomeDataPoints(s) by aggregating data from the three other data structures.
    private static ArrayList<HomeDataPoint> getHomeData(ArrayList<GasDataPoint> dailyGasUsage,
                                                        ArrayList<ElectricDataPoint> dailyElectricUsage,
                                                        ArrayList<TemperatureDataPoint> dailyTemperatureData)
    {
        ArrayList<HomeDataPoint> dailyHomeData = new ArrayList<>();

        int upperBound = Math.min(dailyGasUsage.size(), Math.min(dailyElectricUsage.size(), dailyTemperatureData.size()));
        int index = 0;

        String gasDate = dailyGasUsage.get(0).getDate();
        String electricDate = dailyElectricUsage.get(0).getDate();
        String temperatureDate = dailyTemperatureData.get(0).getDate();
        String startDate = getCommonStartDate(gasDate, electricDate, temperatureDate);

        dailyGasUsage = setCommonGasStartDate(dailyGasUsage, startDate);
        dailyElectricUsage = setCommonElectricStartDate(dailyElectricUsage, startDate);
        dailyTemperatureData = setCommonTemperatureStartDate(dailyTemperatureData, startDate);

        while (index < upperBound)
        {
            gasDate = dailyGasUsage.get(index).getDate();
            electricDate = dailyElectricUsage.get(index).getDate();
            temperatureDate = dailyTemperatureData.get(index).getDate();

            if (sameDate(gasDate, electricDate, temperatureDate))
            {
                float highestTemp = dailyTemperatureData.get(index).getHighestTemperature();
                float lowestTemp = dailyTemperatureData.get(index).getLowestTemperature();
                double electricUsage = dailyElectricUsage.get(index).getCumulativeUsage();
                double gasUsage = dailyGasUsage.get(index).getUsage();

                dailyHomeData.add(new HomeDataPoint(gasDate, lowestTemp, highestTemp, gasUsage, electricUsage));
            }

            index++;
        }

        return dailyHomeData;
    }

    static class DataPopup extends StackPane
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
        initJanuaryVisualization();
        // TODO
    }

}
