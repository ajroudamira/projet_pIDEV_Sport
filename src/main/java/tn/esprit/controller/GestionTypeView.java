package tn.esprit.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.entities.Type;
import tn.esprit.service.ServiceType;

public class GestionTypeView
{
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

    @FXML
    void ajouterType(ActionEvent event) {
        Type type=new Type();
        type.setNom(tfnom.getText());
        type.setDescription(tadesc.getText());
        st.ajouter(type);
        refresh();
    }

    @FXML
    void modifierType(ActionEvent event) {
        if(listview.getSelectionModel().getSelectedItem()!=null){

            Type type=listview.getSelectionModel().getSelectedItem();
            type.setNom(tfnom.getText());
            type.setDescription(tadesc.getText());
            st.modifier(type.getId(), type);
            refresh();
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
}