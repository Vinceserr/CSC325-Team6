package com.mycompany.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class signupStage extends createStage {
    @Override
    public void showStage() throws Exception{
        scene = new Scene(loadFXML("signup").load());
        stage.setScene(scene);
        stage.show();
    }
}
