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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import services.ServiceType;
import javafx.scene.control.TableColumn.CellEditEvent;


public class AffichageTypeCoursController {
    public TextField tf_newcalories;
    public TextField tf_recherche;
    public ImageView rech;
    public Label tf_cours;
    public Label tf_back;
    public ImageView us;
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
 /*   @FXML
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
    } */
    @FXML
    void initialize() {
        try {
            ObservableList<TypeCours> types = FXCollections.observableList(serviceType.afficher());
            tv_type.setItems(types);
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_objective.setCellValueFactory(new PropertyValueFactory<>("objective"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_calories.setCellValueFactory(new PropertyValueFactory<>("calories"));

            // Activation de la sélection au simple clic
            tv_type.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            // Activation de l'édition des cellules
            col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
            col_objective.setCellFactory(TextFieldTableCell.forTableColumn());
            col_calories.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
             col_description.setCellFactory(TextFieldTableCell.forTableColumn());

            // Gestion de l'événement de modification de la cellule pour chaque colonne
            col_nom.setOnEditCommit(event -> {
                TypeCours typeCours = event.getRowValue();
                typeCours.setNom(event.getNewValue());
                ModifierType(typeCours);
            });

            col_objective.setOnEditCommit(event -> {
                TypeCours typeCours = event.getRowValue();
                typeCours.setObjective(event.getNewValue());
                ModifierType(typeCours);
            });

            col_calories.setOnEditCommit(event -> {
                TypeCours typeCours = event.getRowValue();
                typeCours.setCalories(event.getNewValue());
                ModifierType(typeCours);
            });

            col_description.setOnEditCommit(event -> {
                TypeCours typeCours = event.getRowValue();
                typeCours.setDescription(event.getNewValue());
                ModifierType(typeCours);
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   /* public void ModifierType(TypeCours typeCours) {
        try {
            serviceType.modifier(typeCours.getNom(), typeCours.getObjective(),typeCours.getDescription(),typeCours.getCalories() , typeCours.getId());
            // Afficher une alerte pour confirmer la modification
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Modification réussie");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Le cours a été modifié avec succès.");
            successAlert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } */

/*    public void SelectType(javafx.event.ActionEvent actionEvent) {
        TypeCours t = tv_type.getSelectionModel().getSelectedItem();
        col_nom.setText(t.getNom());
        col_calories.setText(String.valueOf(t.getCalories()));
        col_description.setText(t.getDescription());
        col_objective.setText(t.getObjective());
        System.out.println(t);
    } */
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


   /* public void SupprimerCours(javafx.event.ActionEvent actionEvent) throws SQLException {
       TypeCours selectedType = tv_type.getSelectionModel().getSelectedItem();
        if (selectedType != null) {
            try {
                // Supprimer le cours sélectionné
                serviceType.supprimer(selectedType.getId());

                // Mettre à jour la TableView
                data.clear();
                data.addAll(serviceType.afficher());
                tv_type.refresh();

                // Afficher une alerte pour confirmer la suppression
                Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                deleteAlert.setTitle("Delete");
                deleteAlert.setHeaderText(null);
                deleteAlert.setContentText("Cours deleted successfully!");
                deleteAlert.showAndWait();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // Aucune vérification de sélection nécessaire ici
            Alert selectAlert = new Alert(Alert.AlertType.WARNING);
            selectAlert.setTitle("Select a cours");
            selectAlert.setHeaderText(null);
            selectAlert.setContentText("You need to select a cours first!");
            selectAlert.showAndWait();
        }
    } */


 /*   public void changecaloriesCellEvent(CellEditEvent edittedCell) {
        TypeCours typeSelected = tv_type.getSelectionModel().getSelectedItem();
        typeSelected.setCalories(Integer.parseInt(edittedCell.getNewValue().toString()));

    }*/

   /* public void ModifierType(javafx.event.ActionEvent actionEvent) throws SQLException {
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
    } */
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

            ObservableList<TypeCours> types = FXCollections.observableList(serviceType.afficher());
            tv_type.setItems(types);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trier(MouseEvent mouseEvent) {
        ObservableList<TypeCours> data = tv_type.getItems();

        // Tri ordre alph  cours
        data.sort(Comparator.comparing(TypeCours::getNom));

        tv_type.setItems(data);
    }

    public void RechercherType(MouseEvent mouseEvent) {
        String searchQuery = tf_recherche.getText().trim().toLowerCase();

        // Vérif  champ de recherche n'est pas vide
        if (!searchQuery.isEmpty()) {
            // Filtrer  cours selon le nom de recherche
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

    public void AfficheraffCours(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Cours.fxml"));
            tf_cours.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void AffichBg(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/bg.fxml"));
            tf_back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SelectType(ActionEvent actionEvent) {
    }

    public void ModifierType(TypeCours typeCours) {
        try {
            serviceType.modifier(typeCours.getNom(), typeCours.getObjective(), typeCours.getDescription(), typeCours.getCalories(), typeCours.getId());
            // Afficher une alerte pour confirmer la modification
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Modification réussie");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Le cours a été modifié avec succès.");
            successAlert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SupprimerType(ActionEvent actionEvent) {
        TypeCours selectedType = tv_type.getSelectionModel().getSelectedItem();
        if (selectedType != null) {
            try {
                // Supprimer le cours sélectionné
                serviceType.supprimer(selectedType.getId());

                // Mettre à jour la TableView
                data.clear();
                data.addAll(serviceType.afficher());
                tv_type.refresh();

                // Afficher une alerte pour confirmer la suppression
                Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                deleteAlert.setTitle("Delete");
                deleteAlert.setHeaderText(null);
                deleteAlert.setContentText("Cours deleted successfully!");
                deleteAlert.showAndWait();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            // Aucune vérification de sélection nécessaire ici
            Alert selectAlert = new Alert(Alert.AlertType.WARNING);
            selectAlert.setTitle("Select a cours");
            selectAlert.setHeaderText(null);
            selectAlert.setContentText("You need to select a cours first!");
            selectAlert.showAndWait();
        }
    }

    public void refreshCours(MouseEvent mouseEvent) {
        try {
            ObservableList<TypeCours> types = FXCollections.observableList(serviceType.afficher());
            tv_type.setItems(types);
        } catch (SQLException e) {
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



