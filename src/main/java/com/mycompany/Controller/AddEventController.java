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
import javafx.scene.layout.HBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AddEventController {
    @FXML private TextField titleField;
    @FXML private ComboBox<LocalTime> startTime;
    @FXML private ComboBox<LocalTime> endTime;
    @FXML private DatePicker startDay;
    @FXML private DatePicker endDay;
    @FXML private ChoiceBox<String> repeat;
    @FXML private HBox currentDay;

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
        currentDay.setDisable(true);
        LocalDate today = LocalDate.now();
        startDay.setValue(today);
        endDay.setValue(today);

        setTimeOfComboBox();

        //set the repeat select items
        repeat.getItems().addAll("No repeat","Every Day","Every Weeks");
        //set the initial repeat as no repeat
        repeat.setValue("No repeat");

        // listen the state of repeat, if user choose to repeat every week,
        // open the weeks pane what allow user to select the weeks
        repeat.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            handleRepeatClick(newValue,today);
        });


    }
    private void setTimeOfComboBox(){
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
    }

    private void handleRepeatClick(String newValue,LocalDate today){
        if("No repeat".equals(newValue)){

            currentDay.setDisable(true);
            weeksPane.setDisable(true);

            startDay.setValue(today);
            endDay.setValue(today);

        }else if ("Every Weeks".equals(newValue)) {

            currentDay.setDisable(false);
            weeksPane.setDisable(false);

        } else if("Every Day".equals(newValue)){

            currentDay.setDisable(false);
            weeksPane.setDisable(true);

            MON.setSelected(false);
            TUE.setSelected(false);
            WED.setSelected(false);
            THU.setSelected(false);
            FRI.setSelected(false);
            SAT.setSelected(false);
            SUN.setSelected(false);
        }
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

    @FXML
    void saveButtonPress(ActionEvent event) {
        Event e =setEvent();
        eventArrayList.add(e); // save to Array list

        events.add(e);
        eventList.setItems(events); // show on TableView
    }

    // set which weeks is select
    private DayOfWeek[] setWeeks(){
        DayOfWeek[] weeks = new DayOfWeek[7];

        if(SUN.isSelected()){weeks[0] = DayOfWeek.SUNDAY;}
        if(MON.isSelected()){weeks[1] = DayOfWeek.MONDAY;}
        if(TUE.isSelected()){weeks[2] = DayOfWeek.TUESDAY;}
        if(WED.isSelected()){weeks[3] = DayOfWeek.WEDNESDAY;}
        if(THU.isSelected()){weeks[4] = DayOfWeek.THURSDAY;}
        if(FRI.isSelected()){weeks[5] = DayOfWeek.FRIDAY;}
        if(SAT.isSelected()){weeks[6] = DayOfWeek.SATURDAY;}

        return weeks;
    }

    public void showEvent(Event event) {
        titleField.setText(event.getTitle());
        startTime.getSelectionModel().select(event.getStartTime());
        endTime.getSelectionModel().select(event.getEndTime());
        startDay.setValue(event.getStartDay());
        endDay.setValue(event.getEndDay());
        repeat.setValue(event.getRepeat());
        // Set the statue of weeks
        if(event.getRepeat().equals("Every Weeks")) {
            for (DayOfWeek week : event.getWeeks()) {
                if (week.equals(DayOfWeek.MONDAY)) {
                    MON.setSelected(true);
                }
                if (week.equals(DayOfWeek.TUESDAY)) {
                    TUE.setSelected(true);
                }
                if (week.equals(DayOfWeek.WEDNESDAY)) {
                    WED.setSelected(true);
                }
                if (week.equals(DayOfWeek.THURSDAY)) {
                    THU.setSelected(true);
                }
                if (week.equals(DayOfWeek.FRIDAY)) {
                    FRI.setSelected(true);
                }
                if (week.equals(DayOfWeek.SATURDAY)) {
                    SAT.setSelected(true);
                }
                if (week.equals(DayOfWeek.SUNDAY)) {
                    SUN.setSelected(true);
                }
            }
        }else{
            MON.setSelected(false);
            TUE.setSelected(false);
            WED.setSelected(false);
            THU.setSelected(false);
            FRI.setSelected(false);
            SAT.setSelected(false);
            SUN.setSelected(false);
        }

    }

    public void setEventList(TableView<Event>eventList){
        this.eventList = eventList;
    }


}







