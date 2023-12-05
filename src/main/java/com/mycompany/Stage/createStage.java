package com.mycompany.Stage;

import com.mycompany.Model.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class createStage {
    protected static Stage stage;
    protected Scene scene;
    protected static FXMLLoader loader;
    protected Task task;

    public createStage() {
        stage = new Stage();
        task = new Task();
    }

    public abstract void showStage() throws Exception;

    public static FXMLLoader loadFXML(String fxml){
        loader = new FXMLLoader(createStage.class.getResource("/com/mycompany/Application/" + fxml+".fxml"));
        return loader;
    }

    public static void close() {
        stage.close();
    }

}