package com.mycompany.Controller;

import com.mycompany.Application.studyTopic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

import static com.mycompany.Application.Account.loadEmail;


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
    @FXML
    private Button submit;
    @FXML
    private Button cancel;
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

    /**
     * Helper method to check if a string has a letter or number
     * @param input the string that you'd like to check
     * @return true if the string has a letter or number and false if not
     */
    public static boolean containsLetterOrNumber(String input) {
        // Using regular expressions to check if the string contains any letter or number
        return input.matches(".*[a-zA-Z].*") || input.matches(".*\\d.*");
    }
    /**
     * Formats duration to a string
     * @param duration duration object as a string
     * @return string of the duration object
     */
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
    void onSavePress() {
        topics = new ArrayList<>();

        // Assuming you have error checking/validation for each study topic
        // If a study topic is empty or the duration is not selected, handle it accordingly

        String name1 = studyTopic1.getText();
        String durationString1 = duration1.getValue();

        String name2 = studyTopic2.getText();
        String durationString2 = duration2.getValue();

        String name3 = studyTopic3.getText();
        String durationString3 = duration3.getValue();

        String name4 = studyTopic4.getText();
        String durationString4 = duration4.getValue();

        String name5 = studyTopic5.getText();
        String durationString5 = duration5.getValue();

        String name6 = studyTopic6.getText();
        String durationString6 = duration6.getValue();

        // Create studyTopic objects and add them to the ArrayList
        if (containsLetterOrNumber(name1) && durationString1 != null) {
            Duration duration1 = parseDurationString(durationString1);
            studyTopic topic1 = new studyTopic(name1, duration1);
            topics.add(topic1);
        }

        if (containsLetterOrNumber(name2) && durationString2 != null) {
            Duration duration2 = parseDurationString(durationString2);
            studyTopic topic2 = new studyTopic(name2, duration2);
            topics.add(topic2);
        }

        if (containsLetterOrNumber(name3) && durationString3 != null) {
            Duration duration3 = parseDurationString(durationString3);
            studyTopic topic3 = new studyTopic(name3, duration3);
            topics.add(topic3);
        }

        if (containsLetterOrNumber(name4) && durationString4 != null) {
            Duration duration4 = parseDurationString(durationString4);
            studyTopic topic4 = new studyTopic(name4, duration4);
            topics.add(topic4);
        }

        if (containsLetterOrNumber(name5) && durationString5 != null) {
            Duration duration5 = parseDurationString(durationString5);
            studyTopic topic5 = new studyTopic(name5, duration5);
            topics.add(topic5);
        }

        if (containsLetterOrNumber(name6) && durationString6 != null) {
            Duration duration6 = parseDurationString(durationString6);
            studyTopic topic6 = new studyTopic(name6, duration6);
            topics.add(topic6);
        }

        // Now 'topics' ArrayList contains studyTopic objects for each valid study topic
        // You can perform further operations with the 'topics' list as needed
        System.out.println(topics.toString());
        System.out.println(loadEmail());

    }

    // Helper method to parse a duration string and convert it to a Duration object
    public static Duration parseDurationString(String durationString) {
        String[] parts = durationString.split(" ");

        long hours = 0;
        long minutes = 0;

        for (int i = 0; i < parts.length; i += 2) {
            long value = Long.parseLong(parts[i]);
            String unit = i + 1 < parts.length ? parts[i + 1] : "";

            if ("hour".equals(unit) || "hours".equals(unit)) {
                hours += value;
            } else if ("minute".equals(unit) || "minutes".equals(unit)) {
                minutes += value;
            }
        }

        return Duration.ofHours(hours).plusMinutes(minutes);
    }
}
