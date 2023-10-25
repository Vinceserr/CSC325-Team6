/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.Controller;

import com.mycompany.Application.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalTime;
import java.util.ArrayList;

public class AddEventController {
    @FXML private TextField titleField;
    @FXML private ComboBox<LocalTime> startTime;
    @FXML private ComboBox<LocalTime> endTime;
    @FXML private DatePicker startDay;
    @FXML private DatePicker endDay;
    @FXML private ChoiceBox<String> repeat;

    @FXML private CheckBox MON;
    @FXML private CheckBox TUE;
    @FXML private CheckBox WED;
    @FXML private CheckBox THU;
    @FXML private CheckBox FRI;
    @FXML private CheckBox SAT;
    @FXML private CheckBox SUN;

    @FXML private GridPane weeksPane;

    public static ArrayList<Event> eventArrayList = new ArrayList<>();
    private TableView<Event> eventList;
    public ObservableList<Event> events = FXCollections.observableArrayList();

    public void initialize(){
        //set the start time
        for(int hour =0; hour<24; hour++){
            for( int minute =0; minute < 60; minute+=10){
                startTime.getItems().add(LocalTime.of(hour,minute));
            }
        }
        //set the end time
        for(int hour =0; hour<24; hour++){
            for( int minute =0; minute < 60; minute+=10){
                endTime.getItems().add(LocalTime.of(hour,minute));
            }
        }
        //set the repeat select items
        repeat.getItems().addAll("No repeat","Every Day","Every Weeks",
                                    "Every Month","Every Year");
        //set the initial repeat as no repeat
        repeat.setValue("No repeat");

        // listen the state of repeat, if user choose to repeat every week,
        // open the weeks pane what allow user to select the weeks
        repeat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Every Weeks".equals(newValue)) {
                weeksPane.setDisable(false);
            } else {
                weeksPane.setDisable(true);
                MON.setSelected(false);
                TUE.setSelected(false);
                WED.setSelected(false);
                THU.setSelected(false);
                FRI.setSelected(false);
                SAT.setSelected(false);
                SUN.setSelected(false);
            }
        });


    }
    public Event setEvent(){
        Event e = new Event();
        e.setTitle(titleField.getText());
        e.setStartTime(startTime.getValue());
        e.setEndTime(endTime.getValue());
        e.setStartDay(startDay.getValue());
        e.setEndDay(endDay.getValue());
        e.setRepeat(repeat.getValue());
        e.setWeeks(setWeeks());
        return e;
    }

    public void setEventList(TableView<Event>eventList){
        this.eventList = eventList;
    }

    @FXML
    void saveButtonPress(ActionEvent event) {
        Event e =setEvent();
        eventArrayList.add(e); // save to Array list

        events.add(e);
        eventList.setItems(events); // show on TableView
    }

    // set which weeks is select
    private String setWeeks(){
        StringBuilder weeks = new StringBuilder();

        if(SUN.isSelected()){weeks.append("SUN ");}
        if(MON.isSelected()){weeks.append("MON ");}
        if(TUE.isSelected()){weeks.append("TUE ");}
        if(WED.isSelected()){weeks.append("WED ");}
        if(THU.isSelected()){weeks.append("THU ");}
        if(FRI.isSelected()){weeks.append("FRI ");}
        if(SAT.isSelected()){weeks.append("SAT ");}

        return weeks.toString().trim();
    }

    public void showEvent(Event event) {
        titleField.setText(event.getTitle());
        startTime.getSelectionModel().select(event.getStartTime());
        endTime.getSelectionModel().select(event.getEndTime());
        startDay.setValue(event.getStartDay());
        endDay.setValue(event.getEndDay());
        repeat.setValue(event.getRepeat());
        // Set the statue of weeks
        for (String week : event.getWeeks().split(" ")) {
            if (week.equals("MON")) {MON.setSelected(true);}
            if (week.equals("TUE")) {TUE.setSelected(true);}
            if (week.equals("WED")) {WED.setSelected(true);}
            if (week.equals("THU")) {THU.setSelected(true);}
            if (week.equals("FRI")) {FRI.setSelected(true);}
            if (week.equals("SAT")) {SAT.setSelected(true);}
            if (week.equals("SUN")) {SUN.setSelected(true);}
       }
    }




}







