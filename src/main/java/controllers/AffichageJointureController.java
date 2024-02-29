package controllers;
import entities.Cours;
import entities.Goals;
import entities.TypeCours;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ServiceGoals;




public class AffichageJointureController {

    public TextField tf_recherche;
    public ImageView rech;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<TypeCours, Integer> col_calories;

    @FXML
    private TableColumn<Cours, String> col_cours;

    @FXML
    private TableColumn<TypeCours, String> col_objectif;

    @FXML
    private TableColumn<Cours, String> col_salle;

    @FXML
    private TableView<Goals> tv_Join;
    public ObservableList<Goals> data = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        try {
            List<Goals> goalsList = serviceGoals.getAllGoals(); // Récupérer la liste des objectifs
            ObservableList<Goals> observableGoalsList = FXCollections.observableArrayList(goalsList); // Convertir la liste en ObservableList

            // Configurer les cellules de chaque colonne
            col_cours.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
            col_objectif.setCellValueFactory(new PropertyValueFactory<>("objective"));
            col_calories.setCellValueFactory(new PropertyValueFactory<>("calories"));

            // Ajouter les données à la TableView
            tv_Join.setItems(observableGoalsList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer les erreurs de récupération des données depuis la base de données
        }
    }

    ServiceGoals serviceGoals = new ServiceGoals();

    public void Tri(MouseEvent mouseEvent) {
        ObservableList<Goals> data = tv_Join.getItems();

        // Trier les données par l'ordre alphabétique des cours
        data.sort(Comparator.comparing(Goals::getNom));

        // Mettre à jour les données dans la TableView
        tv_Join.setItems(data);
    }

    public void rechercherGoal(MouseEvent mouseEvent) {
        String searchQuery = tf_recherche.getText().trim().toLowerCase();

        // Vérifiez si le champ de recherche n'est pas vide
        if (!searchQuery.isEmpty()) {
            // Filtrer les cours selon le nom de recherche
            ObservableList<Goals> filteredGoals = FXCollections.observableArrayList();

            for (Goals goals : tv_Join.getItems()) {
                if (goals.getObjective().toLowerCase().contains(searchQuery)) {
                    filteredGoals.add(goals);
                }
            }

            // Mettre à jour les données affichées dans le TableView avec les résultats de la recherche
            tv_Join.setItems(filteredGoals);
        } else {
            // Si le champ de recherche est vide, réafficher tous les cours
            tv_Join.setItems(data);
        }
    }
}
