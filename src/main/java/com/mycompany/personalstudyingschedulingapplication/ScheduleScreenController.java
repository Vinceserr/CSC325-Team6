/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.personalstudyingschedulingapplication;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author user
 */

public class ScheduleScreenController {

    @FXML
    private Button generateButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button SaveButton;

    @FXML
    private GridPane schedulePane;

    @FXML
    private Button addEventButton;

    @FXML
    void SaveButtonPress(ActionEvent event) {
            
    }

    @FXML
    void addEventButtonPress(ActionEvent event) throws IOException {
        App.setRoot("addEvent");
    }

    @FXML
    void ExitButtonPress(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void generateButtonPress(ActionEvent event) {

    }

}

