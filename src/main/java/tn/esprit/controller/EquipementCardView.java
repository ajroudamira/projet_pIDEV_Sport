package tn.esprit.controller;

import com.google.zxing.WriterException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Equipement;
import tn.esprit.services.OnChangeListener;
import tn.esprit.services.ServiceType;
import tn.esprit.utils.QrCodeGenerator;

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
    private ImageView qrcodeimg;
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
        File file=new File("C:\\Users\\pc\\Desktop\\PiDEV\\Gymsync\\src\\main\\resources\\image\\"+e.getImage());
        Image image=new Image(file.toURI().toString());
        img.setImage(image);
        try {
            QrCodeGenerator.GenerateQrCode(e.toString(),e.getId());
        } catch (WriterException ex) {
            throw new RuntimeException(ex);
        }
        File file2=new File("C:\\Users\\pc\\Desktop\\PiDEV\\Gymsync\\src\\main\\resources\\qrcode\\Equipement_"+e.getId()+".png");
        Image image2=new Image(file2.toURI().toString());
        qrcodeimg.setImage(image2);
    }
    @FXML
    void selectedEquipement(MouseEvent event) {
        if(onChangeListener!=null){
            onChangeListener.sendEquipementData(e);
        }
    }
}