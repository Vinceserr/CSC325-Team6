package com.mycompany.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class loginStage extends createStage{

    @Override
    public void showStage() throws Exception{
        loader = new FXMLLoader(getClass().getResource("/com/mycompany/Application/login.fxml"));
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
