package controller;

import entities.Categorie;
import entities.Produit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.RatingService;
import services.ServiceCategorie;
import services.ServiceProduit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FrontProduit implements Initializable {


    @javafx.fxml.FXML
    private ScrollPane scrollPane;
    private ServiceProduit serviceProduit = new ServiceProduit();
    private RatingService ratingService = new RatingService();

    private ServiceCategorie serviceCategorie = new ServiceCategorie();
    private final int id_user = 2 ;
    @javafx.fxml.FXML
    private TextField SearchField;
    @javafx.fxml.FXML
    private ComboBox<Categorie> ComboCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ComboCategory.getItems().addAll(this.serviceCategorie.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            afficherProduits(serviceProduit.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SearchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Handle the text change event here
                System.out.println("Text changed from: " + oldValue + " to: " + newValue);
                List<Produit> lp = null;
                try {
                    lp = serviceProduit.getAll().stream().filter(produit -> {
                        return produit.getNom().contains(newValue);
                    }).toList();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    afficherProduits(lp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ComboCategory.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    List<Produit> lp = this.serviceProduit.getAll().stream().filter(produit -> {
                       return produit.getId_categorie() == newValue.getId_categorie() ;
                    }).toList();

                    afficherProduits(lp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Selected category: " + newValue.getNom());
            }
        });
    }


    public void afficherProduits(List<Produit> Produits) throws SQLException {


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

    private VBox createPrduitBox(Produit p) throws SQLException {


        Rating rating = new Rating();
        rating.setPartialRating(true); // Enable partial ratings
        rating.setMax(5); // Set maximum rating
        Double totale;
        Label ratingLabel = new Label("Rating: ");



        ServiceProduit sp = new ServiceProduit();
        VBox box = new VBox();
        ImageView imageView = new ImageView(new Image(new File(p.getImagePath()).toURI().toString()));
      //  ImageView imageView = new ImageView(new Image(p.getImagePath()));
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
        totale = ratingService.GetRatingParProduit(p.getId_produit(),id_user);
        ratingLabel.setText(" Total Ratings: " + String.format("%.2f",totale));
        rating.ratingProperty().setValue(totale);

        rating.ratingProperty().addListener((observable, oldValue, newValue) -> {
            entities.Rating r = new entities.Rating(id_user,p.getId_produit(),Double.parseDouble(newValue.toString()));
            Double tot;
            System.out.println("id produit" + p.getId_produit());
            try {
                ratingService.create(r);

            }
            catch(SQLException e)
            {
                try {
                    ratingService.update(r);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
            try {
                 tot = this.ratingService.GetRatingParProduit(p.getId_produit(),id_user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ratingLabel.setText(" Total Ratings: " + String.format("%.2f",tot));
           // Nom.setText("Rating " + newValue);
        });

        box.getChildren().addAll(Nom, Desc, prix,rating,ratingLabel);

        return box;
    }


    @javafx.fxml.FXML
    public void OnSearchTextChanged(Event event) throws SQLException {
       /* String input = SearchField.getText();
        List<Produit> lp = serviceProduit.getAll().stream().filter(produit -> {
            return produit.getNom().contains(input);
        }).toList();

        afficherProduits(lp);*/

    }
}
