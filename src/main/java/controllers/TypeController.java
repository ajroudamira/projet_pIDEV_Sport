package controllers;

import entities.TypeCours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceType;

import java.io.IOException;
import java.sql.SQLException;

public class TypeController {

    ServiceType serviceType = new ServiceType();

    @FXML
    private TextField tf_calories;

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_objective;

    @FXML
    void AfficherType(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AffichageTypeCours.fxml"));
            tf_nom.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AjouterType(ActionEvent event) {
        try {
            // Vérifier si les champs sont vides
            if (tf_nom.getText().isEmpty() || tf_objective.getText().isEmpty() || tf_description.getText().isEmpty() || tf_calories.getText().isEmpty()) {
                showAlert("Erreur", "Veuillez remplir tous les champs.");
                return;
            }

            // Vérifier l'unicité du nom
            if (serviceType.isNomExist(tf_nom.getText())) {
                showAlert("Erreur", "Un type avec ce nom existe déjà !");
                return;
            }

            // Vérifier la validité des calories
            try {
                int calories = Integer.parseInt(tf_calories.getText());
                if (calories < 0 || calories > 700) {
                    showAlert("Erreur", "Les calories doivent être un nombre entier positif et inférieur ou égal à 700.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez saisir un nombre entier pour les calories.");
                return;
            }

            // Vérifier que les champs nom, description et objective ne contiennent que des lettres
            if (!tf_nom.getText().matches("^[a-zA-Z]+$") || !tf_objective.getText().matches("^[a-zA-Z]+$") || !tf_description.getText().matches("^[a-zA-Z]+$")) {
                showAlert("Erreur", "Les champs Nom, Objective et Description ne doivent contenir que des lettres.");
                return;
            }

            // Ajouter le type
            serviceType.ajouter(new TypeCours(tf_nom.getText(), tf_objective.getText(), tf_description.getText(), Integer.parseInt(tf_calories.getText())));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Type ajouté avec succès !");
            alert.showAndWait();

        } catch(SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs SQL
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}