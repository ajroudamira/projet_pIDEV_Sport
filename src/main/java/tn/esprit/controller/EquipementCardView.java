package tn.esprit.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Equipement;
import tn.esprit.services.OnChangeListener;
import tn.esprit.services.ServiceType;

import java.io.File;

public class EquipementCardView
{

    @FXML
    private ImageView img;

    @FXML
    private ImageView imgetat;

    @FXML
    private Label lnom;

    @FXML
    private Label lmarque;

    @FXML
    private Label ltype;

    @FXML
    private Label ldate;

    @FXML
    public void initialize() {
        imgetat.setVisible(false);
    }
    ServiceType serviceType=new ServiceType();
    Equipement e;
    private OnChangeListener onChangeListener;

    public OnChangeListener getOnChangeListener() {
        return onChangeListener;
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public void remplireData(Equipement e){
        this.e=e;
        lnom.setText(e.getNom());
        lmarque.setText(e.getMarque());
        ldate.setText(String.valueOf(e.getDate_de_fabrication()));
        ltype.setText(serviceType.getTypeParId(e.getIdType()).getNom());
        if(e.isEtat()==false){
            imgetat.setVisible(true);
        }
        File file=new File("C:\\Users\\pc\\Desktop\\pi\\Gymsync\\src\\main\\resources\\image\\"+e.getImage());
        Image image=new Image(file.toURI().toString());
        img.setImage(image);
    }
    @FXML
    void selectedEquipement(MouseEvent event) {
        if(onChangeListener!=null){
            onChangeListener.sendEquipementData(e);
        }
    }
}