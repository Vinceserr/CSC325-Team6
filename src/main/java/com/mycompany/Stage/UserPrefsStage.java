package com.mycompany.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class UserPrefsStage extends createStage {
    @Override
    public void showStage() throws Exception {
        scene = new Scene(loadFXML("UserPrefs").load());
        stage.setScene(scene);
        stage.show();
    }

}

