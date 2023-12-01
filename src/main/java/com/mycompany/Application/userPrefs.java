package com.mycompany.Application;

import java.time.Duration;
import java.time.LocalTime;

/**
 * The userPrefs class contains the methods needed to
 * create and edit a userPrefs
 */
public class userPrefs {
    private LocalTime startTime; //startTime is the earliest time the program will generate a study task.(Waking up)
    private LocalTime endTime; //endTime is the latest time the program will generate a study task.(Bed Time)
    private Duration studyLength; //studyLength is the maximum amount of time a study task will last.
    /**
     * Default constructor for userPrefs
     */
    userPrefs(){
        this.startTime = null;
        this.endTime = null;
        this.studyLength = null;
    }

    /**
     * Constructor for userPrefs
     * @param startTime startTime is the earliest time the program will generate a study task (Waking up)
     * @param endTime endTime is the latest time the program will generate a study task (Bed Time)
     * @param studyLength studyLength is the maximum amount of time a study task will last
     */
    public userPrefs(LocalTime startTime, LocalTime endTime, Duration studyLength) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.studyLength = studyLength;
    }

    /**
     * Gets the startTime of the user
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * sets the startTime for the user
     * @param startTime the new startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the endTime of the user
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }
    /**
     * sets the endTime for the user
     * @param endTime the new endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    /**
     * Gets the studyLength for the user
     * @return Duration the new studyLength
     */
    public Duration getStudyLength() {
        return studyLength;
    }
    /**
     * Sets the studyLength for the user
     * @param studyLength the new studyLength
     */
    public void setStudyLength(Duration studyLength) {
        this.studyLength = studyLength;
    }

    /**
     * Helper method that will turn duration into a string
     * @param duration the duration object you would like to turn into a string
     * @return the duration object in string form
     */
    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        return String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Used to return the userPrefs object as a string
     * @return a string representing the userPrefs object
     */
    @Override
    public String toString() {
        return "Day duration:" + startTime + "-" + endTime + "Max Study Length:" + formatDuration(studyLength);
    }
}