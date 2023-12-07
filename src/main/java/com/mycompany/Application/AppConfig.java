package com.mycompany.Application;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

/**
 * The AppConfig class is a class that contains the methods used to
 * save and load properties from the appConfigProperties file
 */
public class AppConfig {

    private static final String configFiles = "appConfigProperties";
    private static final String emailKey = "email";
    private static final String userPrefsKey = "userPrefs";
    private static final String studyTopicsKey = "studyTopics";

    /**
     * Saves email to the appConfigProperties.
     * @param email The email to be saved.
     */
    public static void saveEmail(String email) {
        saveProperty(AppConfig.emailKey, email);
    }

    /**
     * Retrieves email from the appConfigProperties.
     * @return The email data
     */
    public static String getEmail() {
        return loadProperty(emailKey);
    }

    /**
     * Used to save userPrefs into the appConfigProperties
     * @param userPrefs the userPrefs data to be saved
     */
    public static void saveUserPrefs(String userPrefs) {
        saveProperty(AppConfig.userPrefsKey, userPrefs);
    }

    /**
     * Retrieves userPrefs from the appConfigProperties.
     * @return The userPrefs data
     */
    public static String getUserPrefs() {
        return loadProperty(userPrefsKey);
    }

    /**
     * Saves studyTopics to the appConfigProperties.
     * @param studyTopics The studyTopics data to be saved.
     */
    public static void saveStudyTopics(String studyTopics) {
        saveProperty(studyTopicsKey, studyTopics);
    }

    /**
     * Retrieves studyTopics from the appConfigProperties.
     * @return The studyTopics data
     */
    public static String getStudyTopics() {
        return loadProperty(studyTopicsKey);
    }

    /**
     * Used to save different properties into appConfigProperties.
     * @param key the key of the property
     * @param value the data of the property
     */
    private static void saveProperty(String key, String value) {
        try (InputStream is = new FileInputStream(configFiles)) {
            Properties properties = new Properties();

            // Load existing properties
            properties.load(is);

            // Preserve existing properties
            // Creates an iterator to go through the property keys
            Iterator<String> iterator = properties.stringPropertyNames().iterator();
            for (int i = 0; i < properties.size(); i++) {
                String existingKey = iterator.next();
                if (!existingKey.equals(key)) {
                    properties.setProperty(existingKey, properties.getProperty(existingKey));
                }
            }

            // Save the new property
            properties.setProperty(key, value);

            // Store the properties to the file
            try (OutputStream os = new FileOutputStream(configFiles)) {
                properties.store(os, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to load properties from a given key
     * @param key the key of the property
     * @return the data from the key
     */
    private static String loadProperty(String key) {
        try (InputStream is = new FileInputStream(configFiles)) {
            Properties properties = new Properties();

            // Load existing properties
            properties.load(is);

            // Retrieve the property from properties
            return properties.getProperty(key);
        } catch (IOException e) {
            System.out.println("Couldn't get property: " + key);
            return null;
        }
    }

    /**
     * Checks if the appConfigProperties file exists
     * and if not will create a new file
     */
    public static void fileChecker() {
        try {
            // Check if the config file exists
            File configFile = new File(configFiles);
            if (!configFile.exists()) {
                // If it doesn't exist, create a new file
                configFile.createNewFile();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
