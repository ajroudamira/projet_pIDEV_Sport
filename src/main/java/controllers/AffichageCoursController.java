package controllers;

import entities.Cours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import services.ServiceCours;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Optional;

public class AffichageCoursController {
    public TextField tf_newnom;
    public ImageView rech;
    public TextField tf_recherche;
    public Label tf_type;
    public Label tf_back;
    public ImageView us;

    ServiceCours serviceCours = new ServiceCours();


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

            // Activation de la sélection au simple clic
            tv_cours.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            // Activation de l'édition des cellules
            col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
            col_salle.setCellFactory(TextFieldTableCell.forTableColumn());
            col_duree.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            //  col_horaire.setCellFactory(TextFieldTableCell.forTableColumn());

            // Gestion de l'événement de modification de la cellule pour chaque colonne
            col_nom.setOnEditCommit(event -> {
                Cours cours = event.getRowValue();
                cours.setNom(event.getNewValue());
                ModifierCours(cours);
            });

            col_salle.setOnEditCommit(event -> {
                Cours cours = event.getRowValue();
                cours.setSalle(event.getNewValue());
                ModifierCours(cours);
            });

            col_duree.setOnEditCommit(event -> {
                Cours cours = event.getRowValue();
                cours.setDuree(Integer.parseInt(String.valueOf(event.getNewValue())));
                ModifierCours(cours);
            });

            col_horaire.setOnEditCommit(event -> {
                Cours cours = event.getRowValue();
                cours.setHoraire(String.valueOf(event.getNewValue()));
                ModifierCours(cours);
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


  /*  public void SupprimerCours(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (tv_cours.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("Delete a cours");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("Are you sure that you want to delete this cours?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Cours c = tv_cours.getSelectionModel().getSelectedItem();
                serviceCours.supprimer(c.getId());

                // Mise ajour  liste des données
                data.clear();
                data.addAll(serviceCours.afficher());

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
    }*/
  public void SupprimerCours(javafx.event.ActionEvent actionEvent) throws SQLException {
      Cours selectedCours = tv_cours.getSelectionModel().getSelectedItem();
      if (selectedCours != null) {
          try {
              // Supprimer le cours sélectionné
              serviceCours.supprimer(selectedCours.getId());

              // Mettre à jour la TableView
              data.clear();
              data.addAll(serviceCours.afficher());
              tv_cours.refresh();

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


   /* public void SelectCours(javafx.event.ActionEvent actionEvent) {
        Cours c = tv_cours.getSelectionModel().getSelectedItem();
        col_nom.setText(c.getNom());
        col_duree.setText(String.valueOf(c.getDuree()));
        col_horaire.setText(String.valueOf(c.getHoraire()));
        col_salle.setText(c.getSalle());
        System.out.println(c);
    }*/

    public void changecnomCellEvent(TableColumn.CellEditEvent<Cours, String> coursStringCellEditEvent) {
        Cours coursSelected = tv_cours.getSelectionModel().getSelectedItem();
        TableColumn.CellEditEvent<Object, Object> edittedCell = null;
        coursSelected.setNom(String.valueOf(Integer.parseInt(edittedCell.getNewValue().toString())));
    }



  /* public void ModifierCours(ActionEvent actionEvent) throws SQLException {
        if (tv_cours.getSelectionModel().getSelectedItem() != null) {
            Cours c = tv_cours.getSelectionModel().getSelectedItem();
            String newNomText = tf_newnom.getText();
            if (!newNomText.isEmpty()) {
                // Récupérer les valeurs de salle, duree et horaire à partir de votre TableView
                String salle = c.getSalle();
                int duree = c.getDuree();
                String horaire = c.getHoraire();

                // Appeler la méthode modifier avec les valeurs récupérées
                serviceCours.modifier(newNomText, salle, duree, horaire, c.getId());

                // Mettre à jour la TableView
                tv_cours.refresh();

                // Afficher une alerte pour confirmer la modification
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Modification réussie");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Le cours a été modifié avec succès.");
                successAlert.showAndWait();
            } else {
                // Afficher une alerte si le champ de nouveau nom est vide
                Alert emptyTextFieldAlert = new Alert(Alert.AlertType.WARNING);
                emptyTextFieldAlert.setTitle("Champ vide");
                emptyTextFieldAlert.setHeaderText(null);
                emptyTextFieldAlert.setContentText("Veuillez entrer une valeur pour le nom !");
                emptyTextFieldAlert.showAndWait();
            }
        } else {
            // Afficher une alerte si aucun cours n'est sélectionné
            Alert selectCoursAlert = new Alert(Alert.AlertType.WARNING);
            selectCoursAlert.setTitle("Sélectionnez un cours");
            selectCoursAlert.setHeaderText(null);
            selectCoursAlert.setContentText("Vous devez sélectionner un cours !");
            selectCoursAlert.showAndWait();
        }
    } */


  /*  public void RefreshCours(ActionEvent actionEvent) {
        try {
            ObservableList<Cours> courss = FXCollections.observableList(serviceCours.afficher());
            tv_cours.setItems(courss);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } */

    public void trier(MouseEvent mouseEvent) {
        ObservableList<Cours> data = tv_cours.getItems();

        // Trier les données par l'ordre alphabétique des cours
        data.sort(Comparator.comparing(Cours::getNom));

        // Mettre à jour les données dans la TableView
        tv_cours.setItems(data);
    }

    public void RechercherCours(MouseEvent mouseEvent) {
        String searchQuery = tf_recherche.getText().trim().toLowerCase();

        // Vérif  champ de recherche n'est pas vide
        if (!searchQuery.isEmpty()) {
            // Filtrer cours selon  nom
            ObservableList<Cours> filteredCours = FXCollections.observableArrayList();

            for (Cours cours : tv_cours.getItems()) {
                if (cours.getNom().toLowerCase().contains(searchQuery)) {
                    filteredCours.add(cours);
                }
            }

            tv_cours.setItems(filteredCours);
        } else {
            // Si champ de recherche  vide réafficher tous les cours
            tv_cours.setItems(data);
        }
    }

    public void AfficheraffType(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Type.fxml"));
            tf_type.getScene().setRoot(root);
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

    public void ModifierCours(Cours cours) {
        try {
            serviceCours.modifier(cours.getNom(), cours.getSalle(), cours.getDuree(), cours.getHoraire(), cours.getId());
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




    public void refreshCours(MouseEvent mouseEvent) {
        try {
            ObservableList<Cours> courss = FXCollections.observableList(serviceCours.afficher());
            tv_cours.setItems(courss);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void AfficherMail(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/mail.fxml"));
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






