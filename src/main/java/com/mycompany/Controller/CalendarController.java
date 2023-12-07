package com.mycompany.Controller;

import com.mycompany.Application.App;
import com.mycompany.Application.TaskScheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    @FXML private GridPane schedulePane;

    @FXML private Text month;
    @FXML private Text year;

    ZonedDateTime dateFocus;
    ZonedDateTime today;


    private TaskScheduler taskScheduler;
    private App app;

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

                                    try {
                                        handleDateClick(clickedDate);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        });
                    }
                }
                schedulePane.add(stackPane,j,i);
            }
        }
    }

    // when the data is click, it will show all tasks it have;
    private void handleDateClick(int clickedDate) throws Exception {
        LocalDate clickedLocalDate = LocalDate.from(dateFocus.withDayOfMonth(clickedDate));
        taskScheduler.notifyListeners(clickedLocalDate);

    }

    //drawing a dot in calendar if that date have task
    private void drawingADot(int clickedDate, StackPane stackPane) {
        if(taskScheduler == null){
            return;
        }
        LocalDate clickedLocalDate = LocalDate.from(dateFocus.withDayOfMonth(clickedDate));
        boolean onData = isExist(clickedLocalDate);
        if(onData) {
                Circle dot = new Circle(10, Color.RED);
                stackPane.getChildren().add(dot);
        }

    }

    // check if this data has tasks
    private boolean isExist(LocalDate selectDate){
        if(!taskScheduler.getTasksOnDate(selectDate).isEmpty()){
            return true;
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
    public void setApp(App app){
        this.app = app;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler){
        this.taskScheduler = taskScheduler;
        initialize(null, null);
    }


}
