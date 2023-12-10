package com.mycompany.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.Application.App;
import com.mycompany.Model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class signupController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    private App app;

    @FXML
    void submitButtonPress() throws Exception {
        boolean result = register();
        //if is true, go back to signIn menu
        if (result) {
            app.setRoot("login");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Register Failed");
            alert.setHeaderText("Register Failed");
            ButtonType bt = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(bt);
            alert.showAndWait();
        }
    }

    /**
     * getAccountDetails method:
     * This method get the specific user from the database by using the email.
     *
     * @param email get the email from user enter
     * @return the Account info
     */
    public static Account getAccountDetails(String email) throws ExecutionException, InterruptedException {
        //document reference for the specific email(document name in the database)
        DocumentReference docRef = App.fstore.collection("Users").document(email);
        //we need the document snapShot retrieved from the document reference
        ApiFuture<DocumentSnapshot> ft = docRef.get();
        //extract the document snapShot from the APiFuture object
        DocumentSnapshot doc = ft.get();
        Account ac;
        //if the document exists return the Account object
        if (doc.exists()) {
            ac = new Account(String.valueOf(doc.getData().get("email")),
                    String.valueOf(doc.getData().get("name").toString()),
                    String.valueOf(doc.getData().get("password").toString()));
            return ac;
        } else {
            return null;
        }
    }

    public boolean register() throws ExecutionException, InterruptedException {

        //updated newPassword for emailField
        String emailTF = emailField.getText();
        String usernameTF = nameField.getText();
        String passwordTF = passwordField.getText();
        Account newAccount;

        //if nothing enter, click submit button will not work
        if (emailTF.isEmpty() || usernameTF.isEmpty() || passwordTF.isEmpty()) {
            return false;
        }
        //check if this account exist by calling the getAccountDetails
        newAccount = getAccountDetails(emailTF);
        //if user is not found then add the new User
        if (newAccount == null) {
            //add User to the database
            DocumentReference docRef = App.fstore.collection("Users").document(emailTF);
            //Add document data using a hashMap
            Map<String, Object> data = new HashMap<>();
            data.put("email", emailTF);
            data.put("name", usernameTF);
            data.put("password", passwordTF);
            ApiFuture<WriteResult> result = docRef.set(data);
            //print statement to see if user was added to the database
            System.out.println("UserAdded");
            return true;
        }
        //if user already registered then he/she cant register again
        //and return false
        return false;
    }

    public void setApp(App app){
        this.app = app;
    }


}
