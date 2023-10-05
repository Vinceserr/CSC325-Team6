module com.mycompany.personalstudyingschedulingapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.personalstudyingschedulingapplication to javafx.fxml;
    exports com.mycompany.personalstudyingschedulingapplication;
}
