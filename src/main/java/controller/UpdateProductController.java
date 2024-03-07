package controller;

import entities.Categorie;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import services.ServiceCategorie;
import services.ServiceProduit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateProductController implements Initializable {
    @FXML
    private TextField tf_nom;
    @FXML
    private TextArea tf_desc;
    @FXML
    private ComboBox<Categorie> ComboCatrgorie;
    @FXML
    private TextField tf_prix;
    private ServiceProduit sp = new ServiceProduit();
    private ServiceCategorie sc = new ServiceCategorie();

    private int id_modif;
    @FXML
    public void OnModifierClicked(ActionEvent actionEvent) throws SQLException, IOException {
        Produit p = new Produit();

        p.setId_produit(id_modif);
        p.setNom(tf_nom.getText());
        p.setPrix(Double.parseDouble(tf_prix.getText()));
        p.setDescription(tf_desc.getText());
        p.setId_categorie(ComboCatrgorie.getValue().getId_categorie());

        sp.update(p);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ShowBackProduit.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        tf_nom.getScene().setRoot(newSceneRoot);
    }

    @FXML
    public void GoBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ShowBackProduit.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        tf_nom.getScene().setRoot(newSceneRoot);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Categorie> observableCategories;
        try {
            observableCategories = FXCollections.observableArrayList(sc.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ComboCatrgorie.getItems().addAll(observableCategories);
            id_modif = ShowBackProduit.id_modif ;
            Produit p = new Produit();
        try {
             p = sp.findOneById(id_modif);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tf_nom.setText(p.getNom());
        tf_desc.setText(p.getDescription());
        tf_prix.setText(String.valueOf(p.getPrix()));
        Categorie c = new Categorie();
        try {
             c = sc.findOneById(p.getId_categorie());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ComboCatrgorie.setValue(c);

    }
}
