package com.mycompany.Controller;

import com.mycompany.Application.studyTopic;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class studyTopicsController {
    //Study Topic name text fields
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
    //Durations for the study topics
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
        durationBoxCheck(duration1, studyTopic1);
        durationBoxCheck(duration2, studyTopic2);
        durationBoxCheck(duration3, studyTopic3);
        durationBoxCheck(duration4, studyTopic4);
        durationBoxCheck(duration5, studyTopic5);
        durationBoxCheck(duration6, studyTopic6);
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

    /**
     * Helper method to check if a string has a letter or number
     * @param input the string that you'd like to check
     * @return true if the string has a letter or number and false if not
     */
    public static boolean containsLetterOrNumber(String input) {
        // Using regular expressions to check if the string contains any letter or number
        return input.matches(".*[a-zA-Z].*") || input.matches(".*\\d.*");
    }
    private void durationBoxCheck(ComboBox<String> dur, TextField sT){
        dur.setDisable(containsLetterOrNumber(sT.getText()));
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
