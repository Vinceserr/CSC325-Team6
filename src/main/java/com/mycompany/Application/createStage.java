package com.mycompany.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class createStage {
    protected static Stage stage;
    protected Scene scene;
    protected FXMLLoader loader;
    protected Task task;

    public createStage() {
        stage = new Stage();
        task = new Task();
    }

    public abstract void showStage() throws Exception;

    public static void close() {
        stage.close();
    }
}
