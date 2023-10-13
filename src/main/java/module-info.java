module com.mycompany.personalstudyingschedulingapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.Application to javafx.fxml;
    exports com.mycompany.Application;
    exports com.mycompany.Controller;
    opens com.mycompany.Controller to javafx.fxml;


}
