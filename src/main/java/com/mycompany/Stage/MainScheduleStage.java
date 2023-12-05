package com.mycompany.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class MainScheduleStage extends createStage{
    @Override
    public void showStage() throws Exception {
        scene = new Scene(loadFXML("MainSchedule").load());
        stage.setScene(scene);
        stage.show();
    }
}