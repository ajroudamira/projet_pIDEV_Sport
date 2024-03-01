package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BackHomeController {
    @FXML
    private Button btnGoAjputer;

    @FXML
    public void GoAfficher(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ShowBackProduit.fxml"));

        btnGoAjputer.getScene().setRoot(root);

    }

    @FXML
    public void GoAjout(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddProduct.fxml"));
            Parent newSceneRoot = fxmlLoader.load();
            btnGoAjputer.getScene().setRoot(newSceneRoot);

        } catch (IOException e) {
            e.printStackTrace();
            // Afficher une boîte de dialogue d'erreur pour informer l'utilisateur.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème lors de l'ouverture de la nouvelle scène");
            alert.setContentText("Impossible de charger la vue : " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void GoAddCategorie(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addCategorie.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        btnGoAjputer.getScene().setRoot(newSceneRoot);
    }

    @FXML
    public void GoAfficherCategorie(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficherCategorie.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        btnGoAjputer.getScene().setRoot(newSceneRoot);
    }
}
