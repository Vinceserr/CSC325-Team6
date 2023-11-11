module com.mycompany.personalstudyingschedulingapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires spring.context;
    requires java.annotation;
    requires com.google.api.apicommon;
    requires org.slf4j;

    opens com.mycompany.Application to javafx.fxml;
    exports com.mycompany.Application;
    exports com.mycompany.Controller;
    opens com.mycompany.Controller to javafx.fxml;


}
