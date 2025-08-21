module com.example.javafxloginui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxloginui to javafx.fxml;
    exports com.example.javafxloginui;
}