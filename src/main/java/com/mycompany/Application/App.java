package com.mycompany.Application;

import com.mycompany.Controller.AddTaskController;
import com.mycompany.Controller.CalendarController;
import com.mycompany.Controller.MainScheduleController;
import com.mycompany.Stage.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

        // set the taskScheuler into MainScheduleController
        // which create a map to store task data and listen for data change
       // TaskScheduler taskScheduler = new TaskScheduler();
        //FXMLLoader loader = createStage.loadFXML("mainSchedule");
       // MainScheduleController mainScheduleController = loader.getController();
       // mainScheduleController.setTaskScheduler(taskScheduler);

        // there are create Stage for every window
        createStage login = new loginStage();
        createStage signup = new signupStage();
        createStage mainSchedule = new MainScheduleStage();
        createStage calendar =  new calendarStage();
        createStage addEvent = new addTaskStage();
        createStage UserPrefs = new UserPrefsStage();

        //show the stage for each window
        //notices only login window should be show, other just testing
        //login.showStage();
        //signup.showStage();
        mainSchedule.showStage();
        //calendar.showStage();
        //addEvent.showStage();
        //UserPrefs.showStage();


    }


    public static void main(String[] args) {
        launch();
    }



}