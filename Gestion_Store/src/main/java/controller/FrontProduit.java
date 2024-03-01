package controller;

import entities.Produit;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.ServiceProduit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FrontProduit implements Initializable {


    @javafx.fxml.FXML
    private ScrollPane scrollPane;
    private ServiceProduit serviceProduit = new ServiceProduit();

    public void afficherProduits(List<Produit> Produits) {


        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);//
        //hBox.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: black; -fx-border-width: 2px;");

        int count = 0;
        for (Produit p : Produits) {
            VBox box = createPrduitBox(p);
            box.setPrefWidth(300);
            box.setPrefHeight(300);
            hBox.getChildren().add(box);
            count++;

            if (count == 3) {
                vBox.getChildren().add(hBox);
                hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(100); //
                // hBox.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: black; -fx-border-width: 2px;");
                count = 0;
            }
        }

        if (count > 0) {
            vBox.getChildren().add(hBox);
        }

        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private VBox createPrduitBox(Produit p) {


        ServiceProduit sp = new ServiceProduit();
        VBox box = new VBox();

        ImageView imageView = new ImageView(new Image("/tttttttt.jpg"));
        box.setStyle("-fx-background-color: linear-gradient(to bottom, #BBBBBB, #999999); -fx-background-radius: 20;");

        box.getChildren().add(imageView);

        box.setMaxSize(500, 500);
        box.setSpacing(50);
        box.setAlignment(Pos.CENTER);

        Insets I = new Insets(20, 0, 20, 0);
        box.setPadding(I);
        box.setOpacity(0.7);
        Glow glow = new Glow();
        DropShadow shadow = new DropShadow();
        box.setEffect(shadow);
        box.setUserData(p.getId_produit()); // set the ID as the user data for the VBox

        Label Nom = new Label(p.getNom());
        Label Desc = new Label(p.getDescription());
        Label prix = new Label(Double.toString(p.getPrix()) + " DT");

        Nom.setStyle("-fx-text-fill: #acaaad;");
        Nom.setWrapText(true);
        Nom.setAlignment(Pos.CENTER);
        Nom.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Desc.setTextFill(Color.WHITE);
        Desc.setFont(Font.font("Arial", FontWeight.BOLD, 19));
        prix.setFont(Font.font("Arial", FontWeight.BOLD, 19));
        prix.setTextFill(Color.WHITE);

        box.getChildren().addAll(Nom, Desc, prix);
        return box;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            afficherProduits(serviceProduit.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
