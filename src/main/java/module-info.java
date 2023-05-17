module com.example.oop_ui_test {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_ui_test to javafx.fxml;
    exports com.example.oop_ui_test;
    exports com.example.oop_ui_test.Classes;
    opens com.example.oop_ui_test.Classes to javafx.fxml;
    exports com.example.oop_ui_test.Controller;
    opens com.example.oop_ui_test.Controller to javafx.fxml;
}