package com.mycompany.Controller;

import com.mycompany.Application.studyTopic;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class studyTopicsController {
    @FXML
    private TextField studyTopic1;
    @FXML
    private TextField studyTopic2;
    @FXML
    private TextField studyTopic3;
    @FXML
    private TextField studyTopic4;
    @FXML
    private TextField studyTopic5;
    @FXML
    private TextField studyTopic6;
    @FXML
    private ComboBox<String> duration1;
    @FXML
    private ComboBox<String> duration2;
    @FXML
    private ComboBox<String> duration3;
    @FXML
    private ComboBox<String> duration4;
    @FXML
    private ComboBox<String> duration5;
    @FXML
    private ComboBox<String> duration6;
    ArrayList<studyTopic> topics;
    ArrayList<Duration> durations;
    public void initialize(){
        setTimeofDuration();
    }
    private void setTimeofDuration() {
        for (int hours = 0; hours < 40; hours++) {
            for (int minutes = 0; minutes < 60; minutes += 30) {
                Duration duration = Duration.ofMinutes((hours * 60) + minutes);
                String formattedDuration = formatDuration(duration);
                duration1.getItems().add(formattedDuration);
                duration2.getItems().add(formattedDuration);
                duration3.getItems().add(formattedDuration);
                duration4.getItems().add(formattedDuration);
                duration5.getItems().add(formattedDuration);
                duration6.getItems().add(formattedDuration);
            }
        }
    }
    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();

        if (hours == 0) {
            return minutes + " minutes";
        } else if (minutes == 0) {
            return hours + " hour(s)";
        } else {
            return hours + " hour(s) " + minutes + " minutes";
        }
    }
    @FXML
    void onSavePress(){


    }
}
