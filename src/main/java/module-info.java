module tn.esprit {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.apache.poi.ooxml;
    requires com.google.zxing;
    requires javafx.swing;
    exports tn.esprit.controller;
    opens tn.esprit.controller to javafx.fxml;
    exports tn.esprit;
    opens tn.esprit to javafx.fxml;

}