package com.mycompany.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class calendarStage extends createStage {
    @Override
    public void showStage()throws Exception {
        scene = new Scene(loadFXML("calendar").load());
        stage.setScene(scene);
        stage.show();
    }

}