package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.io.IOException;

import entities.Cours;
import entities.TypeCours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ServiceType;
import javafx.scene.control.TableColumn.CellEditEvent;


public class AffichageTypeCoursController {
    public TextField tf_newcalories;
    public TextField tf_recherche;
    public ImageView rech;
    ServiceType serviceType= new ServiceType();


    @FXML
    private TableColumn<TypeCours, Integer> col_calories;

    @FXML
    private TableColumn<TypeCours, String> col_description;

    @FXML
    private TableColumn<TypeCours, String> col_nom;

    @FXML
    private TableColumn<TypeCours, String> col_objective;

    @FXML
    private TableView<TypeCours> tv_type;



    public ObservableList<TypeCours> data = FXCollections.observableArrayList();
    public int getCalories() {
        return Integer.parseInt(col_calories.getText());
    }
   /* @FXML
    void search(ActionEvent event) {
        String searchQuery = tf_recherche.getText().trim().toLowerCase();

        // Vérifiez si le champ de recherche n'est pas vide
        if (!searchQuery.isEmpty()) {
            // Filtrer les cours selon le nom de recherche
            ObservableList<TypeCours> filteredType = FXCollections.observableArrayList();

            for (TypeCours typeCours : tv_type.getItems()) {
                if (typeCours.getNom().toLowerCase().contains(searchQuery)) {
                    filteredType.add(typeCours);
                }
            }

            // Mettre à jour les données affichées dans le TableView avec les résultats de la recherche
            tv_type.setItems(filteredType);
        } else {
            // Si le champ de recherche est vide, réafficher tous les cours
            tv_type.setItems(data);
        }
    } */
    @FXML
    void initialize() {

        try {
            System.out.println(serviceType.afficher());
            ObservableList<TypeCours> types= FXCollections.observableList(serviceType.afficher());
            System.out.println(types);
            tv_type.setItems(types);
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_objective.setCellValueFactory(new PropertyValueFactory<>("objective"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_calories.setCellValueFactory(new PropertyValueFactory<>("calories"));




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SelectType(javafx.event.ActionEvent actionEvent) {
        TypeCours t = tv_type.getSelectionModel().getSelectedItem();
        col_nom.setText(t.getNom());
        col_calories.setText(String.valueOf(t.getCalories()));
        col_description.setText(t.getDescription());
        col_objective.setText(t.getObjective());
        System.out.println(t);
    }
   /* @FXML
    private void changecaloriesCellEvent(TableColumn.CellEditEvent<TypeCours, Integer> event) {

        TypeCours selectedTypeCours = tv_type.getRowValue();
        selectedTypeCours.setCalories(event.getNewValue());
    }*/

 /*   public void ModifierType(ActionEvent event)throws SQLException {
        if (tv_type.getSelectionModel().getSelectedItem() != null) {

           TypeCours t = tv_type.getSelectionModel().getSelectedItem();

            serviceType.modifier(t.getCalories(), t.getId());
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("edit");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("The type was successfully edit");
            BookAlert.showAndWait();

        } else {
            //Alert Select type :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a type");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a type first!");
            selectBookAlert.showAndWait();
            //Alert Select type !
        }
    } */


    public void SupprimerType(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (tv_type.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("Delete a type");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("Are you sure that you want to delete this type?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {

                TypeCours t = tv_type.getSelectionModel().getSelectedItem();
                serviceType.supprimer(t.getId());
                data.clear();
                data.addAll(serviceType.afficher());

                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("Delete Blog");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("cours deleted successfully!");

                succDeleteBookAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a cours");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a cours first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !
    }
}

 /*   public void changecaloriesCellEvent(CellEditEvent edittedCell) {
        TypeCours typeSelected = tv_type.getSelectionModel().getSelectedItem();
        typeSelected.setCalories(Integer.parseInt(edittedCell.getNewValue().toString()));

    }*/

    public void ModifierType(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (tv_type.getSelectionModel().getSelectedItem() != null) {
            TypeCours t = tv_type.getSelectionModel().getSelectedItem();
            String newCaloriesText = tf_newcalories.getText();
            if (!newCaloriesText.isEmpty()) {
                int newCalories = Integer.parseInt(newCaloriesText);
                serviceType.modifier(newCalories, t.getId());

                // Rafraîchir le TableView
                tv_type.refresh(); // Actualiser le TableView

                Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
                BookAlert.setTitle("edit");
                BookAlert.setHeaderText(null);
                BookAlert.setContentText("The type was successfully edit");
                BookAlert.showAndWait();
            } else {
                Alert emptyTextFieldAlert = new Alert(Alert.AlertType.WARNING);
                emptyTextFieldAlert.setTitle("Empty Field");
                emptyTextFieldAlert.setHeaderText(null);
                emptyTextFieldAlert.setContentText("Please enter a value for calories!");
                emptyTextFieldAlert.showAndWait();
            }
        } else {
            //Alert Select type :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a type");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a type first!");
            selectBookAlert.showAndWait();
            //Alert Select type !
        }
    }
   /*public void ModifierType(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (tv_type.getSelectionModel().getSelectedItem() != null) {

            TypeCours t = tv_type.getSelectionModel().getSelectedItem();

            serviceType.modifier(t.getCalories(), t.getId());
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("edit");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("The type was successfully edit");
            BookAlert.showAndWait();

        } else {
            //Alert Select type :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a type");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a type first!");
            selectBookAlert.showAndWait();
            //Alert Select type !
        }
    } */

    public void changecaloriesCellEvent(CellEditEvent<TypeCours, Integer> typeCoursIntegerCellEditEvent) {
        TypeCours typeSelected = tv_type.getSelectionModel().getSelectedItem();
        CellEditEvent<Object, Object> edittedCell = null;
        typeSelected.setCalories(Integer.parseInt(edittedCell.getNewValue().toString()));
    }


    public void RefreshType(ActionEvent actionEvent) {
        try {
            // Rafraîchir les données de votre TableView
            ObservableList<TypeCours> types = FXCollections.observableList(serviceType.afficher());
            tv_type.setItems(types);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trier(MouseEvent mouseEvent) {
        ObservableList<TypeCours> data = tv_type.getItems();

        // Trier les données par l'ordre alphabétique des cours
        data.sort(Comparator.comparing(TypeCours::getNom));

        // Mettre à jour les données dans la TableView
        tv_type.setItems(data);
    }

    public void RechercherType(MouseEvent mouseEvent) {
        String searchQuery = tf_recherche.getText().trim().toLowerCase();

        // Vérifiez si le champ de recherche n'est pas vide
        if (!searchQuery.isEmpty()) {
            // Filtrer les cours selon le nom de recherche
            ObservableList<TypeCours> filteredType = FXCollections.observableArrayList();

            for (TypeCours typeCours : tv_type.getItems()) {
                if (typeCours.getNom().toLowerCase().contains(searchQuery)) {
                    filteredType.add(typeCours);
                }
            }

            // Mettre à jour les données affichées dans le TableView avec les résultats de la recherche
            tv_type.setItems(filteredType);
        } else {
            // Si le champ de recherche est vide, réafficher tous les cours
            tv_type.setItems(data);
        }
    }

   /* public void RechercherType(MouseEvent mouseEvent) {
        String recherche = tf_recherche.getText().trim();
        if (!recherche.isEmpty()) {
            try {
                List<Cours> coursRecherches = serviceCours.rechercherParNom(recherche);
                if (!coursRecherches.isEmpty()) {
                    ObservableList<Cours> listeCours = FXCollections.observableArrayList(coursRecherches);
                    tv_cours.setItems(listeCours);
                } else {
                    afficherAlerte(Alert.AlertType.INFORMATION, "Aucun résultat", "Aucun cours trouvé pour cette recherche.");
                }
            } catch (SQLException e) {
                afficherAlerte(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de la recherche.");
            }
        } else {
            afficherAlerte(Alert.AlertType.WARNING, "Champ vide", "Veuillez entrer un nom de cours pour la recherche.");
        }
    }

    // Méthode utilitaire pour afficher une alerte
    private void afficherAlerte(Alert.AlertType type, String titre, String contenu) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    } */
    }


