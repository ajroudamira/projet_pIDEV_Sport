package tn.esprit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.entities.Equipement;
import tn.esprit.service.ServiceEquipement;
import tn.esprit.service.ServiceType;

import java.sql.Date;

public class AjouterEquipementView {

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
    ServiceEquipement se=new ServiceEquipement();
    ServiceType st=new ServiceType();
    @FXML
    public void initialize() {
        cbtype.getItems().setAll(st.getAllTypeName());
    }
    @FXML
    void ajouterEquipement(ActionEvent event) {
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


    }

    @FXML
    void uploadImage(ActionEvent event) {

    }

}
