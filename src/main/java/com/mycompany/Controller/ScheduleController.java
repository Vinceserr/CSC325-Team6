/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package com.mycompany.Controller;

import java.io.IOException;

import com.mycompany.Application.Event;
import com.mycompany.Application.addEventStage;
import com.mycompany.Application.createStage;
import com.mycompany.Application.loginStage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public class ScheduleController {

    @FXML
    private GridPane schedulePane;


    @FXML
    void addEventPress(ActionEvent event) throws Exception {

      createStage addEvent = new addEventStage();
      addEvent.showStage();

    }

    @FXML
    void savePress(ActionEvent event) {

    }

    @FXML
    void closePress(ActionEvent event) {

    }

    @FXML
    void aboutPress(ActionEvent event) {

    }

    private static ScheduleController instance;

    public ScheduleController() {
        instance = this;
    }

    public static ScheduleController getInstance() {
        return instance;
    }

    public void addEventToSchedule(Event event){
        String weeks = event.getDayOfWeeks();

        //get all weeks when user choose
        for(String str :weeks.split(" ")){
            // get column of grid pane which list the box under the week
            int column = event.weeksToRows(str);

            // get row of grid pane, if one box is exit than go to next box
            int row  = findNextAvailableRow(column);

            // if all row are occupied show the warring to user
            if(row == -1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add Failed");
                alert.setHeaderText("all rows are occupied");
                ButtonType bt = new ButtonType("Cancel");
                alert.getButtonTypes().setAll(bt);
                alert.showAndWait();
            }else {
                VBox evenBox = createEventBox(event);
                schedulePane.add(evenBox, column, row);
            }
        }
    }

    public int findNextAvailableRow(int column){
        int maxRows = 6;
        for(int row = 0; row<maxRows;row++){
            for(Node e:schedulePane.getChildren()){
                boolean isOccupied = false;
                // check have box in row,if not return this row
                if(Integer.valueOf(column).equals(GridPane.getColumnIndex(e))
                        && !Integer.valueOf(row).equals(GridPane.getRowIndex(e))){
                    isOccupied = true;
                    break;
                }
                if (!isOccupied) {
                    return row;
                }
            }
        }

        return -1;
    }
    // create the event box in VBox which assign the information of event
    // and return the VBox for add event to schedule
    public VBox createEventBox(Event event){
        VBox eventBox = new VBox();
        //set color of box
        eventBox.setStyle("-fx-background-color: " + event.getColor() + ";");

        //set data of event
        Label title = new Label(event.getTitle());
        Label time = new Label(event.getStartTime()+" - "+event.getEndTime());
        Label day = new Label(event.getStartDay()+"\n"+event.getEndDay());
        Label loop = new Label("Loop: "+event.isLoop());

        //add these labels to box
        eventBox.getChildren().add(title);
        eventBox.getChildren().add(time);
        eventBox.getChildren().add(day);
        eventBox.getChildren().add(loop);

        return eventBox;
    }


    public GridPane getSchedulePane() {
        return schedulePane;
    }

    public void setSchedulePane(GridPane schedulePane) {
        this.schedulePane = schedulePane;
    }
}


