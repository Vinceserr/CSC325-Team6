package com.mycompany.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class loginStage extends createStage {

    @Override
    public void showStage() throws Exception{
        scene = new Scene(loadFXML("login").load());
        stage.setScene(scene);
        stage.show();
        //make the login stage not resizable
        stage.setResizable(false);
    }


}