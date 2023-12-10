package com.mycompany.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class UserPrefsController {

    @FXML private ComboBox<String> genEndHours;
    @FXML private ComboBox<String> genEndMinutes;

    @FXML private Label alarmTime;

    private LocalDateTime genEndTime;
    private Timeline clock;

    public void initialize(){
        setTimeOfComboBox();
        setTimeToNow();

    }

    @FXML
    void onStartPress() {
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> updateLabel()),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    @FXML
    void onStopPress() {
        clock.stop();
    }
    private void setTimeToNow() {
        LocalDateTime now = LocalDateTime.now();

        genEndHours.setValue(String.format("%02d", now.getHour()));
        genEndMinutes.setValue(String.format("%02d", now.getMinute()));
    }

    private void updateLabel() {
        updateAlarmTimeLabel();
        setAlarm();
    }

    private void setAlarm() {
        try {
            LocalDate today = LocalDate.now();
            if (genEndHours.getValue() != null && genEndMinutes.getValue() != null) {
                LocalTime newEndTime = LocalTime.of(Integer.parseInt(genEndHours.getValue()), Integer.parseInt(genEndMinutes.getValue()));
                LocalDateTime newGenEndTime = LocalDateTime.of(today, newEndTime);

                // only update when genEndTime is change
                if (newGenEndTime.equals(genEndTime)) {
                    return;
                }

                genEndTime = newGenEndTime;
                updateAlarmTimeLabel(); // update alarmTime
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in ComboBox");
        }
    }

    private void checkAlarm() {
        if (alarmTime.getText().equals("00:00:00")) {
            clock.stop();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Time's Up!");
            alert.setHeaderText(null);
            alert.setContentText("The time has been reached!");

            alert.show();
        }
    }
    private void updateAlarmTimeLabel() {
        if (genEndTime == null) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.of(now.toLocalDate(), LocalTime.from(genEndTime));

        // check current time is after than genEndTime
        if (now.toLocalTime().isAfter(genEndTime.toLocalTime())) {
            // if current is after genEndTime，than set genEndTime is next day
            endDateTime = LocalDateTime.of(now.toLocalDate().plusDays(1), LocalTime.from(genEndTime));
        } else {
            endDateTime = LocalDateTime.of(now.toLocalDate(), LocalTime.from(genEndTime));
        }
        if (now.isBefore(endDateTime)) {
            long hours = now.until(genEndTime, ChronoUnit.HOURS);
            long minutes = now.until(genEndTime, ChronoUnit.MINUTES) % 60;
            long seconds = now.until(genEndTime, ChronoUnit.SECONDS) % 60;
            alarmTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        } else {
            alarmTime.setText("00:00:00");

        }
        checkAlarm();
    }

    private void setTimeOfComboBox(){
        ObservableList<String> hours = generateTimeList(24);
        ObservableList<String> minutes = generateTimeList(60);

        genEndHours.setItems(hours);
        genEndMinutes.setItems(minutes);
    }
    // used to set the hours and minutes
    private ObservableList<String> generateTimeList(int max) {
        ObservableList<String> timeList = FXCollections.observableArrayList();
        for (int i = 0; i < max; i++) {
            timeList.add(String.format("%02d", i));
        }
        return timeList;
    }

}
