package com.mycompany.Controller;

import com.mycompany.Application.userPrefs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        for (int hours = 0; hours < 12; hours++) {
            for (int minutes = 0; minutes < 60; minutes += 30) {
                Duration duration = Duration.ofMinutes((hours * 60) + minutes);
                String formattedDuration = formatDuration(duration);
                maxStudyTime.getItems().add(formattedDuration);
            }
        }
    }

    // Formatting Duration to String
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

    private Duration parseDuration(String formattedDuration) {
        // Implement the logic to parse the formatted string back to a Duration object
        // This logic should handle the reverse of the formatting in formatDuration()

        // Split the string by space and get the components
        String[] parts = formattedDuration.split(" ");
        if (parts.length == 3) {
            long hours = Long.parseLong(parts[0]);
            long minutes = Long.parseLong(parts[2]);
            return Duration.ofHours(hours).plusMinutes(minutes);
        } else if (parts.length == 2) {
            if (parts[1].equals("hour(s)")) {
                long hours = Long.parseLong(parts[0]);
                return Duration.ofHours(hours);
            } else {
                long minutes = Long.parseLong(parts[0]);
                return Duration.ofMinutes(minutes);
            }
        }
        return Duration.ZERO; // Default value if parsing fails
    }
    @FXML
    void onSubmitPress() {
        userPrefs prefs = new userPrefs(timeToLocalTime(genStartTime.getValue()), timeToLocalTime(genEndTime.getValue()), parseDuration(maxStudyTime.getValue()));
        System.out.println(prefs);
    }
    public static LocalTime timeToLocalTime(String time){
        // Helper method to convert formatted time string to LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.parse(time, formatter);
    }
}
