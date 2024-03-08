package controllers;

import entities.Cours;
import entities.TypeCours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ServiceCours;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CoursController {

    public Label tf_back;
    public ImageView us;
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
    private ComboBox<TypeCours> combo;

    @FXML
    void initialize() {
        chargerTypesCours();
    }

    private void chargerTypesCours() {
        try {
            List<TypeCours> types = serviceCours.getAllTypesCours();
            ObservableList<TypeCours> observableTypesCours = FXCollections.observableArrayList(types);
            combo.setItems(observableTypesCours);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger les types de cours.");
        }
    }

    @FXML
    void AfficherCours(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AffichageCours.fxml"));
            tf_duree.getScene().setRoot(root);
        } catch (IOException e) {
            showAlert("Erreur", "Impossible de charger l'affichage des cours.");
        }
    }

    @FXML
    void AjouterCours(ActionEvent event) {
        try {
            if (!isNumeric(tf_duree.getText())) {
                showAlert("Erreur", "La durée doit contenir uniquement des chiffres !");
                return;
            }

            if (!isAlpha(tf_nom.getText())) {
                showAlert("Erreur", "Le nom ne peut contenir que des lettres !");
                return;
            }

            TypeCours selectedTypeCours = combo.getSelectionModel().getSelectedItem();
            if (selectedTypeCours == null) {
                showAlert("Erreur", "Veuillez sélectionner un type de cours !");
                return;
            }

            Cours nouveauCours = new Cours(
                    Integer.parseInt(tf_duree.getText()),
                    tf_nom.getText(),
                    tf_salle.getText(),
                    tf_horaire.getText(),
                    selectedTypeCours
            );

            serviceCours.ajouter(nouveauCours);

            showAlert("Succès", "Cours ajouté avec succès !");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de l'ajout du cours : " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }

    public void AffichBg(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/bg.fxml"));
            tf_back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void goclient(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AffichageJointure.fxml"));
            tf_back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}