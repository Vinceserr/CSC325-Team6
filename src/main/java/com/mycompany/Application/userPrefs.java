package com.mycompany.Application;

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Properties;

public class userPrefs {
    private LocalTime startTime; //startTime is the earliest time the program will generate a study task.(Waking up)
    private LocalTime endTime; //endTime is the latest time the program will generate a study task.(Bed Time)
    private Duration studyLength; //studyLength is the maximum amount of time a study task will last.
    private static final String CONFIG_FILE = "config.properties";

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
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Used to save userPrefs into the CONFIG_FILE
     * @param userPrefs the userPrefs object to be saved
     */
    public static void saveUserPrefs(userPrefs userPrefs) throws FileNotFoundException {
        try (OutputStream os = new FileOutputStream(CONFIG_FILE)) {
            Properties properties = new Properties();

            // Combine userPrefs properties into a single string
            String prefs = String.format("%s;%s;%s",
                    userPrefs.getStartTime().toString(),
                    userPrefs.getEndTime().toString(),
                    userPrefs.getStudyLength().toString());

            // Save the combined string to the properties
            properties.setProperty("userPrefs", prefs);

            // Store the properties to the file
            properties.store(os, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Retrieves userPrefs from the CONFIG_FILE.
     * @return The userPrefs object, or null if there is an error.
     */
    public static userPrefs getUserPrefs() {
        try (InputStream is = new FileInputStream(CONFIG_FILE)) {
            Properties properties = new Properties();

            // Load existing properties
            properties.load(is);

            // Retrieve the combined string from properties
            String combinedPrefs = properties.getProperty("userPrefs");

            // Split the combined string into individual properties
            String[] prefsArray = combinedPrefs.split(";");
            LocalTime startTime = LocalTime.parse(prefsArray[0]);
            LocalTime endTime = LocalTime.parse(prefsArray[1]);
            Duration studyLength = Duration.parse(prefsArray[2]);

            return new userPrefs(startTime, endTime, studyLength);
        } catch (IOException e) {
            System.out.println("Couldn't get userPrefs");
            return null;
        }
    }

    @Override
    public String toString() {
        return "Day duration: " + startTime + "-" + endTime + " Max Study Length:" + formatDuration(studyLength);
    }
}