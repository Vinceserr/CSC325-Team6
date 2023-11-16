package com.mycompany.Controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.mycompany.Application.*;
import com.mycompany.Model.Account;
import com.mycompany.ViewModel.AccountViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import static com.mycompany.Controller.signupController.getAccountDetails;

public class loginController {

    @FXML
    private TextField tf_userName;

    @FXML
    private TextField tf_password;

    public void initialize(){
        AccountViewModel accountViewModel = new AccountViewModel();
        tf_userName.textProperty().bindBidirectional(accountViewModel.usernameProperty());
        tf_password.textProperty().bindBidirectional(accountViewModel.passwordProperty());
    }
    @FXML
    void signInButtonPress(ActionEvent event) throws Exception {
        ArrayList<Account>list = signupController.list;
        // check the username and password is correct
        // if is true, go to schedule
        boolean result = login(list);
        if(result){
            CreateStage.close();
            CreateStage.setRoot("mainSchedule");
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Login Failed");
            ButtonType bt = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(bt);
            alert.showAndWait();
        }



    }
    boolean login(ArrayList<Account> list) throws ExecutionException, InterruptedException {
        String userNameText = tf_userName.getText();
        String passwordText = tf_password.getText();
        Account account = getAccountDetails(userNameText);
        //if nothing enter, click logIn button will not work
        if (userNameText.isEmpty() || passwordText.isEmpty()) {
            return false;
        }
        //if there is an account created, enter the correct credentials
        if (account != null) {
            String username = account.getUsername();
            String password = account.getPassword();

            if (username.equals(userNameText) &&
                    password.equals(passwordText)) {
                return true;
            }
        }
        return false;

    }
    @FXML
    void signUpButtonPress(ActionEvent event) throws Exception {
        CreateStage.close();
        CreateStage.setRoot("signup");
    }

}
