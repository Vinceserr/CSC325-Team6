package com.mycompany.Application;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateStage {
    private static Stage stage;
    private static Scene scene;

    public static void setRoot(String fxml) throws IOException {
        stage = new Stage();
        scene = new Scene(loadFXML(fxml));
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateStage.class.getResource("/com/mycompany/Application/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void close() {
        stage.close();
    }
}
