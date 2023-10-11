package com.mycompany.personalstudyingschedulingapplication;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        ArrayList<Account> list = signUpMenuController.list;
        // check the username and password is correct
        // if is true, go to scedule
        boolean result = login(list); 
        if(result){
            App.setRoot("scheduleScreen");
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Login Failed");
            ButtonType bt = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(bt);
            alert.showAndWait();
        }
        
        

    }
    boolean login(ArrayList<Account> list){
        Account account = new Account();
        if(!list.isEmpty()){
            for(Account a:list){
               String username = a.getUsername();
               String password =a.getPassword();
               String usernameText = tf_userName.getText();
               String passwordText = tf_password.getText();
               
               if(username.equals(usernameText) && 
                       password.equals(passwordText)){
                   return true;
               }
            }
        }
        return false;
        
    }
    @FXML
    void signUpButtonPress(ActionEvent event) throws IOException {
        App.setRoot("signUpMenu");
    }

}

