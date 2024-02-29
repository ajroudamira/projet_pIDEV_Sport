package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Cours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceCours;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CoursController {

    ServiceCours serviceCours = new ServiceCours();

    @FXML
    private TextField tf_duree;

    @FXML
    private TextField tf_horaire;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_salle;

    @FXML
    void AfficherCours(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AffichageCours.fxml"));
            tf_duree.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AjouterCours(ActionEvent event) {
        try {
            // Vérifier si la durée contient uniquement des chiffres
            if (!isNumeric(tf_duree.getText())) {
                showAlert("Erreur", "La durée doit contenir uniquement des chiffres !");
                return;
            }

            // Vérifier si le nom contient uniquement des lettres
            if (!isAlpha(tf_nom.getText())) {
                showAlert("Erreur", "Le nom ne peut contenir que des lettres !");
                return;
            }

          /*  // Vérifier si la salle contient uniquement des lettres
            if (!isAlpha(tf_salle.getText())) {
                showAlert("Erreur", "La salle ne peut contenir que dessss lettres !");
                return;
            }*/

            // Vérifier l'unicité du nom
            if (serviceCours.isNomExist(tf_nom.getText())) {
                showAlert("Erreur", "Un cours avec ce nom existe déjà !");
                return;
            }

            // Ajouter le cours si toutes les validations sont passées
            serviceCours.ajouter(new Cours(Integer.parseInt(tf_duree.getText()), tf_horaire.getText(), tf_nom.getText(), tf_salle.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours ajouté avec succès !");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs SQL
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour vérifier si une chaîne contient uniquement des chiffres
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Méthode pour vérifier si une chaîne contient uniquement des lettres
    private boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }
}