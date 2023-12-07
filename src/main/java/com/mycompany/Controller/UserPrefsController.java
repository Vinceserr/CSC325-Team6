package com.mycompany.Controller;

import com.mycompany.Application.App;
import com.mycompany.Model.UserPrefs;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.time.Duration;
import java.time.LocalTime;

public class UserPrefsController {

    @FXML
    private ComboBox<LocalTime> genStartTime;
    @FXML
    private ComboBox<LocalTime> genEndTime;
    @FXML
    private ComboBox<String> maxStudyTime;
    @FXML
    private Button submit;

    private App app;

    public void initialize(){
        setTimeOfComboBox();
        setTimeofDuration();
    }

    private void setTimeOfComboBox(){
        for(int hour =0; hour<24; hour++){
            for( int minute =0; minute < 60; minute+=10){
                genStartTime.getItems().add(LocalTime.of(hour,minute));
                genEndTime.getItems().add(LocalTime.of(hour,minute));
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

    // Parsing String back to Duration
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
        UserPrefs prefs = new UserPrefs(genStartTime.getValue(), genEndTime.getValue(), parseDuration(maxStudyTime.getValue()));
        System.out.println(prefs);
    }

    public void setApp(App app){
        this.app = app;
    }
}
