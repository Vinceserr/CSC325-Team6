package com.mycompany.Application;

import com.mycompany.Stage.*;
import javafx.application.Application;
import javafx.stage.Stage;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirebaseInitialize contxtFirebase = new FirebaseInitialize();


    @Override
    public void start(Stage stage) throws Exception {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();

        createStage login = new loginStage();
        createStage signup = new signupStage();
        createStage mainSchedule = new MainScheduleStage();
        createStage calendar =  new calendarStage();
        createStage addEvent = new addTaskStage();



        //login.showStage();
        //signup.showStage();
        mainSchedule.showStage();
        //calendar.showStage();
        //addEvent.showStage();


    }


    public static void main(String[] args) {
        launch();
    }



}