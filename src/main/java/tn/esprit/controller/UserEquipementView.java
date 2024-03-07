package tn.esprit.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.esprit.FXMain;
import tn.esprit.entities.Equipement;
import tn.esprit.services.ServiceEquipement;
import tn.esprit.services.ServiceType;

import java.io.IOException;
import java.util.List;

public class UserEquipementView
{
    @FXML
    private GridPane grid;
    @FXML
    private ComboBox<String> cbtype;
    ServiceEquipement se=new ServiceEquipement();
    ServiceType st=new ServiceType();
    @FXML
    private ComboBox<String> cbtri;

    @FXML
    private TextField tfrecherche;
    ServiceType serviceType=new ServiceType();


    @FXML
    void tri(ActionEvent event) {
        refresh(se.triEquipementParCritere(cbtri.getValue()));


    }
    @FXML
    void filtre(ActionEvent event) {
        refresh(se.getEquipementsParType(serviceType.getIdtypeByNom(cbtype.getValue())));
    }
    void recherche_avance(){
        ObservableList<Equipement> data= FXCollections.observableArrayList(se.afficher());
        FilteredList<Equipement> filteredList=new FilteredList<>(data, e->true);
        tfrecherche.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(e->{
                if(newValue==null || newValue.isEmpty()){
                    return true;
                }
                if(e.getNom().contains(newValue)){
                    return true;
                }
                else if(e.getDescription().contains(newValue)){
                    return true;
                }
                else if(e.getMarque().contains(newValue)){
                    return true;
                }
                else if(String.valueOf(e.getDate_de_fabrication()).contains(newValue)){
                    return true;
                } else if (st.getTypeParId(e.getIdType()).getNom().contains(newValue)) {
                    return true;
                } else{
                    return false;
                }
            });
            refresh(filteredList);
        });
    }

    @FXML
    public void initialize() {
        refresh(se.afficher());
        cbtri.getItems().setAll("Nom","Description","Date","Type","Marque");
        cbtype.getItems().setAll(st.getAllTypeName());

        recherche_avance();
    }
    public void refresh(List<Equipement> list){
        grid.getChildren().clear();
        int column=0;
        int row=1;
        for(Equipement e:list){
            FXMLLoader card=new FXMLLoader(FXMain.class.getResource("/equipement-card-view.fxml"));
            AnchorPane anchorPane= null;
            try {
                anchorPane = card.load();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            EquipementCardView controller=card.getController();
            controller.remplireData(e);

            if(column==2){
                column=0;
                row++;
            }
            grid.add(anchorPane,column++,row);
            GridPane.setMargin(anchorPane,new Insets(10));
        }
    }

}