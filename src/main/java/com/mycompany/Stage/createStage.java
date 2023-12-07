package com.mycompany.Stage;

import com.mycompany.Application.TaskScheduler;
import com.mycompany.Controller.*;
import com.mycompany.Model.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class createStage {
    protected static Stage stage;
    protected Scene scene;
    protected static FXMLLoader loader;

    public createStage() {
        stage = new Stage();
    }

    public abstract void showStage() throws Exception;

    public static FXMLLoader loadFXML(String fxml) throws Exception {
        loader = new FXMLLoader(createStage.class.getResource("/com/mycompany/Application/" + fxml+".fxml"));
        loader.load();
        loadController(loader);
        return loader;
    }

    public static <T> T loadController(FXMLLoader loader) throws Exception {
        T controller = loader.getController();
        TaskScheduler taskScheduler = new TaskScheduler();
        if(controller instanceof loginController){

        }else if (controller instanceof signupController) {

        }else if (controller instanceof MainScheduleController) {

            ((MainScheduleController) controller).setTaskScheduler(taskScheduler);

        }else if(controller instanceof AddTaskController){
            ((AddTaskController) controller).setTaskScheduler(taskScheduler);

        }else if (controller instanceof CalendarController){
            ((CalendarController) controller).setTaskScheduler(taskScheduler);

        }else if(controller instanceof UserPrefsController){
        }

        return controller;
    }

    public static void close() {
        stage.close();
    }

}