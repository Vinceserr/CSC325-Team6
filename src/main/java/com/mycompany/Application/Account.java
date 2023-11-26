/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author user
 */
public class Account {
    private String name;
    private String password;
    private String email;
    private static final String CONFIG_FILE = "config.properties";
    private static final String EMAIL_KEY = "email";


    public Account() {
    }

    public Account(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
    /**
     * Used to save the email used to log in into the CONFIG_FILE file
     * using the key "EMAIL_KEY"
     * @param email the email that is to be saved
     */
    public static void saveEmail(String email) {
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            Properties properties = new Properties();
            properties.setProperty(EMAIL_KEY, email);
            properties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Used to retrieve the email from CONFIG_FILE using the key "EMAIL_KEY"
     * @return the email from the CONFIG_FILE
     */
    public static String loadEmail() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            Properties properties = new Properties();
            properties.load(fis);
            return properties.getProperty(EMAIL_KEY);
        } catch (IOException e) {
            System.out.println("Couldn't get email");
            return null;
        }
    }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
