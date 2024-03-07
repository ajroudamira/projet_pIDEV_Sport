package controller;

import entities.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceCategorie;
import services.ServiceProduit;

import java.io.IOException;
import java.sql.SQLException;

public class AddCategorieController {
    @FXML
    private TextArea tf_Desc;
    @FXML
    private TextField tf_nom;
    @FXML
    private Label errorLabelNom2;
    @FXML
    private Label errorLabelDesc2;
    private ServiceCategorie serviceCategorie = new ServiceCategorie();

    @FXML
    public void OnRetourClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BackHome.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        tf_Desc.getScene().setRoot(newSceneRoot);
    }

    @FXML
    public void OnAddClicked(ActionEvent actionEvent) throws SQLException {

        String nom = tf_nom.getText().trim();
        String description = tf_Desc.getText().trim();

        // Vérification du champ nom
        if (nom.isEmpty() || !nom.matches("[a-zA-Z]+")) {
            errorLabelNom2.setText("Le champ nom est vide ou contient des caractères non autorisés.");
            return;
        } else {
            errorLabelNom2.setText(""); // Effacer le message d'erreur s'il est valide
        }

        // Vérification du champ description
        if (description.isEmpty() || !description.matches("[a-zA-Z]+")) {
            errorLabelDesc2.setText("Le champ description est vide ou contient des caractères non autorisés.");
            return;
        } else {
            errorLabelDesc2.setText(""); // Effacer le message d'erreur s'il est valide
        }
    }
}
