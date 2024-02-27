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

public class CoursController {

    ServiceCours serviceCours = new ServiceCours() ;


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
            serviceCours.ajouter(new Cours(Integer.parseInt(tf_duree.getText()), tf_horaire.getText(), /*tf_id_type.getText(),*/ tf_nom.getText(), tf_salle.getText()));



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("cours ajoute");
            alert.showAndWait();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

  /*  @FXML
    void ModifierCours(ActionEvent event) {

    }

   @FXML
    void SupprimerCours(ActionEvent event) {

    } */




}
