module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.controller;
    opens com.example.demo.controller to javafx.fxml;
    exports com.example.demo.debug;
    opens com.example.demo.debug to javafx.fxml;
    exports com.example.demo.level;
    opens com.example.demo.level to javafx.fxml;
    exports com.example.demo.user;
    opens com.example.demo.user to javafx.fxml;
    exports com.example.demo.view;
    opens com.example.demo.view to javafx.fxml;
    exports com.example.demo.view.gamescreens;
    opens com.example.demo.view.gamescreens to javafx.fxml;
}