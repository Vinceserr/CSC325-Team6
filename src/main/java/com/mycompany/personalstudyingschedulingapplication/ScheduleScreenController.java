/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package com.mycompany.personalstudyingschedulingapplication;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.TableView;

import javafx.scene.layout.GridPane;

public class ScheduleScreenController {

    @FXML
    private Button clearAllButton;

    @FXML
    private TableColumn<?, ?> eventLoop;

    @FXML
    private Button AddEventButton;

    @FXML
    private TableColumn<?, ?> eventTime;

    @FXML
    private TableColumn<?, ?> eventTiltle;

    @FXML
    private GridPane schedulePane;

    @FXML
    private TableView<?> EventTable;

    @FXML
    void AddEventButtonPress(ActionEvent event) throws IOException {
        App.setRoot("AddEvent");
    }

    @FXML
    void clearAllButtonPress(ActionEvent event) {

    }

}


