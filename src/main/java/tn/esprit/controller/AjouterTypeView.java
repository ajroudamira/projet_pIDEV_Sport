package tn.esprit.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.FXMain;
import tn.esprit.entities.Type;
import tn.esprit.services.ServiceType;

import java.io.IOException;

public class AjouterTypeView {

    @FXML
    private TextField tfnom;

    @FXML
    private TextArea tadesc;

    @FXML
    private ListView<Type> listview;
    ServiceType st=new ServiceType();
    ObservableList<Type> data= FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        refresh();
    }
    public void refresh(){
        data.clear();
        data=FXCollections.observableArrayList(st.afficher());
        listview.setItems(data);
    }
    public String controleDeSaisie(){
        String erreur="";
        if(tfnom.getText().isEmpty() || !tfnom.getText().matches("\\D*")){
            erreur+="Nom vide ou invalide\n";
        }
        if(tadesc.getText().isEmpty()){
            erreur+="Description vide\n";
        }
        return erreur;
    }

    @FXML
    void ajouterType(ActionEvent event) {
        if(controleDeSaisie().length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formulaire invalide!");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Type type=new Type();
            type.setNom(tfnom.getText());
            type.setDescription(tadesc.getText());

            st.ajouter(type);
            refresh();
        }

    }

    @FXML
    void modifierType(ActionEvent event) {

        if(listview.getSelectionModel().getSelectedItem()!=null){
            if(controleDeSaisie().length()>0){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Formulaire invalide!");
                alert.setContentText(controleDeSaisie());
                alert.showAndWait();
            }
            else{
                Type type=listview.getSelectionModel().getSelectedItem();
                type.setNom(tfnom.getText());
                type.setDescription(tadesc.getText());
                st.modifier(type.getId(), type);
                refresh();
            }

        }

    }

    @FXML
    void supprimerType(ActionEvent event) {
        if(listview.getSelectionModel().getSelectedItem()!=null){
            int id=listview.getSelectionModel().getSelectedItem().getId();
            st.supprimer(id);
            refresh();
        }

    }
    @FXML
    void gotoGestionEquipement(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("/ajouter-equipement-view.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) tfnom.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.setTitle("GymSync");
        stage1.show();
    }



}