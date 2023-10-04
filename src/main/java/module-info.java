module com.mycompany.personalstudyingschedulingapplication {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.personalstudyingschedulingapplication to javafx.fxml;
    exports com.mycompany.personalstudyingschedulingapplication;
}
