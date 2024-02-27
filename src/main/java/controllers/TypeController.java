package controllers;

import java.io.IOException;
import java.sql.SQLException;

import entities.Cours;
import entities.TypeCours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceType;

public class TypeController {

    ServiceType serviceType = new ServiceType() ;


   /* @FXML
    private TableColumn<TypeCours, Integer> col_calories;

    @FXML
    private TableColumn<TypeCours, String> col_description;

    @FXML
    private TableColumn<TypeCours, String> col_nom;

    @FXML
    private TableColumn<TypeCours, String> col_objective; */

    @FXML
    private TextField tf_calories;

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_nom;

  @FXML
    private TextField tf_objective;

   /* @FXML
    private TableView<TypeCours> tv_types; */

    @FXML
    void AfficherType(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AffichageTypeCours.fxml"));
            tf_nom.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AjouterType(ActionEvent event) {
        try {

           // TypeCours t=new TypeCours(Integer.parseInt(tf_calories.getText()), tf_description.getText(),  tf_nom.getText(), tf_objectif.getText());
           // TypeCours t=new TypeCours( tf_nom.getText(), tf_objective.getText(), tf_description.getText(), Integer.parseInt(tf_calories.getText())  );
            serviceType.ajouter(new TypeCours( tf_nom.getText(), tf_objective.getText(), tf_description.getText(), Integer.parseInt(tf_calories.getText())  ));

          /*  System.out.println(t);
            serviceType.ajouter(t);*/


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("type ajoute");
            alert.showAndWait();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
  /* @FXML
   private void AjouterType(ActionEvent event) throws SQLException {
       Type t = new Type(Integer.parseInt(tf_calories_brulees.getText()), tf_description.getText(),  tf_nom.getText(), tf_objectif.getText());

       if (serviceType.ajouter(t)); {
           Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
           succDeleteBookAlert.setTitle("Delete Blog");
           succDeleteBookAlert.setHeaderText("Results:");
           succDeleteBookAlert.setContentText("cours deleted successfully!");
           succDeleteBookAlert.showAndWait();
       } else  {
           Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
           selectBookAlert.setTitle("Select a cours");
           selectBookAlert.setHeaderText(null);
           selectBookAlert.setContentText("You need to select a cours first!");
           selectBookAlert.showAndWait();
       }
     /*  data.clear();
       data.addAll(cr.readAll());*/
    // }

    @FXML
    void ModifierType(ActionEvent event) {
       /* if (tv_types.getSelectionModel().getSelectedItem() != null) {

            TypeCours t = tv_types.getSelectionModel().getSelectedItem();

            try {
                serviceType.modifier(t.getNom(), t.getId());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("edit");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("The type was successfully edit");
            BookAlert.showAndWait();

        } else {
            //Alert Select type :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select type");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select type first!");
            selectBookAlert.showAndWait();
            //Alert Select type!
        } */
    }
    // @FXML
   /* private void changelessonCellEvent(CellEditEvent edittedCell) {
        /* cours coursSelected = tabviewcours.getSelectionModel().getSelectedItem();
        coursSelected.setDuration(Integer.parseInt(edittedCell.getNewValue().toString()));
        // enti tbadel ken duration ? normalement naatih l id iwal i ibadel el duration oui duration kahaw ok*/

    //}

    /* @FXML
     private void changeDescriptionCellEvent(CellEditEvent edittedCell) {
         Type typeSelected = tv_types.getSelectionModel().getSelectedItem();
         typeSelected.setDescription(Integer.parseInt(edittedCell.getNewValue().toString()));
     }
 */
    @FXML
    void SupprimerType(ActionEvent event) {

    }

  /*  @FXML
    void initialize() {
        try {
            System.out.println(serviceType.afficher());
            ObservableList<TypeCours> types= FXCollections.observableList(serviceType.afficher());
            tv_types.setItems(types);
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_objectif.setCellValueFactory(new PropertyValueFactory<>("objectif"));
            col_cal.setCellValueFactory(new PropertyValueFactory<>("id"));




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }*/


}
