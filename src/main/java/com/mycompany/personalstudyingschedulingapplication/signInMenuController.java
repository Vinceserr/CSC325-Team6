package com.mycompany.personalstudyingschedulingapplication;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class signInMenuController {

    @FXML
    private TextField usernameField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signInButton;
    
 
    @FXML
    void signInButtonPress(ActionEvent event)throws IOException {
         
    }

    @FXML
    void signUpButtonPress(ActionEvent event) throws IOException {
          App.setRoot("signUpMenu");
    }

}

