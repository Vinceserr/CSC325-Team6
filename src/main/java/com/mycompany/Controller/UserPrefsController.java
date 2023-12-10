package com.mycompany.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UserPrefsController {

    @FXML private ComboBox<String> durationHours;
    @FXML private ComboBox<String> durationMinutes;
    @FXML private ComboBox<String> durationSeconds;

    @FXML private Label alarmTime;

    private LocalDateTime genEndTime;
    private Timeline clock;

    public void initialize(){
        durationHours.setValue("00");
        durationMinutes.setValue("00");
        durationSeconds.setValue("00");
        setTimeOfComboBox();
    }

    @FXML
    void onStartPress() {
        setAlarm();
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> updateAlarmTimeLabel()),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    @FXML
    void onStopPress() {
        clock.stop();
    }

    private void setTimeOfComboBox(){
        ObservableList<String> hours = generateTimeList(24);
        ObservableList<String> minutes = generateTimeList(60);

        durationHours.setItems(hours);
        durationMinutes.setItems(minutes);
        durationSeconds.setItems(minutes);


    }

    // used to set the hours and minutes
    private ObservableList<String> generateTimeList(int max) {
        ObservableList<String> timeList = FXCollections.observableArrayList();
        for (int i = 0; i < max; i++) {
            timeList.add(String.format("%02d", i));
        }
        return timeList;
    }


    // set the ending time
    private void setAlarm() {
        try {
            LocalDateTime now = LocalDateTime.now();
            int selectedHours = Integer.parseInt(durationHours.getValue());
            int selectedMinutes = Integer.parseInt(durationMinutes.getValue());
            int selectedSeconds = Integer.parseInt(durationSeconds.getValue());

            genEndTime = now.plusHours(selectedHours).plusMinutes(selectedMinutes).plusSeconds(selectedSeconds);


        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in ComboBox");
        }
    }

    private void checkAlarm() {
        if (alarmTime.getText().equals("00:00:00")) {
            clock.stop();

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Time's Up!");
                alert.setHeaderText(null);
                alert.setContentText("The time has been reached!");

                alert.showAndWait();
            });
        }
    }
    private void updateAlarmTimeLabel() {
        System.out.println(genEndTime);
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(genEndTime)) {
            long hours = now.until(genEndTime, ChronoUnit.HOURS);
            long minutes = now.until(genEndTime, ChronoUnit.MINUTES) % 60;
            long seconds = now.until(genEndTime, ChronoUnit.SECONDS) % 60;
            alarmTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        } else {
            alarmTime.setText("00:00:00");
            checkAlarm();
        }

    }
}
