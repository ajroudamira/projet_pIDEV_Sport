package tn.esprit.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.FXMain;
import tn.esprit.entities.Equipement;
import tn.esprit.services.OnChangeListener;
import tn.esprit.services.ServiceEquipement;
import tn.esprit.services.ServiceType;
import tn.esprit.utils.ExportToExcel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AfficherEquipementAdmin implements OnChangeListener {

    @FXML
    private GridPane grid;

    @FXML
    private TextField tfrecherche;

    @FXML
    private ComboBox<String> cbtri;

    @FXML
    private Label idgetter;
    ServiceEquipement se=new ServiceEquipement();
    ServiceType st=new ServiceType();
    public static int idModifier;
    @FXML
    public void initialize() {
        refresh(se.afficher());
        idgetter.setVisible(false);
        cbtri.getItems().setAll("Nom","Description","Date","Type","Marque");
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
            controller.setOnChangeListener(this);
            if(column==2){
                column=0;
                row++;
            }
            grid.add(anchorPane,column++,row);
            GridPane.setMargin(anchorPane,new Insets(10));
        }
    }

    @FXML
    void ajouterEquipement(ActionEvent event) {
        idModifier=0;
        Stage stage=(Stage) idgetter.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader=new FXMLLoader(FXMain.class.getResource("/ajouter-equipement-view.fxml"));
        Stage stage1=new Stage();
        stage1.setTitle("GymSync");
        try {
            stage1.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage1.show();
    }

    @FXML
    void excel(ActionEvent event) {
        String filename="Equipement.xlsx";
        File file=new File("C:\\Users\\pc\\Desktop\\PiDEV\\Gymsync\\src\\main\\resources\\excel\\"+filename);
        ExportToExcel.exportToExcel(se.afficher(),file);
        Notifications.create().title("Fichier excel").text("Fichier excel generer avec succes").hideAfter(Duration.seconds(5)).showInformation();
    }

    @FXML
    void gotoGestionType(ActionEvent event) {
        Stage stage=(Stage) idgetter.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader=new FXMLLoader(FXMain.class.getResource("/ajouter-type-view.fxml"));
        Stage stage1=new Stage();
        stage1.setTitle("GymSync");
        try {
            stage1.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage1.show();
    }

    @FXML
    void gotoStat(ActionEvent event) {
        Stage stage=(Stage) idgetter.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader=new FXMLLoader(FXMain.class.getResource("/stat-equipement-view.fxml"));
        Stage stage1=new Stage();
        stage1.setTitle("GymSync");
        try {
            stage1.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage1.show();
    }

    @FXML
    void modifierEquipement(ActionEvent event) {
        if(idgetter.getText().matches("\\d+")){
            idModifier=Integer.valueOf(idgetter.getText());
            Stage stage=(Stage) idgetter.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader=new FXMLLoader(FXMain.class.getResource("/ajouter-equipement-view.fxml"));
            Stage stage1=new Stage();
            stage1.setTitle("GymSync");
            try {
                stage1.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage1.show();
        }


    }

    @FXML
    void supprimerEquipement(ActionEvent event) {
        se.supprimer(Integer.valueOf(idgetter.getText()));
        refresh(se.afficher());
    }

    @FXML
    void tri(ActionEvent event) {
        refresh(se.triEquipementParCritere(cbtri.getValue()));
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

    @Override
    public void sendEquipementData(Equipement equipement) {
        idgetter.setText(String.valueOf(equipement.getId()));
    }
}
