package com.mycompany.Controller;

import java.util.ArrayList;

import com.mycompany.Application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class loginController {

    @FXML
    private TextField tf_userName;


    @FXML
    private TextField tf_password;

    
    @FXML
    void signInButtonPress(ActionEvent event) throws Exception {
        ArrayList<Account>list = signupController.list;
        // check the username and password is correct
        // if is true, go to schedule
        boolean result = login(list); 
        if(result){
            CreateStage.close();
            //CreateStage.setRoot("mainSchedule");
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
    void signUpButtonPress(ActionEvent event) throws Exception {
        CreateStage.close();
        //CreateStage.setRoot("signup");
    }

}

