package com.mycompany.Controller;

import com.mycompany.Application.App;
import com.mycompany.Model.UserPrefs;
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

    @FXML private ComboBox<Integer> genStartHours;
    @FXML private ComboBox<Integer> genStartMinutes;

    @FXML private ComboBox<Integer> genEndHours;
    @FXML private ComboBox<Integer> genEndMinutes;

    @FXML private Label alarmTime;

    private LocalDateTime genStartTime;
    private LocalDateTime genEndTime;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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

        genStartHours.setValue(now.getHour());
        genStartMinutes.setValue(now.getMinute());
        genEndHours.setValue(now.getHour());
        genEndMinutes.setValue(now.getMinute());
    }
    private void updateLabel() {
        LocalDateTime now = LocalDateTime.now();
        genStartTime = now;
        alarmTime.setText(formatter.format(now));
        updateAlarmTimeLabel();
        checkAlarm(now);
        setAlarm();
    }

    private void setAlarm() {
        if (genEndHours.getValue() != null && genEndMinutes.getValue() != null) {
            genEndTime = LocalDateTime.of(genStartTime.toLocalDate(),
                    LocalTime.of(genEndHours.getValue(), genEndMinutes.getValue()));
        }
    }

    private void checkAlarm(LocalDateTime now) {
        if (genEndTime != null && now.equals(genEndTime)) {
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
        ObservableList<Integer> hours = FXCollections.observableArrayList();
        ObservableList<Integer> minutes = FXCollections.observableArrayList();

        for (int hour = 00; hour < 24; hour++) {
            hours.add(hour);
        }
        for (int minute = 00; minute < 60; minute++) {
            minutes.add(minute);
        }

        genStartHours.setItems(hours);
        genStartMinutes.setItems(minutes);
        genEndHours.setItems(hours);
        genEndMinutes.setItems(minutes);
    }

}
