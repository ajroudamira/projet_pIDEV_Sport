package controller;

import entities.Categorie;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import services.Mailing;
import services.ServiceCategorie;
import services.ServiceProduit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    @FXML
    private TextField tf_nom;
    @FXML
    private TextArea tf_desc;
    @FXML
    private ComboBox<Categorie> ComboCatrgorie;
    @FXML
    private Label errorLabelNom;
    @FXML
    private Label errorLabelDesc;
    @FXML
    private Label errorLabelPrix;
    @FXML
    private TextField tf_prix;

    private ServiceCategorie sc = new ServiceCategorie();
    private ServiceProduit sp = new ServiceProduit();
    @FXML
    private Button addImageButton;
    @FXML
    private Button ajouterProduit;

    private String ImagePath = "src/main/resources/tttttttt.jpg";
    @FXML
    private ImageView imageView;

    @FXML
    public void GoBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BackHome.fxml"));
        Parent newSceneRoot = fxmlLoader.load();
        tf_desc.getScene().setRoot(newSceneRoot);
    }

    @FXML
    public void OnAjouterClicked(ActionEvent actionEvent) throws SQLException {
        String nom = tf_nom.getText().trim();
        String description = tf_desc.getText().trim();
        String prixText = tf_prix.getText().trim();

        // Vérification du champ nom
        if (nom.isEmpty() || !nom.matches("[a-zA-Z]+")) {
            errorLabelNom.setText("Le champ nom est vide ou contient des caractères non autorisés.");
            return;
        } else {
            errorLabelNom.setText(""); // Effacer le message d'erreur s'il est valide
        }

        // Vérification du champ description
        if (description.isEmpty() || !description.matches("[a-zA-Z]+")) {
            errorLabelDesc.setText("Le champ description est vide ou contient des caractères non autorisés.");
            return;
        } else {
            errorLabelDesc.setText(""); // Effacer le message d'erreur s'il est valide
        }

        // Vérification du champ prix
        if (prixText.isEmpty() || !prixText.matches("\\d+")) {
            errorLabelPrix.setText("Le champ prix est vide ou contient des caractères non autorisés.");
            return;
        } else {
            errorLabelPrix.setText(""); // Effacer le message d'erreur s'il est valide
        }

        // Tous les champs sont valides, continuer le traitement
        int prix = Integer.parseInt(prixText);

        Produit p = new Produit();
        p.setNom(nom);
        p.setDescription(description);
        p.setPrix(prix);
        p.setId_categorie(ComboCatrgorie.getValue().getId_categorie());
        p.setImagePath(ImagePath);

        sp.create(p);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        try {
            Mailing.sendHtmlNotification("aziz.chouikha@esprit.tn", "Nouveau Produit !", nom);
        }
        catch(Exception e)
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setTitle("NOT OK");
            a.setContentText("Mail NON enovyé !");
            a.showAndWait();
            System.out.println(e.getMessage());

        }
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setTitle("OK");
        a.setContentText("Mail enovyé !");
        a.showAndWait();


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


    }

    @FXML
    public void OnAddImageButtonClicked(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        File defaultDir = new File("src/main/resources");
        fc.setInitialDirectory(defaultDir);
        File SelectedFile = fc.showOpenDialog(this.addImageButton.getScene().getWindow());

        if (SelectedFile != null) {
            copyFileToDirectory(SelectedFile, defaultDir);

            ImagePath = "src/main/resources/" + SelectedFile.getName();
            System.out.println(ImagePath);
            imageView.setImage(new Image(new File(ImagePath).toURI().toString()));
        } else {
            ImagePath = "src/main/resources/tttttttt.jpg";
            System.out.println(ImagePath);
            imageView.setImage(new Image(new File(ImagePath).toURI().toString()));

        }

    }

    public static void copyFileToDirectory(File sourceFile, File destDir) throws IOException {
        Path sourcePath = sourceFile.toPath();
        Path destPath = destDir.toPath().resolve(sourceFile.getName());
        Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
