package com.mycompany.Application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //CreateStage.setRoot("login");
        CreateStage.setRoot("signup");
        //CreateStage.setRoot("mainSchedule");
        //CreateStage.setRoot("calendar");
        //CreateStage.setRoot("addTask");



    }


    public static void main(String[] args) {
        launch();
    }



}