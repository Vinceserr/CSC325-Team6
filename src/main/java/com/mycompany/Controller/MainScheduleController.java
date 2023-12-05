package com.mycompany.Controller;
import com.mycompany.Model.Task;
import com.mycompany.Stage.createStage;
import com.mycompany.Stage.loginStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainScheduleController{
    @FXML private BorderPane bPane;

    @FXML private Button deleteTaskButton;
    @FXML private Button addTaskButton;

    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, String> tableColumn;

    AddTaskController addTaskController;
    CalendarController calendarController;
    HashMap<LocalDate, List<Task>> taskHashMap = AddTaskController.taskHashMap;
    ObservableList<Task> taskObservableList = FXCollections.observableArrayList();

    public void initialize() {

        //load calendar in center of pane
        loadCalendarPage();

        taskObservableList.clear();
        addTaskButton.setVisible(true);

        // define the date into colum list
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //listen the TableView for which event is select
        taskTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) ->{
                    try {
                        showEventDetail(newVal);
                        deleteTaskButton.setOnAction(e->deleteEvent(newVal));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

    }

    /**
     * HomeButton method
     * This method handles the Home Button
     *
     * @param event on mouse click
     */
    @FXML
    public void homeButton(ActionEvent event) {
        // clear the event in home page, wait for select day to show daily schedule
        taskObservableList.clear();
        loadCalendarPage();
        addTaskButton.setVisible(true);

    }

    /**
     * addEventButton method
     * @param event on mouse click
     */
    @FXML
    public void addTaskButton(ActionEvent event) {
        loadAddTaskPage();
        // show all event as user click
        if (!taskHashMap.isEmpty()) {
            taskObservableList.clear();
            for (List<Task> tasks : taskHashMap.values()) {
                for (Task task : tasks) {
                    taskObservableList.addAll(task);
                }
                taskTableView.setItems(taskObservableList);
            }
            addTaskButton.setVisible(false);

        }
    }

    // This method is a utility method for getting the added task controller
    // to be able to use its instance
    private void loadAddTaskPage() {
        try {
            FXMLLoader loader = createStage.loadFXML("addTask");
            Parent root = loader.load();
            bPane.setCenter(root);

            addTaskController = loader.getController();
            addTaskController.setMainScheduleController(this);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to load addTask FXML!");
        }
    }

    private void loadCalendarPage(){
        try {
            FXMLLoader loader = createStage.loadFXML("calendar");
            Parent root = loader.load();
            bPane.setCenter(root);

            calendarController = loader.getController();
            calendarController.setMainScheduleController(this);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to load calendar FXML!");
        }
    }
    private void loadUserPrefsPage(){
        try {
            FXMLLoader loader = createStage.loadFXML("UserPrefs");
            Parent root = loader.load();
            bPane.setCenter(root);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to load UserPrefs FXML!");
        }
    }

    @FXML
    public void signOutButton(ActionEvent event)throws Exception{
        createStage.close();
        createStage login = new loginStage();
        login.showStage();

    }

    @FXML
    void UserPrefsButton(ActionEvent event) {
        loadUserPrefsPage();
    }


    // show the detail of event and able to edit it
    public void showEventDetail(Task task) {
        addTaskButton.setVisible(false);
        loadAddTaskPage();
        addTaskController.showTask(task);
    }

    public void deleteEvent(Task task){
         if(!taskHashMap.isEmpty()){
             taskHashMap.remove(task);
         }
        // remove from TableView
        if(!taskObservableList.isEmpty()){
            taskObservableList.remove(task);
        }
        System.out.println("delete: "+ task.getTitle());
    }

    public void addTaskToTableView(Task task){
        taskObservableList.add(task);
        try {
            taskTableView.setItems(taskObservableList); // show on TableView
        }catch (NullPointerException npe){
            npe.printStackTrace();
            System.out.println("taskTableView is null");
        }

    }

    // showing the tasks on table view by click the date in calendar
    public void showTasksByDate(LocalDate currentDay, DayOfWeek currentWeek) {
        for(List<Task> tasks: taskHashMap.values()) {
            for (Task task : tasks) {
                if (isOnHashMap(currentDay, currentWeek)) {
                    taskObservableList.add(task);
                    taskTableView.setItems(taskObservableList);
                }
            }
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

}

