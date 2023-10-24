package com.mycompany.Controller;
import com.mycompany.Application.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScheduleController implements Initializable{
    @FXML private BorderPane bPane;
    @FXML private VBox vBox;
    @FXML private Text year;
    @FXML private Text month;
    @FXML private GridPane schedulePane;

    @FXML private TableView<Event> eventList;
    @FXML private TableColumn<Event, String> timeColumn;
    @FXML private TableColumn<Event, String> titleColumn;

    ZonedDateTime dateFocus;
    ZonedDateTime today;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();

        // define the date into colum list
        titleColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        timeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
                Event event = param.getValue();
                String timeString =event.getStartTime().toString()+" >> "
                        +event.getEndTime().toString();
                return new SimpleStringProperty(timeString);
            }
        });


        eventList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) ->{
                    try {
                        showEventDetail(newVal);
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

        int dateOffSet = ZonedDateTime.of(dateFocus.getYear(),dateFocus.getMonthValue(),1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();
        for (int i = 0 ; i < 6 ; i ++){
            for (int j = 0; j < 7 ; j ++){
                StackPane stackPane = new StackPane();
                int calculatedDate = (j+1)+(7*i);
                if (calculatedDate > dateOffSet){
                    int currentDate = calculatedDate - dateOffSet;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        //Button date = new Button(String.valueOf(currentDate));
                        stackPane.getChildren().add(date);
                        date.setFont(Font.font("Arial Rounded MT Bold"));
                    }
                }
                schedulePane.add(stackPane,j,i);
            }
        }
    }

    public GridPane getSchedulePane() {
        return schedulePane;
    }

    private void loadPage(String page) throws Exception {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/com/mycompany/Application/"+page + ".fxml"));
        bPane.setCenter(root);
    }

    /**
     * HomeButton method
     * This method handles the Home Button
     *
     * @param event
     */
    @FXML
    public void homeButton(ActionEvent event) {
        bPane.setCenter(vBox);
    }

    /**
     * addEventButton method
     * @param event
     * @throws Exception
     */
    @FXML
    public void addEventButton(ActionEvent event) throws Exception {
        loadPage("addEvent");
    }

    @FXML
    public void signOutButton(ActionEvent event)throws Exception{
        createStage.close();
        createStage lg = new loginStage();
        lg.showStage();
    }

    // add Event to list , not add to schedule
    public void addEventToList(Event e){
        AddEventController addevent = new AddEventController();
        ObservableList<Event> events = FXCollections.observableArrayList();
        if(e!=null)
            events.add(e);
        else
            System.out.println("null");
        eventList.setItems(events);
    }



    // show the detail of event and able to edit it
    public void showEventDetail(Event event) throws Exception {
        loadPage("addEvent");
        AddEventController addevent = new AddEventController();
       // addevent.showEvent(event);
    }

}


