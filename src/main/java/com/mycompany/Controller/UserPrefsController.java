package com.mycompany.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class UserPrefsController {

    @FXML private ComboBox<String> genStartHours;
    @FXML private ComboBox<String> genStartMinutes;

    @FXML private ComboBox<String> genEndHours;
    @FXML private ComboBox<String> genEndMinutes;

    @FXML private Label alarmTime;

    private LocalDateTime genStartTime;
    private LocalDateTime genEndTime;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void initialize(){
        setTimeOfComboBox();
        setTimeToNow();

    }

    @FXML
    void onStartPress() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> updateLabel()),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }
    private void setTimeToNow() {
        LocalDateTime now = LocalDateTime.now();

        genStartHours.setValue(String.valueOf(now.getHour()));
        genStartMinutes.setValue(String.valueOf(now.getMinute()));
        genEndHours.setValue(String.valueOf(now.getHour()));
        genEndMinutes.setValue(String.valueOf(now.getMinute()));
    }
    private void updateLabel() {
        LocalDateTime now = LocalDateTime.now();
        genStartTime = now;
       // alarmTime.setText(formatter.format(now));
        updateAlarmTimeLabel();
        checkAlarm(now);
        setAlarm();
    }

    private void setAlarm() {
        if (genEndHours.getValue() != null && genEndMinutes.getValue() != null) {
            genEndTime = LocalDateTime.of(genStartTime.toLocalDate(),
                    LocalTime.of(Integer.parseInt(genEndHours.getValue()), Integer.parseInt(genEndMinutes.getValue())));
        }
    }

    private void checkAlarm(LocalDateTime now) {
        if (now.equals(genEndTime)) {
            System.out.println("Alarm! " + genEndTime.format(formatter));
        }
    }
    private void updateAlarmTimeLabel() {
        if (genStartTime != null && genEndTime != null && genStartTime.isBefore(genEndTime)) {
            long hours = genStartTime.until(genEndTime, ChronoUnit.HOURS);
            long minutes = genStartTime.until(genEndTime, ChronoUnit.MINUTES) % 60;
            long seconds = genStartTime.until(genEndTime, ChronoUnit.SECONDS) % 60;
            alarmTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        } else {
            alarmTime.setText("00:00:00");
        }
    }

    private void setTimeOfComboBox(){
        ObservableList<String> hours = FXCollections.observableArrayList();
        ObservableList<String> minutes = FXCollections.observableArrayList();

        for (int hour = 0; hour < 24; hour++) {
            hours.add(String.format("%02d", hour));
        }
        for (int minute = 0; minute < 60; minute++) {
            minutes.add(String.format("%02d", minute));
        }

        genStartHours.setItems(hours);
        genStartMinutes.setItems(minutes);
        genEndHours.setItems(hours);
        genEndMinutes.setItems(minutes);
    }

}
