package com.mycompany.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateStage {
    private static Stage stage;
    private static Scene scene;
    private static String path = "/com/mycompany/Controller/";

    public static void setRoot(String fxml) throws IOException {
        stage = new Stage();
        Parent root = loadFXML(fxml).load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static FXMLLoader loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateStage.class.getResource(path + fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void close() {
        stage.close();
    }

}