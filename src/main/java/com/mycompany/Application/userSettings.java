package com.mycompany.Application;

import java.util.ArrayList;

/**
 * A class used to store the study topics and userPreferences and link it
 * to the corresponding email
 */
public class userSettings {
    String email; //The email used to link the studyTopics and accounts to
    ArrayList<studyTopic> sT; //The studyTopics the program will use to generate
    userPrefs uP; //The userPreferences the program will use to generate

    /**
     * Default constructor for the userSettings class
     */
    userSettings(){
        email = null;
        sT = null;
        uP = null;
    }
    /**
     * The constructor for the userSettings class
     * @param email The email used to link the studyTopics and accounts to
     * @param sT The studyTopics the program will use to generate
     * @param uP The userPreferences the program will use to generate
     */
    userSettings(String email, ArrayList<studyTopic> sT, userPrefs uP){
        this.email = email;
        this.sT = sT;
        this.uP = uP;
    }
}
