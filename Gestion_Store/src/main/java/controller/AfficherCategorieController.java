package controller;

import com.mysql.cj.xdevapi.Table;
import entities.Categorie;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceCategorie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCategorieController implements Initializable {
    @javafx.fxml.FXML
    private TableView<Categorie> TableCat;
    @javafx.fxml.FXML
    private TableColumn<Categorie,String> colNom;
    @javafx.fxml.FXML
    private TableColumn<Categorie,String> colDesc;
    @javafx.fxml.FXML
    private TableColumn<Categorie,Integer> colId;

    private ServiceCategorie serviceCategorie = new ServiceCategorie();
    public static int id_modif;


    @FXML
    public void OnSupprimerClicked(ActionEvent actionEvent) {
        Categorie selectedCategorie = getSelectedCat();
        if (selectedCategorie != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setHeaderText("Confirmation");
            confirmationAlert.setContentText("Are you sure you want to delete this category?");
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Delete the category
                    try {
                        serviceCategorie.delete(selectedCategorie.getId_categorie());
                        afficherCats();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    // Refresh the TableView or perform any other necessary actions
                }
            });
        } else {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setHeaderText("No Category Selected");
            warningAlert.setContentText("Please select a category before deleting.");
            warningAlert.showAndWait();
        }
    }


    @javafx.fxml.FXML
    public void OnREtourClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BackHome.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        TableCat.getScene().setRoot(newSceneRoot);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            afficherCats();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Categorie getSelectedCat() {
        return TableCat.getSelectionModel().getSelectedItem();
    }


    public void afficherCats() throws SQLException {
        List<Categorie> lu = serviceCategorie.getAll();
        System.out.println(lu);
        ObservableList<Categorie> list = FXCollections.observableArrayList(lu);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        TableCat.setItems(list);
    }
    public int getSelectedProduitId() {
        Categorie selectedProduit = TableCat.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            return selectedProduit.getId_categorie();
        }
        return -1; // Return -1 if no row is selected
    }
    @javafx.fxml.FXML
    public void OnModifierClicked(ActionEvent actionEvent) throws IOException {
        if (getSelectedProduitId() != -1)
        {
            id_modif = getSelectedProduitId() ;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UpdateCategorie.fxml"));
            Parent newSceneRoot = fxmlLoader.load();
            TableCat.getScene().setRoot(newSceneRoot);
        }
        else
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setHeaderText("No Product Selected");
            warningAlert.setContentText("Please select a product before updating.");
            warningAlert.showAndWait();
        }
    }

}
