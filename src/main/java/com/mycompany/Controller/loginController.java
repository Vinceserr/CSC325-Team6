package com.mycompany.Controller;

import java.util.concurrent.ExecutionException;

import com.mycompany.Application.App;
import com.mycompany.Application.TaskScheduler;
import com.mycompany.Model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import static com.mycompany.Controller.signupController.getAccountDetails;

public class loginController {

    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_password;

    private App app;
    private TaskScheduler taskScheduler;

    @FXML
    void signInButtonPress() throws Exception {
        // check the username and password is correct
        // if is true, go to schedule
        boolean result = login();
        if(result){
            taskScheduler.clearAll();
            app.setRoot("mainSchedule");

        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Login Failed");
            ButtonType bt = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(bt);
            alert.showAndWait();
        }
    }

    boolean login() throws ExecutionException, InterruptedException {
        String emailText = tf_email.getText();
        String passwordText = tf_password.getText();
        Account account = getAccountDetails(emailText);
        //if nothing enter, click logIn button will not work
        if (emailText.isEmpty() || passwordText.isEmpty()) {
            return false;
        }
        //if there is an account created, enter the correct credentials
        if (account != null) {
            String username = account.getEmail();
            String password = account.getPassword();

            return username.equals(emailText) &&
                    password.equals(passwordText);
        }
        return false;

    }
    @FXML
    void signUpButtonPress() throws Exception {
        app.setRoot("signUp");
    }

    public void setApp(App app){
        this.app = app;
    }

    public void setTaskScheduler(TaskScheduler taskScheduler){
        this.taskScheduler = taskScheduler;
    }
}
