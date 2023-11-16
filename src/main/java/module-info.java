module com.mycompany.personalstudyingschedulingapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.Application to javafx.fxml;
    exports com.mycompany.Application;
    exports com.mycompany.View;
    opens com.mycompany.View to javafx.fxml;
    exports com.mycompany.Model;
    opens com.mycompany.Model to javafx.fxml;


}
