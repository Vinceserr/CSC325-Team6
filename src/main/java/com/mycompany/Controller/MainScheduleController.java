package com.mycompany.Controller;
import com.mycompany.Application.App;
import com.mycompany.Application.DataChangeListener;
import com.mycompany.Application.TaskScheduler;
import com.mycompany.Model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.List;

public class MainScheduleController implements DataChangeListener {
    @FXML private BorderPane bPane;
    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, String> tableColumn;

    private AddTaskController addTaskController;
    private TaskScheduler taskScheduler;
    private App app;
    private LocalDate date;

    public void initializeAll() {

        loadPage("calendar");

        // define the date into colum list
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        setActionColumn(tableColumn);

        onDataChanged(null);
    }

    private <T> void loadPage(String fxml){
        if(app == null){
            return;
        }
        try {
            FXMLLoader loader = app.setLoader(fxml);
            Parent root = loader.getRoot();
            bPane.setCenter(root);

            T controller =  loader.getController();
            if(controller instanceof AddTaskController){

                addTaskController = (AddTaskController) controller;
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to load "+fxml+" FXML!");
        }
    }

    /**
     * HomeButton method
     * This method handles the Home Button
     *
     */
    @FXML
    public void homeButton() {
        taskTableView.getItems().clear();
        loadPage("calendar");
    }

    /**
     * addEventButton method
     *
     */
    @FXML
    public void addTaskButton() {
        loadPage("addTask");
        // show all tasks
        onDataChanged(null);
    }
    @FXML
    void UserPrefsButton() {
        taskTableView.getItems().clear();
        loadPage("UserPrefs");
    }

    @FXML
    public void signOutButton()throws Exception{
        app.setRoot("login");
    }




    // show the detail of event and able to edit it
    public void showEventDetail(Task task) {
        loadPage("addTask");
        addTaskController.showTask(task);
    }


    // set the taskScheduler and add listener in this class
    public void setTaskScheduler(TaskScheduler taskScheduler){
        this.taskScheduler = taskScheduler;
        this.taskScheduler.addListener(this);
    }

    public void setApp(App app){
        this.app = app;
        initializeAll();
    }

    //shows all the tasks on table view
    @Override
    public void onDataChanged(LocalDate date) {
        this.date = date;
        if(taskScheduler == null){
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

    // it set modify and delete button on tableview and set there actions
    private void setActionColumn(TableColumn<Task, String> tableColumn){
        tableColumn.setCellFactory(col -> new TableCell<>() {
            private final Button modify = new Button("+");
            private final Button delete = new Button("-");
            private final Label title = new Label();

            {

                modify.setOnAction(event -> {
                    Task task = getTableView().getItems().get(getIndex());
                    try {
                        showEventDetail(task);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                delete.setOnAction(event -> {
                    Task task = getTableView().getItems().get(getIndex());
                    taskScheduler.removeTask(date, task);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Task task = getTableView().getItems().get(getIndex());
                    title.setText(task.getTitle());
                    HBox hBox = new HBox(title, modify, delete);
                    HBox.setHgrow(title, Priority.ALWAYS);
                    title.setMaxWidth(Double.MAX_VALUE);
                    hBox.setSpacing(5);
                    setGraphic(hBox);
                }
            }
        });
    }

}

