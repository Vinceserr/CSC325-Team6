package com.mycompany.Controller;

import com.mycompany.Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    @FXML private VBox vBox;
    @FXML private GridPane schedulePane;

    @FXML private Text month;
    @FXML private Text year;

    ZonedDateTime dateFocus;
    ZonedDateTime today;


    MainScheduleController mainScheduleController;
    ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
    //ArrayList<Task> taskArrayList = AddTaskController.taskArrayList;
    HashMap<LocalDate, List<Task>> taskHashMap = AddTaskController.taskHashMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    private void drawCalendar(){
        //display the year
        year.setText(String.valueOf(dateFocus.getYear()));
        //display the month
        month.setText(String.valueOf(dateFocus.getMonth()));

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }

        int dateOffSet = ZonedDateTime.of(dateFocus.getYear(),dateFocus.getMonthValue(),
                1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();
        for (int i = 0 ; i < 6 ; i ++){
            for (int j = 0; j < 7 ; j ++){
                StackPane stackPane = new StackPane();
                int calculatedDate = (j+1)+(7*i);
                if (calculatedDate > dateOffSet){
                    int currentDate = calculatedDate - dateOffSet;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        drawingADot(currentDate,stackPane);
                        stackPane.getChildren().add(date);
                        date.setFont(Font.font("Arial Rounded MT Bold"));

                        // Add click event listener for the stackPane
                        stackPane.setOnMouseClicked(event -> {
                            StackPane clickedStack = (StackPane) event.getSource();
                            for(Node node:clickedStack.getChildren())
                                if(node instanceof Text) {
                                    Text day = (Text) node;
                                    // add event to tableView list
                                    int clickedDate = Integer.parseInt(day.getText());
                                    handleDateClick(clickedDate);
                                }
                        });
                    }
                }
                schedulePane.add(stackPane,j,i);
            }
        }
    }

    // when the data is click, it will show all tasks it have;
    private void handleDateClick(int clickedDate) {
        taskObservableList.clear();
        LocalDate clickedLocalDate = LocalDate.from(dateFocus.withDayOfMonth(clickedDate));
        mainScheduleController.showTasksByDate(clickedLocalDate, clickedLocalDate.getDayOfWeek());

    }

    //drawing a dot in calendar if that date have task
    private void drawingADot(int clickedDate, StackPane stackPane) {
        LocalDate clickedLocalDate = LocalDate.from(dateFocus.withDayOfMonth(clickedDate));
        boolean onData = isOnHashMap(clickedLocalDate,clickedLocalDate.getDayOfWeek());
        if(onData) {
                Circle dot = new Circle(10, Color.RED);
                stackPane.getChildren().add(dot);
        }

    }
    // a tool class  using to check the task is meets the condition
    public boolean isOnHashMap(LocalDate currentDay, DayOfWeek currentWeek){
        for(List<Task> tasks: taskHashMap.values()){
            for(Task task: tasks) {
                // when the repeat is every week
                if ("Every Weeks".equals(task.getRepeat())) {
                    if (task.isActivityOnDate(currentDay) && task.isActivityOnWeek(currentWeek)) {
                        return true;
                    }
                } else { // when the repeat is not repeat or every day
                    if (task.isActivityOnDate(currentDay)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * monthBackButton:
     * This button moves one-month backwards
     * @param event
     */
    @FXML
    void monthBackButton(ActionEvent event){
        dateFocus = dateFocus.minusMonths(1);
        schedulePane.getChildren().clear();
        drawCalendar();
    }
    /**
     * forwardOneMonth:
     * This button moves one-month forward
     * @param event
     */
    @FXML
    void  forwardOneMonth(ActionEvent event){
        dateFocus = dateFocus.plusMonths(1);
        schedulePane.getChildren().clear();
        drawCalendar();
    }

    public void setMainScheduleController(MainScheduleController mainScheduleController) {
        this.mainScheduleController = mainScheduleController;
    }
}

