package com.mycompany.Controller;
import com.mycompany.Application.*;
import com.mycompany.Application.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.mycompany.Application.AppConfig.getEmail;
import static com.mycompany.Controller.loginController.*;

public class MainScheduleController implements Initializable{
    @FXML private BorderPane bPane;
    @FXML private VBox vBox;
    @FXML private Text year;
    @FXML private Text month;
    @FXML private GridPane schedulePane;

    @FXML private Button deleteTaskButton;
    @FXML private Button addTaskButton;

    @FXML private TableColumn<Task, LocalDate> dateColumn;
    @FXML private TableView<Task> TaskTableView;
    @FXML private TableColumn<Task, String> TaskColumn;
    @FXML private TableColumn<Task, LocalTime> startColumn;
    @FXML private TableColumn<Task, LocalTime> endColumn;
    ZonedDateTime dateFocus;
    ZonedDateTime today;
    Text day;
    StackPane stackPane;
    ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
    ArrayList<Task> taskArrayList = AddTaskController.taskArrayList;
    AddTaskController addTaskController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Testing (Will print the current email)
        System.out.println(getEmail());
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();

        taskObservableList.clear();
        addTaskButton.setVisible(true);

        // define the date into colum list
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDay"));
        TaskColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

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
                stackPane = new StackPane();
                int calculatedDate = (j+1)+(7*i);
                if (calculatedDate > dateOffSet){
                    int currentDate = calculatedDate - dateOffSet;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));

                        stackPane.getChildren().add(date);
                        date.setFont(Font.font("Arial Rounded MT Bold"));

                        // Add click event listener for the stackPane
                        stackPane.setOnMouseClicked(event -> {
                            StackPane clickedStack = (StackPane) event.getSource();
                            Node node = clickedStack.getChildren().get(0);
                            day = (Text) node;

                            // add event to tableView list
                            int clickedDate = Integer.parseInt(day.getText());
                            handleDateClick(clickedDate);

                        });
                    }
                }
                schedulePane.add(stackPane,j,i);
            }
        }
    }

    private void handleDateClick(int clickedDate) {
        if (!taskArrayList.isEmpty()) {
            taskObservableList.clear();
            LocalDate clickedLocalDate = LocalDate.from(dateFocus.withDayOfMonth(clickedDate));
            addEventToList(clickedLocalDate, clickedLocalDate.getDayOfWeek());
        }
    }


    private void loadPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/addTask.fxml"));
            Parent root = loader.load();
            bPane.setCenter(root);

            //sent TableView to addEventController
            addTaskController = loader.getController();
            if(addTaskController != null){
                addTaskController.setTaskTableView(TaskTableView);
            }else{
                System.out.println("addTaskController is null");
            }

        }catch (Exception e){
            System.out.println("Failed to load FXML!");
        }
    }

    /**
     * Will change the interface to the userPreferences Menu
     * @param event this handles the Home Button
     */
    public void userPrefButton(ActionEvent event) {
        taskObservableList.clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/userPrefs.fxml"));
            Parent root = loader.load();
            bPane.setCenter(root);
        }catch (Exception e){
            System.out.println("Failed to load FXML!");
        }
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
        bPane.setCenter(vBox);
    }

    @FXML
    public void studyTopicButton(ActionEvent event) {
        taskObservableList.clear();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/studyTopics.fxml"));
            Parent root = loader.load();
            bPane.setCenter(root);
        } catch (Exception e) {
            System.out.println("Failed to load FXML!");

        }
    }

    /**
     * addEventButton method
     * @param event on mouse click
     */
    @FXML
    public void addTaskButton(ActionEvent event) throws Exception {
        loadPage();
        // show all event as user click
        taskObservableList.clear();
        if(!taskArrayList.isEmpty()) {
            taskObservableList.addAll(taskArrayList);
            TaskTableView.setItems(taskObservableList);
        }
    }


    @FXML
    public void signOutButton(ActionEvent event)throws Exception{
        createStage.close();
        createStage lg = new loginStage();
        lg.showStage();
    }

    // change the time formal from 4 OCTOBER 2023 to 4/10/2023
    // which able to compare with even's day data
    private LocalDate getCurrentDay() {
        String timeInput = day.getText() + " " + month.getText() + " " + year.getText();

        DateTimeFormatter inputFormat = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("d MMMM yyyy")
                .toFormatter();

        return LocalDate.parse(timeInput, inputFormat);

    }

    // add Task to list , not add to schedule
    public void addEventToList(LocalDate currentDay, DayOfWeek currentWeek){

        for(Task task : taskArrayList){
            if ("Task Repeats on Certain Days".equals(task.getRepeat())) {
                if (task.isActivityOnDate(currentDay) && task.isActivityOnWeek(currentWeek)) {
                    taskObservableList.add(task);
                }
            }else {
                if (task.isActivityOnDate(currentDay)) {
                    taskObservableList.add(task);
                }
            }
        }
        TaskTableView.setItems(taskObservableList);
    }
    private void createCalendar(){

    }

    // show the detail of event and able to edit it
    public void showEventDetail(Task task) throws Exception {
        try {
            loadPage();
            addTaskController.showEvent(task);
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

}


