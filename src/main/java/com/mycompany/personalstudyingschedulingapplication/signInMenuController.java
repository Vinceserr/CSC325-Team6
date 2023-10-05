package com.mycompany.personalstudyingschedulingapplication;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class signInMenuController {

    @FXML
    private TextField tf_userName;

    @FXML
    private Button button_login;

    @FXML
    private TextField tf_password;

    @FXML
    private Button button_signUp;
    
 
    @FXML
    void signInButtonPress(ActionEvent event)throws IOException {
         App.setRoot("scheduleScreen");
    }

    @FXML
    void signUpButtonPress(ActionEvent event) throws IOException {
          App.setRoot("signUpMenu");
    }

}

