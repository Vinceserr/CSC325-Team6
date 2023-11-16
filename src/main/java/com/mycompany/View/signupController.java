package com.mycompany.View;

import java.util.ArrayList;

import com.mycompany.Model.Account;
import com.mycompany.Application.CreateStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class signupController {

    @FXML
    private TextField usernameField;

    @FXML
    private Button submitButton;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField passwordField;

    static ArrayList<Account> list = new ArrayList<>();

    @FXML
    void submitButtonPress(ActionEvent event) throws Exception {
        boolean result = register(list);
        //if is true, go back to signIn menu
        if(result){
            CreateStage.close();
            CreateStage.setRoot("login");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Register Failed");
            alert.setHeaderText("Register Failed");
            ButtonType bt = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(bt);
            alert.showAndWait();
        }

    }
    boolean register(ArrayList<Account> list){

        String usernameTF  = usernameField.getText();
        String passwordTF = passwordField.getText();
        String newpasswordTF = newPasswordField.getText();
        Account newAccount = new Account();

        //if nothing enter, click submit button will not work
        if(usernameTF.isEmpty() || passwordTF.isEmpty()){
            return false;
        }
        // if it is empty, only need to check the password
        if(list.isEmpty()){
            if(passwordTF.equals(newpasswordTF)){

                newAccount.setUsername(usernameTF);
                newAccount.setPassword(passwordTF);
                list.add(newAccount);

                return true;
            }
        }

        //check this account is not exist
        for(Account a:list){
            String username = a.getUsername();
            String password =a.getPassword();

            // check username is not exit and two passsword enters are correct
            if(!username.equals(usernameTF) &&
                    passwordTF.equals(newpasswordTF)){

                newAccount.setUsername(usernameTF);
                newAccount.setPassword(passwordTF);
                list.add(newAccount);

                return true;
            }
        }

        return false;

    }

}
