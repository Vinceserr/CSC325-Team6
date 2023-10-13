package com.mycompany.Application;

import com.mycompany.Application.createStage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class scheduleStage extends createStage {
    @Override
    public void showStage()throws Exception {
        loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/schedule.fxml"));
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
