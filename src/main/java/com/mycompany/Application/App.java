package com.mycompany.Application;

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

        //CreateStage.setRoot("login");
        //CreateStage.setRoot("signup");
        //CreateStage.setRoot("mainSchedule");
        //CreateStage.setRoot("calendar");
        //CreateStage.setRoot("addTask");


    }


    public static void main(String[] args) {
        launch();
    }



}