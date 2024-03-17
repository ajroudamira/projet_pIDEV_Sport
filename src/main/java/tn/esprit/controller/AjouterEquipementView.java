package tn.esprit.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
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
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

public class AjouterEquipementView  {

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfmarque;

    @FXML
    private TextField tfmodele;

    @FXML
    private TextField tfcouleur;

    @FXML
    private TextField tfmatiere;

    @FXML
    private TextField tftaille;

    @FXML
    private TextField tfimage;

    @FXML
    private ComboBox<String> cbtype;

    @FXML
    private TextArea tadesc;
    @FXML
    private DatePicker dpdate;

    @FXML
    private Label idgetter;


    @FXML
    private ComboBox<String> cbtri;
    ServiceEquipement se=new ServiceEquipement();
    ServiceType st=new ServiceType();
    @FXML
    public void initialize() {
        tfimage.setDisable(true);
        cbtype.getItems().setAll(st.getAllTypeName());
        if(AfficherEquipementAdmin.idModifier!=0){
            Equipement equipement=se.getEquipementById(AfficherEquipementAdmin.idModifier);

            tfimage.setText(equipement.getImage());
            tfcouleur.setText(equipement.getCouleur());
            tfmarque.setText(equipement.getMarque());
            tadesc.setText(equipement.getDescription());
            tfmatiere.setText(equipement.getMatiere());
            tfnom.setText(equipement.getNom());
            tfmodele.setText(equipement.getModele());
            tftaille.setText(equipement.getTaille());
            cbtype.setValue(st.getTypeParId(equipement.getIdType()).getNom());
            dpdate.setValue(Instant.ofEpochMilli(equipement.getDate_de_fabrication().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        }
    }
    public String controleDeSaisie(){
        String erreur="";
        if(tfnom.getText().isEmpty()|| !tfnom.getText().matches("\\D*")){
            erreur+="Nom vide\n";
        }
        if(tadesc.getText().isEmpty()){
            erreur+="Description vide\n";
        }
        if(tftaille.getText().isEmpty()){
            erreur+="Taille vide\n";
        }
        if(tfmodele.getText().isEmpty()){
            erreur+="Modele vide\n";
        }
        if(tfmatiere.getText().isEmpty()){
            erreur+="Matiere vide\n";
        }
        if(tfmarque.getText().isEmpty()){
            erreur+="Marque vide\n";
        }
        if(tfcouleur.getText().isEmpty()){
            erreur+="Couleur vide\n";
        }
        if(tfimage.getText().isEmpty()){
            erreur+="Image vide\n";
        }
        if(cbtype.getValue()==null){
            erreur+="Type vide\n";
        }
        if(dpdate.getValue()==null){
            erreur+="Date vide\n";
        }
        return erreur;
    }
    @FXML
    void ajouterEquipement(ActionEvent event) {
        if(controleDeSaisie().length()>0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formulaire invalide!");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Equipement e=new Equipement();
            e.setNom(tfnom.getText());
            e.setDescription(tadesc.getText());
            e.setCouleur(tfcouleur.getText());
            e.setImage(tfimage.getText());
            e.setMarque(tfmarque.getText());
            e.setMatiere(tfmatiere.getText());
            e.setModele(tfmodele.getText());
            e.setTaille(tftaille.getText());
            e.setEtat(true);
            e.setDate_de_fabrication(Date.valueOf(dpdate.getValue()));

            e.setIdType(st.getIdtypeByNom(cbtype.getValue()));
            se.ajouter(e);
            //NOTIFICATION
            Notifications.create().title("Ajout equipement").text("Ajout avec succes").hideAfter(Duration.seconds(5)).showInformation();
        }



    }
    @FXML
    void modifierEquipement(ActionEvent event) {
        if(AfficherEquipementAdmin.idModifier!=0){
            if(controleDeSaisie().length()>0){
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Formulaire invalide!");
                alert.setContentText(controleDeSaisie());
                alert.showAndWait();
            }
            else{
                Equipement e=new Equipement();
                e.setNom(tfnom.getText());
                e.setDescription(tadesc.getText());
                e.setCouleur(tfcouleur.getText());
                e.setImage(tfimage.getText());
                e.setMarque(tfmarque.getText());
                e.setMatiere(tfmatiere.getText());
                e.setModele(tfmodele.getText());
                e.setTaille(tftaille.getText());
                e.setEtat(true);
                e.setDate_de_fabrication(Date.valueOf(dpdate.getValue()));

                e.setIdType(st.getIdtypeByNom(cbtype.getValue()));
                se.modifier(AfficherEquipementAdmin.idModifier,e);
            }
        }


    }





    @FXML
    void uploadImage(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        Stage stage=(Stage)tfcouleur.getScene().getWindow();
        File file=fileChooser.showOpenDialog(stage);
        if(file!=null){
            String filename=file.getName();
            tfimage.setText(filename);
        }
    }


    @FXML
    void gotoGestionType(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("/ajouter-type-view.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) tfcouleur.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.setTitle("GymSync");
        stage1.show();


    }
    @FXML
    void gotoAfficher(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("/afficher-equipement-admin.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) tfcouleur.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root));
        stage1.setTitle("GymSync");
        stage1.show();
    }




}