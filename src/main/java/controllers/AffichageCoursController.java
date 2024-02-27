package controllers;

import entities.Cours;
import entities.TypeCours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceCours;

import java.sql.SQLException;
import java.util.Optional;

public class AffichageCoursController {
    public TextField tf_newnom;
    ServiceCours serviceCours = new ServiceCours() ;



    @FXML
    private TableColumn<Cours, Integer> col_duree;

    @FXML
    private TableColumn<Cours, Integer> col_horaire;

    @FXML
    private TableColumn<Cours, String> col_nom;

    @FXML
    private TableColumn<Cours, String> col_salle;

    @FXML
    private TableView<Cours> tv_cours;
    public ObservableList<Cours> data = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        try {
            ObservableList<Cours> courss = FXCollections.observableList(serviceCours.afficher());
            tv_cours.setItems(courss);
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
            col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            col_horaire.setCellValueFactory(new PropertyValueFactory<>("horaire"));



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SupprimerCours(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (tv_cours.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("Delete a cours");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("Are you sure that you want to delete this cours?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Cours c = tv_cours.getSelectionModel().getSelectedItem();
                serviceCours.supprimer(c.getId());

                // Mise à jour de la liste des données
                data.clear();
                data.addAll(serviceCours.afficher());

                // Rafraîchissement du TableView
                tv_cours.refresh();

                // Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("Delete Blog");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("cours deleted successfully!");
                succDeleteBookAlert.showAndWait();
            }
        } else {
            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a cours");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a cours first!");
            selectBookAlert.showAndWait();
        }
    }



    public void SelectCours(javafx.event.ActionEvent actionEvent) {
        Cours c = tv_cours.getSelectionModel().getSelectedItem();
        col_nom.setText(c.getNom());
        col_duree.setText(String.valueOf(c.getDuree()));
        col_horaire.setText(String.valueOf(c.getHoraire()));
        col_salle.setText(c.getSalle());
        System.out.println(c);
    }

    public void changecnomCellEvent(TableColumn.CellEditEvent<Cours, String> coursStringCellEditEvent) {
        Cours coursSelected = tv_cours.getSelectionModel().getSelectedItem();
        TableColumn.CellEditEvent<Object, Object> edittedCell = null;
        coursSelected.setNom(String.valueOf(Integer.parseInt(edittedCell.getNewValue().toString())));
    }



    public void ModifierCours(ActionEvent actionEvent) throws SQLException {
        if (tv_cours.getSelectionModel().getSelectedItem() != null) {
            Cours c = tv_cours.getSelectionModel().getSelectedItem();
            String newNomText = tf_newnom.getText();
            if (!newNomText.isEmpty()) {
                // Mettre à jour le nom du cours seulement si le champ n'est pas vide
                serviceCours.modifier(newNomText, c.getId());
                c.setNom(newNomText); // Mettre à jour la valeur dans l'objet Cours
                tv_cours.refresh(); // Rafraîchir le TableView pour refléter les changements
                Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
                BookAlert.setTitle("edit");
                BookAlert.setHeaderText(null);
                BookAlert.setContentText("The cours was successfully edited");
                BookAlert.showAndWait();
            } else {
                Alert emptyTextFieldAlert = new Alert(Alert.AlertType.WARNING);
                emptyTextFieldAlert.setTitle("Empty Field");
                emptyTextFieldAlert.setHeaderText(null);
                emptyTextFieldAlert.setContentText("Please enter a value for nom!");
                emptyTextFieldAlert.showAndWait();
            }
        } else {
            Alert selectCoursAlert = new Alert(Alert.AlertType.WARNING);
            selectCoursAlert.setTitle("Select a cours");
            selectCoursAlert.setHeaderText(null);
            selectCoursAlert.setContentText("You need to select a cours first!");
            selectCoursAlert.showAndWait();
        }
    }
    }


