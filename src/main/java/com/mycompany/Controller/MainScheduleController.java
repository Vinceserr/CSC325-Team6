package com.mycompany.Controller;
import com.mycompany.Application.*;
import com.mycompany.Application.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainScheduleController{
    @FXML private BorderPane bPane;

    @FXML private Button deleteTaskButton;
    @FXML private Button addTaskButton;

    @FXML private TableView<Task> TaskTableView;
    @FXML private TableColumn<Task, String> TaskColumn;

    ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
    ArrayList<Task> taskArrayList = AddTaskController.taskArrayList;

    public void initialize() {
        //load calendar fxml to in center of pane
        try {
            Parent root = CreateStage.loadFXML("calendar");
            bPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        taskObservableList.clear();
        addTaskButton.setVisible(true);

        // define the date into colum list
        TaskColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //listen the TableView for which event is select
        TaskTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) ->{
                    try {
                        showEventDetail(newVal);
                        if(newVal == null){
                            System.out.println("newVal is null");
                        }
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
    public void homeButton(ActionEvent event) throws IOException {
        // clear the event in home page, wait for select day to show daily schedule
        taskObservableList.clear();
        CreateStage calendar = new CreateStage();
        Parent root = calendar.loadFXML("calendar");
        bPane.setCenter(root);
        addTaskButton.setVisible(true);

    }

    /**
     * addEventButton method
     * @param event on mouse click
     */
    @FXML
    public void addTaskButton(ActionEvent event) throws Exception {
        loadPage();
        // show all event as user click
        if(!taskArrayList.isEmpty()) {
            taskObservableList.addAll(taskArrayList);
            TaskTableView.setItems(taskObservableList);
        }
        addTaskButton.setVisible(false);

    }

    @FXML
    public void signOutButton(ActionEvent event)throws Exception{
        CreateStage.close();
       CreateStage.setRoot("login");
    }

    private void loadPage() {
        try {
            Parent root = CreateStage.loadFXML("addTask");
            bPane.setCenter(root);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to load addTask fxml");
        }
    }

    // showing the tasks on table view by click the date in calendar
    public void showTasksByDate(LocalDate currentDay, DayOfWeek currentWeek) {
        for (Task task : taskArrayList) {
            if (isOnArrayList(currentDay, currentWeek)) {
                taskObservableList.add(task);
            }
            TaskTableView.setItems(taskObservableList);
        }
    }

    // show the detail of event and able to edit it
    public void showEventDetail(Task task) throws Exception {

        try {
            loadPage();
            AddTaskController addTaskController = new AddTaskController();
            addTaskController.showTask(task);
        }catch (NullPointerException npe){
            npe.printStackTrace();
            System.out.println("Failed to load stored task.");
        }

    }

    public void deleteEvent(Task task){
        // remove from Array list
        if(!taskArrayList.isEmpty()) {
            taskArrayList.remove(task);}
        // remove from TableView
        if(!taskObservableList.isEmpty()){
            taskObservableList.remove(task);
        }
        System.out.println("delete: "+ task.getTitle());
    }

    public void addTaskToTableView(Task task){
        taskObservableList.add(task);
        TaskTableView.setItems(taskObservableList); // show on TableView
    }

    // a tool class  using to check the task is meets the condition
    public boolean isOnArrayList(LocalDate currentDay, DayOfWeek currentWeek){
        for(Task task: taskArrayList){
            // when the repeat is every week
            if ("Every Weeks".equals(task.getRepeat())) {
                if (task.isActivityOnDate(currentDay) && task.isActivityOnWeek(currentWeek)) {
                    return true;
                }
            }else { // when the repeat is not repeat or every day
                if (task.isActivityOnDate(currentDay)) {
                    return true;
                }
            }
        }
        return false;
    }

}


