package com.mycompany.Application;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirebaseInitialize contxtFirebase = new FirebaseInitialize();

    @Override
    public void start(Stage stage) throws Exception {
        //fstore = contxtFirebase.firebase();
        //fauth = FirebaseAuth.getInstance();
        //createStage login = new loginStage();
        //login.showStage();

        createStage Schedule = new MainScheduleStage();
        Schedule.showStage();

        //createStage addEvent = new addTaskStage();
        //addEvent.showStage();

        //createStage UserPrefs = new studyTopicStage();
        //UserPrefs.showStage();
    }


    public static void main(String[] args) {
        launch();
    }

    

}