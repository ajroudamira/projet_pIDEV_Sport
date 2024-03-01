package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Cours;
import entities.TypeCours;
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

  //  public TextField tf_typecours;
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
    private TextField tf_typecours;
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
            if (!isNumeric(tf_duree.getText())) {
                showAlert("Erreur", "La duree doit contenir uniquement des chiffres !");
                return;
            }

            if (!isAlpha(tf_nom.getText())) {
                showAlert("Erreur", "Le nom ne peut contenir que des lettres !");
                return;
            }

            if (serviceCours.isNomExist(tf_nom.getText())) {
                showAlert("Erreur", "Un cours avec ce nom existe deja !");
                return;
            }

            // Chercher  TypeCours par son nom
            TypeCours types = serviceCours.getTypeCoursByNom(tf_typecours.getText());

            if (types != null) {
                // Creer un nouvel objet Cours en utilisant l'objet TypeCours obtenu
                Cours nouveauCours = new Cours(
                        Integer.parseInt(tf_duree.getText()),
                        tf_nom.getText(),
                        tf_salle.getText(),
                        tf_horaire.getText(),
                        types
                );

                // najouti cours jdid b service
                serviceCours.ajouter(nouveauCours);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Cours ajoute avec succes !");
                alert.showAndWait();
            } else {
                System.out.println("Le type de cours specifie est introuvable.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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