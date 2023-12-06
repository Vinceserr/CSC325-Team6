package com.mycompany.Controller;
import com.mycompany.Application.DataChangeListener;
import com.mycompany.Application.TaskScheduler;
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
import java.util.Map;

public class MainScheduleController implements DataChangeListener {
    @FXML private BorderPane bPane;

    @FXML private Button deleteTaskButton;
    @FXML private Button addTaskButton;

    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, String> tableColumn;

    private AddTaskController addTaskController;
    private CalendarController calendarController;
    private TaskScheduler taskScheduler;

    public void initialize() {

        taskScheduler = new TaskScheduler();
        taskScheduler.addListener(this);

        //load calendar in center of pane
        loadCalendarPage();

        // define the date into colum list
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //listen the TableView for which event is select
        taskTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) ->{
                    try {
                        showEventDetail(newVal);
                        deleteTaskButton.setOnAction(e->taskScheduler.removeTask(newVal));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        onDataChanged(null);
    }

    /**
     * HomeButton method
     * This method handles the Home Button
     *
     * @param event on mouse click
     */
    @FXML
    public void homeButton(ActionEvent event) {
        loadCalendarPage();
    }

    /**
     * addEventButton method
     * @param event on mouse click
     */
    @FXML
    public void addTaskButton(ActionEvent event) {
        loadAddTaskPage();
        // show all tasks
        onDataChanged(null);
    }

    // This method is a utility method for getting the added task controller
    // to be able to use its instance
    private void loadAddTaskPage() {
        try {
            FXMLLoader loader = createStage.loadFXML("addTask");
            Parent root = loader.load();
            bPane.setCenter(root);

            addTaskController = loader.getController();
            addTaskController.setTaskScheduler(taskScheduler);

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
            calendarController.setTaskScheduler(taskScheduler);

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
        loadAddTaskPage();
        addTaskController.showTask(task);
    }



    // set the taskScheduler and add listener in this class
    public void setTaskScheduler(TaskScheduler taskScheduler){
        this.taskScheduler = taskScheduler;
        this.taskScheduler.addListener(this);
    }

    //shows all the tasks on table view
    @Override
    public void onDataChanged(LocalDate date) {
        if(!taskScheduler.getTaskMap().isEmpty()){
            return;
        }
        List<Task> tasksList;
        //if date is null, shows all tasks
        // else show task for this date
        if(date == null){
            tasksList = taskScheduler.getAllTasks();
        }else{
            tasksList = taskScheduler.getTasksOnDate(date);
        }

        taskTableView.getItems().setAll(tasksList);
    }

}

