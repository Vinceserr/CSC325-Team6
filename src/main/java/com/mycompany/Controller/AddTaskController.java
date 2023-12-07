/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.Controller;

import com.mycompany.Application.App;
import com.mycompany.Application.TaskScheduler;
import com.mycompany.Model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddTaskController {
    @FXML private TextField titleField;
    @FXML private ComboBox<String> startTime;
    @FXML private ComboBox<String> endTime;
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

    private TaskScheduler taskScheduler;
    private App app;

    public void initialize(){

        setTimeOfComboBox();
        repeatHandler();
        initTaskMessage();
    }

    /**
     * set the time of ComboBox
     */
    private void setTimeOfComboBox() {
        for (int i = 0; i < 60; i+=15) {
            if(i<10){
                startTime.getItems().add("12:0" + i + " AM");
                endTime.getItems().add("12:0" + i + " AM");
            }
            else {
                startTime.getItems().add("12:" + i + " AM");
                endTime.getItems().add("12:" + i + " AM");
            }
        }

        for (int i = 1; i < 12; i++) {
            for (int j = 0; j < 60; j += 15) {
                String hour = (i < 10) ? "0" + i : String.valueOf(i);
                String minute = (j < 10) ? "0" + j : String.valueOf(j);

                startTime.getItems().add(hour + ":" + minute + " AM");
                endTime.getItems().add(hour + ":" + minute + " AM");
            }
        }

        for (int i = 1; i < 12; i++) {
            for (int j = 0; j < 60; j += 15) {
                //adds a zero if the number is less than 10 in order to keep proper format
                String hour = (i < 10) ? "0" + i : String.valueOf(i);
                String minute = (j < 10) ? "0" + j : String.valueOf(j);
                startTime.getItems().add(hour + ":" + minute + " PM");
                endTime.getItems().add(hour + ":" + minute + " PM");
            }
        }
    }

    @FXML
    void saveButtonPress(ActionEvent event) throws Exception {
        Task task = setEvent();
        taskScheduler.addTask(task); // add task on map
        taskScheduler.notifyListeners(null);
        initTaskMessage();
    }

    /**
     * show task on addTask pane
     * @param task from which is user choose
     */
    public void showTask(Task task) {
        if(task != null) {
            titleField.setText(task.getTitle());
            startTime.getSelectionModel().select(task.getStartTime());
            endTime.getSelectionModel().select(task.getEndTime());
            startDay.setValue(task.getStartDay());
            endDay.setValue(task.getEndDay());
            repeat.setValue(task.getRepeat());

            // Set the statue of weeks buttons
            if (task.getRepeat().equals("Every Weeks")) {
                for (DayOfWeek week : task.getWeeks()) {

                    if (week == DayOfWeek.MONDAY) {
                        MON.setSelected(true);
                    }
                    if (week == DayOfWeek.TUESDAY) {
                        TUE.setSelected(true);
                    }
                    if (week == DayOfWeek.WEDNESDAY) {
                        WED.setSelected(true);
                    }
                    if (week == DayOfWeek.THURSDAY) {
                        THU.setSelected(true);
                    }
                    if (week == DayOfWeek.FRIDAY) {
                        FRI.setSelected(true);
                    }
                    if (week == DayOfWeek.SATURDAY) {
                        SAT.setSelected(true);
                    }
                    if (week == DayOfWeek.SUNDAY) {
                        SUN.setSelected(true);
                    }


                }
            } else {
                hideWeeksPane();
            }
        }

    }

    /**
     * Change the statue of component when user choose different repeat
     * @param newValue get the new item from repeat's listener
     */
    private void handleRepeatClick(String newValue){
        if("Single Task".equals(newValue)){

            currentDay.setDisable(false); // hide
            weeksPane.setDisable(true); // hide
            endDay.setDisable(true);

        } else if("Task Repeats Every Day".equals(newValue)){

            currentDay.setDisable(false); //show
            weeksPane.setDisable(true); //hide
            endDay.setDisable(false);
            hideWeeksPane();

        }else if ("Task Repeats on Certain Days".equals(newValue)) {

            currentDay.setDisable(false); // show
            weeksPane.setDisable(false); // show
            endDay.setDisable(false);

        }
    }

    /**
     * Encapsulate all task information
     * @return task to show task or add to list
     */
    public Task setEvent(){
        Task e = new Task();
        e.setTitle(titleField.getText());
        e.setStartTime(startTime.getValue());
        e.setEndTime(endTime.getValue());
        e.setStartDay(startDay.getValue());
        if(Objects.equals(repeat.getValue(), "Single Task")) {
            e.setEndDay(startDay.getValue());
        }
        else
            e.setEndDay(endDay.getValue());
        e.setRepeat(repeat.getValue());
        e.setWeeks(setWeeks());
        return e;
    }



    /**
     * hide all weeks button, without let user click
     */
    private void hideWeeksPane() {
        MON.setSelected(true);
        TUE.setSelected(true);
        WED.setSelected(true);
        THU.setSelected(true);
        FRI.setSelected(true);
        SAT.setSelected(true);
        SUN.setSelected(true);
    }

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

    /**
     * initialize state of all components
     */
    private void initTaskMessage(){
        titleField.clear();
        startTime.getSelectionModel().clearSelection();
        endTime.getSelectionModel().clearSelection();
        startDay.setValue(LocalDate.now());
        endDay.setValue(LocalDate.now());
        repeat.setValue("Single Task");

        hideWeeksPane();
    }

    public static String timeToLocalTime(String time){
        // Helper method to convert formatted time string to LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return String.valueOf(LocalTime.parse(time, formatter));
    }

    public static String convertTimeToString(LocalTime localTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return localTime.format(formatter);
    }

    /**
     * Handles the repeat button
     */
    public void repeatHandler(){
        repeat.getItems().addAll("Single Task","Task Repeats Every Day","Task Repeats on Certain Days");

        // listen the state of repeat, if user choose to repeat every week,
        // open the weeks pane what allow user to select the weeks
        repeat.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleRepeatClick(newValue));
    }


    public void setTaskScheduler(TaskScheduler taskScheduler){
        this.taskScheduler = taskScheduler;
    }

    public void setApp(App app){
        this.app = app;
    }

}






