package com.mycompany.Controller;
import com.mycompany.Application.*;
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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScheduleController implements Initializable{
    @FXML private BorderPane bPane;
    @FXML private VBox vBox;
    @FXML private Text year;
    @FXML private Text month;
    @FXML private GridPane schedulePane;

    @FXML private Button deleteButton;

    @FXML private TableView<Event> eventList;
    @FXML private TableColumn<Event, String> eventColumn;

    ZonedDateTime dateFocus;
    ZonedDateTime today;
    Text day;
    ObservableList<Event> events = FXCollections.observableArrayList();
    ArrayList<Event> eventArrayList = AddEventController.eventArrayList;
    AddEventController addEventController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();

        // define the date into colum list
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //listen the TableView for which event is select
        eventList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) ->{
                    try {
                        showEventDetail(newVal);
                        deleteButton.setOnAction(e->deleteEvent(newVal));
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
                StackPane stackPane = new StackPane();
                int calculatedDate = (j+1)+(7*i);
                if (calculatedDate > dateOffSet){
                    int currentDate = calculatedDate - dateOffSet;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));

                        stackPane.getChildren().add(date);
                        date.setFont(Font.font("Arial Rounded MT Bold"));
                    }
                }

                // Add click event listener for the stackPane
                stackPane.setOnMouseClicked(event -> {
                    StackPane clickedStack = (StackPane) event.getSource();
                    Node node = clickedStack.getChildren().get(0);
                    day = (Text) node;

                    // add event to tableView list
                    int clickedDate = Integer.parseInt(day.getText());
                    handleDateClick(clickedDate);


                });
                schedulePane.add(stackPane,j,i);


            }
        }
    }

    private void handleDateClick(int clickedDate) {
        if (!eventArrayList.isEmpty()) {
            events.clear();
            LocalDate clickedLocalDate = LocalDate.from(dateFocus.withDayOfMonth(clickedDate));
            addEventToList(clickedLocalDate, clickedLocalDate.getDayOfWeek());
        }
    }


    private void loadPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/addEvent.fxml"));
        Parent root = loader.load();
        bPane.setCenter(root);

        //sent TableView to addEventController
        addEventController = loader.getController();
        addEventController.setEventList(eventList);
    }

    /**
     * HomeButton method
     * This method handles the Home Button
     *
     * @param event
     */
    @FXML
    public void homeButton(ActionEvent event) {
        // clear the event in home page, wait for select day to show daily schedule
        events.clear();
        bPane.setCenter(vBox);


    }

    /**
     * addEventButton method
     * @param event
     * @throws Exception
     */
    @FXML
    public void addEventButton(ActionEvent event) throws Exception {
        loadPage();
        // show all event as user created
        events.clear();
        if(!eventArrayList.isEmpty()) {
            events.addAll(eventArrayList);
            eventList.setItems(events);
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

    // add Event to list , not add to schedule
    public void addEventToList(LocalDate currentDay, DayOfWeek currentWeek){

        for(Event e :eventArrayList){
            if ("Every Weeks".equals(e.getRepeat())) {
                if (e.isActivityOnDate(currentDay) && e.isActivityOnWeek(currentWeek)) {
                    events.add(e);
                }
            }else {
                if (e.isActivityOnDate(currentDay)) {
                    events.add(e);
                }
            }
        }
        eventList.setItems(events);
    }

    // show the detail of event and able to edit it
    public void showEventDetail(Event event) throws Exception {
        loadPage();
        addEventController.showEvent(event);
    }

    public void deleteEvent(Event e){
        // remove from Array list
        if(!eventArrayList.isEmpty()) {eventArrayList.remove(e);}
        // remove from TableView
        if(!events.isEmpty()){events.remove(e);}
    }

}


