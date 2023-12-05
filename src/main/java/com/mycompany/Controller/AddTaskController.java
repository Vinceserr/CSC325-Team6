/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.Controller;

import com.mycompany.Model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddTaskController{
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

    MainScheduleController mainScheduleController;
    static HashMap<LocalDate,List<Task>> taskHashMap = new HashMap<>();

    public void initialize(){

        setTimeOfComboBox();
        //set the repeat select items
        repeat.getItems().addAll("No repeat","Every Day","Every Weeks");

        // listen the state of repeat, if user choose to repeat every week,
        // open the weeks pane what allow user to select the weeks
        repeat.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handleRepeatClick(newValue));

        initTaskMessage();
    }

    /**
     * set the time of ComboBox
     */
    private void setTimeOfComboBox(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        for(int hour =0; hour<24; hour++){
            for( int minute =0; minute < 60; minute+=10){
                LocalTime time = LocalTime.of(hour, minute);
                startTime.getItems().add(time);
                endTime.getItems().add(time);
            }
        }

        StringConverter<LocalTime> converter = new StringConverter<LocalTime>() {
            @Override
            public String toString(LocalTime object) {
                if (object != null) {
                    return formatter.format(object);
                } else {
                    return "";
                }
            }

            @Override
            public LocalTime fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalTime.parse(string, formatter);
                } else {
                    return null;
                }
            }
        };

        startTime.setConverter(converter);
        endTime.setConverter(converter);
    }

    @FXML
    void saveButtonPress(ActionEvent event) {
        Task task = setEvent();
        //taskArrayList.add(task); // save to Array list
        LocalDate date = task.getStartDay();
        while(!task.getStartDay().isAfter(task.getEndDay())){
            taskHashMap.computeIfAbsent(date, k -> new ArrayList<>()).add(task);
            date = date.plusDays(1);
        }

        try{
            mainScheduleController.addTaskToTableView(task); // show on table view
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("mainScheduleController is null");
        }
        initTaskMessage();

    }


    /**
     * Change the statue of component when user choose different repeat
     * @param newValue get the new item from repeat's listener
     */
    private void handleRepeatClick(String newValue){
        if("No repeat".equals(newValue)){

            currentDay.setDisable(true); // hide
            weeksPane.setDisable(true); // hide

            // set as today's date
            startDay.setValue(LocalDate.now());
            endDay.setValue(LocalDate.now());

        } else if("Every Day".equals(newValue)){

            currentDay.setDisable(false); //show
            weeksPane.setDisable(true); //hide
            hideWeeksPane();

        }else if ("Every Weeks".equals(newValue)) {

            currentDay.setDisable(false); // show
            weeksPane.setDisable(false); // show

        }
    }

    /**
     * Encapsulate all task information
     * @return task to show task or add to list
     */
    public Task setEvent(){
        Task task = new Task();
        task.setTitle(titleField.getText());
        task.setStartTime(startTime.getValue());
        task.setEndTime(endTime.getValue());
        task.setStartDay(startDay.getValue());
        task.setEndDay(endDay.getValue());
        task.setRepeat(repeat.getValue());
        task.setWeeks(setWeeks());
        return task;
    }


    // set which weeks is select
    private ArrayList<DayOfWeek> setWeeks(){
        ArrayList<DayOfWeek> weeks = new ArrayList<>();

        if(SUN.isSelected()){weeks.add(DayOfWeek.SUNDAY);}
        if(MON.isSelected()){weeks.add(DayOfWeek.MONDAY);}
        if(TUE.isSelected()){weeks.add(DayOfWeek.TUESDAY);}
        if(WED.isSelected()){weeks.add(DayOfWeek.WEDNESDAY);}
        if(THU.isSelected()){weeks.add(DayOfWeek.THURSDAY);}
        if(FRI.isSelected()){weeks.add(DayOfWeek.FRIDAY);}
        if(SAT.isSelected()){weeks.add(DayOfWeek.SATURDAY);}

        return weeks;
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
     * hide all weeks button, without let user click
     */
    private void hideWeeksPane() {
        MON.setSelected(false);
        TUE.setSelected(false);
        WED.setSelected(false);
        THU.setSelected(false);
        FRI.setSelected(false);
        SAT.setSelected(false);
        SUN.setSelected(false);
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
        repeat.setValue("No repeat");

        hideWeeksPane();
    }

    public void setMainScheduleController(MainScheduleController mainScheduleController) {
        this.mainScheduleController = mainScheduleController;
    }
}






