/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddEventController {
    @FXML private TextField titleField;

    @FXML private RadioButton MONButton;
    @FXML private RadioButton WEDButton;
    @FXML private RadioButton FRIButton;
    @FXML private RadioButton THUButton;
    @FXML private RadioButton SATButton;
    @FXML private RadioButton TUEButton;
    @FXML private RadioButton SUNButton;

    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;

    @FXML private DatePicker startDayField;
    @FXML private DatePicker endDayField;

    @FXML private ColorPicker ColorButton;
    @FXML private RadioButton loopButton;

    public void setEvent(){
        titleField.getText();
        startTimeField.getText();
        endTimeField.getText();
        startDayField.getEditor();
        endDayField.getEditor();
        ColorButton.getCustomColors();
    }

    @FXML
    void saveButtonPress(ActionEvent event) {

    }

    @FXML
    void deleteButtonPress(ActionEvent event) {

    }

}



