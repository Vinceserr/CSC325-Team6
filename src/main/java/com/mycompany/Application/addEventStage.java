package com.mycompany.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class addEventStage extends createStage{
    @Override
    public void showStage()throws Exception {
        loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/addEvent.fxml"));
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
