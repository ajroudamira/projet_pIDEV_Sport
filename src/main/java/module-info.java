module tn.esprit {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    exports tn.esprit.controller;
    opens tn.esprit.controller to javafx.fxml;
    exports tn.esprit;
    opens tn.esprit to javafx.fxml;

}