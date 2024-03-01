package controller;

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
import services.ServiceProduit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ShowBackProduit implements Initializable {
    @FXML
    private TableColumn<Produit,String> colNom;
    @FXML
    private TableColumn<Produit,String> colDesc;
    @FXML
    private TableColumn<Produit,Integer> colCateg;
    @FXML
    private TableColumn<Produit,Double> colPrix;
    @FXML
    private TableColumn<Produit,Integer> colId;
    @FXML
    private TableView<Produit> TableProduits;

    public static int id_modif;

    private ServiceProduit sp = new ServiceProduit();

    @FXML
    public void OnSupprimerClicked(ActionEvent actionEvent) {

        if (getSelectedProduitId() != -1)
        {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setHeaderText("Confirmation");
            confirmationAlert.setContentText("Are you sure you want to delete this product?");
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Delete the product
                    try {
                        sp.delete(getSelectedProduitId());
                        afficherProduits();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    // Refresh the TableView or perform any other necessary actions
                }
            });
        }
        else
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setHeaderText("No Product Selected");
            warningAlert.setContentText("Please select a product before deleting.");
            warningAlert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void OnModifierClicked(ActionEvent actionEvent) throws IOException {
        if (getSelectedProduitId() != -1)
        {
            id_modif = getSelectedProduitId() ;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UpdateProduct.fxml"));
            Parent newSceneRoot = fxmlLoader.load();
            TableProduits.getScene().setRoot(newSceneRoot);

        }
        else
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setHeaderText("No Product Selected");
            warningAlert.setContentText("Please select a product before updating.");
            warningAlert.showAndWait();
        }
    }

    public void afficherProduits() throws SQLException {
        List<Produit> lu = sp.getAll();
        System.out.println(lu);
        ObservableList<Produit> list = FXCollections.observableArrayList(lu);

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCateg.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        TableProduits.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      try {
            afficherProduits();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSelectedProduitId() {
        Produit selectedProduit = TableProduits.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            return selectedProduit.getId_produit();
        }
        return -1; // Return -1 if no row is selected
    }

    @javafx.fxml.FXML
    public void OnRetourClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BackHome.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        TableProduits.getScene().setRoot(newSceneRoot);
    }
}
