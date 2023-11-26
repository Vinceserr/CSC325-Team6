package com.mycompany.Controller;

import com.mycompany.Application.userPrefs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.mycompany.Application.Account.loadEmail;
import static com.mycompany.Application.userPrefs.*;
import static com.mycompany.Controller.studyTopicsController.parseDurationString;

public class userPrefsController {

    @FXML
    private ComboBox<String> genStartTime;
    @FXML
    private ComboBox<String> genEndTime;
    @FXML
    private ComboBox<String> maxStudyTime;
    @FXML
    private Button submit;

    public void initialize(){
        setTimeOfComboBox();
        setTimeofDuration();
    }

    private void setTimeOfComboBox() {
        for (int i = 0; i < 60; i+=15) {
            if(i<10){
                genStartTime.getItems().add("12:0" + i + " AM");
                genEndTime.getItems().add("12:0" + i + " AM");
            }
            else {
                genStartTime.getItems().add("12:" + i + " AM");
                genEndTime.getItems().add("12:" + i + " AM");
            }
        }

        for (int i = 1; i < 12; i++) {
            for (int j = 0; j < 60; j += 15) {
                String hour = (i < 10) ? "0" + i : String.valueOf(i);
                String minute = (j < 10) ? "0" + j : String.valueOf(j);

                genStartTime.getItems().add(hour + ":" + minute + " AM");
                genEndTime.getItems().add(hour + ":" + minute + " AM");
            }
        }

        for (int i = 1; i < 12; i++) {
            for (int j = 0; j < 60; j += 15) {
                //adds a zero if the number is less than 10 in order to keep proper format
                String hour = (i < 10) ? "0" + i : String.valueOf(i);
                String minute = (j < 10) ? "0" + j : String.valueOf(j);
                genStartTime.getItems().add(hour + ":" + minute + " PM");
                genEndTime.getItems().add(hour + ":" + minute + " PM");
            }
        }
    }
    // Populating the ComboBox with formatted duration strings
    private void setTimeofDuration() {
        for (int hours = 0; hours <= 10; hours++) {
            for (int minutes = 0; minutes < 60; minutes += 30) {
                Duration duration = Duration.ofMinutes((hours * 60) + minutes);
                String formattedDuration = formatDuration(duration);
                maxStudyTime.getItems().add(formattedDuration);
                if (hours == 10 && minutes == 0) {
                    // Break out of the loop when 10:00 is reached
                    break;
                }
                }
            if (hours == 10) {
                // Break out of the outer loop when 10:00 is reached
                break;
            }
        }
    }
    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();

        if (hours == 0) {
            return minutes + " minutes";
        } else if (hours == 1 && minutes == 0) {
            return hours + " hour";
        } else if (hours == 1) {
            return hours + " hour " + minutes + " minutes";
        }
        else if (minutes == 0) {
            return hours + " hours";
        }
        else {
            return hours + " hours " + minutes + " minutes";
        }
    }
    @FXML
    void onSubmitPress() throws FileNotFoundException {
        userPrefs prefs = new userPrefs(timeToLocalTime(genStartTime.getValue()), timeToLocalTime(genEndTime.getValue()), parseDurationString(maxStudyTime.getValue()));
        saveUserPrefs(prefs);
        System.out.println(loadEmail());
        System.out.println(getUserPrefs());
    }
    public static LocalTime timeToLocalTime(String time){
        // Helper method to convert formatted time string to LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.parse(time, formatter);
    }
}
