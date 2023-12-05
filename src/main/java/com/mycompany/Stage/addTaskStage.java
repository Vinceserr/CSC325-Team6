package com.mycompany.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class addTaskStage extends createStage {
    @Override
    public void showStage()throws Exception {
        scene = new Scene(loadFXML("addTask").load());
        stage.setScene(scene);
        stage.show();
    }

}