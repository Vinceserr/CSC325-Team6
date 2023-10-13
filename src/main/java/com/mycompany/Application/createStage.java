package com.mycompany.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class createStage {
    protected static Stage stage;
    protected Scene scene;
    protected FXMLLoader loader;

    public createStage() {
        stage = new Stage();
    }

    public abstract void showStage() throws Exception;

    public static void close() {
        stage.close();
    }
}
