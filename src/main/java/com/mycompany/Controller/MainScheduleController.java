package com.mycompany.Controller;
import com.mycompany.Application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class MainScheduleController implements Initializable{
    @FXML
    private BorderPane bPane;

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private VBox vBox;
    @FXML
    private Text year;
    @FXML
    private Text month;
    @FXML
    private GridPane schedulePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
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

    //private Map<Integer, List<Event>> createCalendarMap(List<Event> events){
       // Map<Integer ,List<Event>> calendarMap = new HashMap<>();

       // for (Event e: events){
       //     int eventDate;
      //  }
   // }

    private static MainScheduleController instance;

    public MainScheduleController() {
        instance = this;
    }

    public static MainScheduleController getInstance() {
        return instance;
    }

    public void addEventToSchedule(Event event){
        String weeks = event.getDayOfWeeks();

        //get all weeks when user choose
        for(String str :weeks.split(" ")){
            // get column of grid pane which list the box under the week
            int column = event.weeksToRows(str);

            // get row of grid pane, if one box is exit than go to next box
            int row  = findNextAvailableRow(column);

            // if all row are occupied show the warring to user
            if(row == -1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add Failed");
                alert.setHeaderText("all rows are occupied");
                ButtonType bt = new ButtonType("Cancel");
                alert.getButtonTypes().setAll(bt);
                alert.showAndWait();
            }else {
                VBox evenBox = createEventBox(event);
                schedulePane.add(evenBox, column, row);
            }
        }
    }

    public int findNextAvailableRow(int column){
        int maxRows = 6;
        for(int row = 0; row<maxRows;row++){
            for(Node e:schedulePane.getChildren()){
                boolean isOccupied = false;
                // check have box in row,if not return this row
                if(Integer.valueOf(column).equals(GridPane.getColumnIndex(e))
                        && !Integer.valueOf(row).equals(GridPane.getRowIndex(e))){
                    isOccupied = true;
                    break;
                }
                if (!isOccupied) {
                    return row;
                }
            }
        }

        return -1;
    }
    // create the event box in VBox which assign the information of event
    // and return the VBox for add event to schedule
    public VBox createEventBox(Event event){
        VBox eventBox = new VBox();
        //set color of box
        eventBox.setStyle("-fx-background-color: " + event.getColor() + ";");

        //set data of event
        Label title = new Label(event.getTitle());
        Label time = new Label(event.getStartTime()+" - "+event.getEndTime());
        Label day = new Label(event.getStartDay()+"\n"+event.getEndDay());
        Label loop = new Label("Loop: "+event.isLoop());

        //add these labels to box
        eventBox.getChildren().add(title);
        eventBox.getChildren().add(time);
        eventBox.getChildren().add(day);
        eventBox.getChildren().add(loop);

        return eventBox;
    }


    public void setSchedulePane(GridPane schedulePane) {
        this.schedulePane = schedulePane;
    }
}


