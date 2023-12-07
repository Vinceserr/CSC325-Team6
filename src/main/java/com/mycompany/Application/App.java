package com.mycompany.Application;

import com.mycompany.Controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirebaseInitialize contxtFirebase = new FirebaseInitialize();
    private Stage stage;
    private TaskScheduler taskScheduler = new TaskScheduler();


    @Override
    public void start(Stage stage) throws Exception {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();

        this.stage = stage;

        //setRoot("login");
        //setRoot("signup");
        setRoot("mainSchedule");
        //setRoot("calendar");
        //setRoot("addEvent");
        //setRoot("UserPrefs");
        stage.show();

    }

    public void setRoot(String fxml) throws Exception {
        FXMLLoader loader = setLoader(fxml);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    public FXMLLoader setLoader(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/mycompany/Application/" + fxml+".fxml"));
        loader.load();
        loadController(loader);
        return loader;
    }

    public <T> T loadController(FXMLLoader loader) throws Exception {
        T controller = loader.getController();

        if(controller instanceof loginController){
            ((loginController) controller).setApp(this);
        }else if (controller instanceof signupController) {
            ((signupController) controller).setApp(this);

        }else if (controller instanceof MainScheduleController) {

            ((MainScheduleController) controller).setApp(this);
            ((MainScheduleController) controller).setTaskScheduler(taskScheduler);

        }else if(controller instanceof AddTaskController){
            ((AddTaskController) controller).setApp(this);
            ((AddTaskController) controller).setTaskScheduler(taskScheduler);

        }else if (controller instanceof CalendarController){
            ((CalendarController) controller).setApp(this);
            ((CalendarController) controller).setTaskScheduler(taskScheduler);

        }else if(controller instanceof UserPrefsController){

        }

        return controller;
    }


    public static void main(String[] args) {
        launch();
    }



}