/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.personalstudyingschedulingapplication;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddEventController {

    @FXML
    private TextField startTimeField;

    @FXML
    private RadioButton MONButton;

    @FXML
    private RadioButton THUButton;

    @FXML
    private MenuButton ColorButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField endTimeField;

    @FXML
    private RadioButton SATButton;

    @FXML
    private Button createEvenButton;

    @FXML
    private RadioButton WEDButton;

    @FXML
    private RadioButton TUEButton;

    @FXML
    private RadioButton FRIButton;

    @FXML
    private RadioButton SUNButton;

    @FXML
    void MONButtonSelect(ActionEvent event) {

    }

    @FXML
    void TUEButtonSelect(ActionEvent event) {

    }

    @FXML
    void WEDButtonSelect(ActionEvent event) {

    }

    @FXML
    void THUButtonSelect(ActionEvent event) {

    }

    @FXML
    void FRIButtonSelect(ActionEvent event) {

    }

    @FXML
    void SATButtonSelect(ActionEvent event) {

    }

    @FXML
    void SUNButtonSelect(ActionEvent event) {
        
    }

    @FXML
    void ColorButtonSelect(ActionEvent event) {
        
    }

    @FXML
    void createEvenButtonPress(ActionEvent event) throws IOException {
        App.setRoot("scheduleScreen");
    }

}

