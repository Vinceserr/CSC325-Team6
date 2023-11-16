package com.mycompany.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.Application.App;
import com.mycompany.Model.Account;
import com.mycompany.Application.CreateStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class signupController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;

    @FXML private TextField passwordField;
    @FXML private TextField newPasswordField;

    @FXML private Button submitButton;

    static ArrayList<Account> list = new ArrayList<>();

    @FXML
    void submitButtonPress(ActionEvent event) throws Exception {
        boolean result = register();
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
    /**
     * getAccountDetails method:
     * This method get the specific user from the database by using the email.
     *
     * @param username
     * @return the Account info
     */
    public static Account getAccountDetails(String username) throws ExecutionException, InterruptedException {
        //document reference for the specific email(document name in the database)
        DocumentReference docRef = App.fstore.collection("Users").document(username);
        //we need the document snapShot retrieved from the document reference
        ApiFuture<DocumentSnapshot> ft = docRef.get();
        //extract the document snapShot from the APiFuture object
        DocumentSnapshot doc = ft.get();
        //if the document exists return the Account object
        if (doc.exists()) {
            Account ac = new Account(String.valueOf(doc.getData().get("email")),
                    String.valueOf(doc.getData().get("name").toString()),
                    String.valueOf(doc.getData().get("password").toString()));
            return ac;
        } else {
            return null;
        }
    }

    boolean register() throws ExecutionException, InterruptedException{

        String usernameTF  = usernameField.getText();
        String emailTF = emailField.getText();
        String passwordTF = passwordField.getText();
        String newpasswordTF = newPasswordField.getText();
        Account newAccount = new Account();

        //if nothing enter, click submit button will not work
        if(emailTF.isEmpty() ||usernameTF.isEmpty() || passwordTF.isEmpty()){
            return false;
        }
        // if it is empty, only need to check the password
        if(list.isEmpty()){
            if(passwordTF.equals(newpasswordTF)){
                newAccount.setUsername(usernameTF);
                newAccount.setEmail(emailTF);
                newAccount.setPassword(passwordTF);
                list.add(newAccount);
                return true;
            }
        }

        //check if this account exist by calling the getAccountDetails
        newAccount = getAccountDetails(usernameTF);
        //if user is not found then add the new User
        if (newAccount == null) {
            //add User to the database
            DocumentReference docRef = App.fstore.collection("Users").document(usernameTF);
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

}
