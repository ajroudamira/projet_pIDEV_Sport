package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class bgController {


    public Button tf_type;
    public Button tf_cours;
    public ImageView us;

    @FXML
    void AfficheraffCours(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Cours.fxml"));
            tf_cours.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    void AfficheraffType(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Type.fxml"));
            tf_type.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void goclient(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AffichageJointure.fxml"));
            tf_type.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}




