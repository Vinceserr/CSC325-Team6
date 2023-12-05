package com.mycompany.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class UserPrefsStage extends createStage{
    @Override
    public void showStage()throws Exception {
        loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/UserPrefs.fxml"));
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}

