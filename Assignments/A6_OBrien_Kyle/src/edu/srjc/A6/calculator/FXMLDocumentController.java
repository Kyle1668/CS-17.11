/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.A6.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author 
 */
public class  FXMLDocumentController implements Initializable
{

    @FXML
    private TextField screen;

    private void sendInputToController()
    {
        // Stuff
    }

    @FXML
    private void handleEnterClicked(ActionEvent event)
    {
        if (screen.getText().length() == 0)
        {
            screen.setText("No Value");
        }
        else
        {
            sendInputToController();
        }
    }

    @FXML
    private void handleDivideClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + " / ");
    }

    @FXML
    private void handleMultiplyClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + " * ");
    }

    @FXML
    private void handleMinusClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + " - ");
    }

    @FXML
    private void handleAddClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + " + ");
    }

    @FXML
    private void handleLeftBraceClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + "(");
    }

    @FXML
    private void handleRightBraceClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + ")");
    }

    @FXML
    private void handleDeleteClicked(ActionEvent event)
    {
        int textLength = screen.getText().length();

        if (textLength != 0)
        {
            if (textLength == 1) {
                screen.clear();
            }
            else if (textLength == 2) {
                char newText = screen.getText().charAt(0);
                screen.setText(Character.toString(newText));
            }
            else {
                String newText = screen.getText().substring(0, textLength - 1);
                screen.setText(newText);
            }
        }

    }

    private void checkForEmptyMessage()
    {
        if (screen.getText().equals("No Value"))
        {
            screen.clear();
        }
    }


    @FXML
    private void handleNumberOneButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 1);
    }

    @FXML
    private void handleNumberTwoButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 2);
    }

    @FXML
    private void handleNumberThreeButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 3);
    }

    @FXML
    private void handleNumberFourButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 4);
    }

    @FXML
    private void handleNumberFiveButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 5);
    }

    @FXML
    private void handleNumberSixButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 6);
    }

    @FXML
    private void handleNumberSevenButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 7);
    }

    @FXML
    private void handleNumberEightButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 8);
    }

    @FXML
    private void handleNumberNineButtonClicked(ActionEvent event)
    {
        checkForEmptyMessage();
        final String currentInput = screen.getText();
        screen.setText(currentInput + 9);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
