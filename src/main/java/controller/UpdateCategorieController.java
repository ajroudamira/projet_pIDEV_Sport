package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Categorie;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceCategorie;
import services.ServiceProduit;

public class UpdateCategorieController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label errorLabelDesc2;

    @FXML
    private Label errorLabelNom2, lb_id;

    @FXML
    private TextArea tf_Desc;

    @FXML
    private TextField tf_nom;
    private ServiceCategorie sc = new ServiceCategorie();
    private ServiceProduit sp = new ServiceProduit();
    private static Categorie cat;
    public static void setCateg(Categorie categ){categ=cat;}
    private int id_modif;

    @FXML
    void OnAddClicked(ActionEvent event) throws IOException, SQLException {
        Categorie p = new Categorie();
        lb_id.setText(String.valueOf(id_modif));
        p.setId_categorie(id_modif);
        p.setNom(tf_nom.getText());
        p.setDescription(tf_Desc.getText());

        sc.update(p);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficherCategorie.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        tf_nom.getScene().setRoot(newSceneRoot);
    }

    @FXML
    void OnRetourClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ShowBackProduit.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        tf_nom.getScene().setRoot(newSceneRoot);

    }

    @FXML
    void initialize() {
        id_modif = AfficherCategorieController.id_modif;
    }

}
