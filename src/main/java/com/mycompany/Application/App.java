package com.mycompany.Application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createStage login = new loginStage();
        login.showStage();
    }

    
    public static void main(String[] args) {
        launch();
    }

    

}