package tn.esprit.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.esprit.FXMain;
import tn.esprit.entities.Equipement;
import tn.esprit.services.ServiceEquipement;

import java.io.IOException;
import java.util.List;

public class UserEquipementView
{
    @FXML
    private GridPane grid;
    ServiceEquipement se=new ServiceEquipement();

    @FXML
    public void initialize() {
        refresh();
    }
    public void refresh(){
        grid.getChildren().clear();
        List<Equipement> list=se.afficher();
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