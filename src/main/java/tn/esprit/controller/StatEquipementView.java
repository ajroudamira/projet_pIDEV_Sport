package tn.esprit.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import tn.esprit.FXMain;
import tn.esprit.utils.MyDatabase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatEquipementView
{
    @FXML
    private PieChart piechart;
    ObservableList<PieChart.Data> data= FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            stat();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void stat() throws SQLException {
        String query="SELECT count(e.id) as nbr,t.nom as nom FROM equipement e JOIN type t ON e.idType=t.id GROUP BY t.id;";
        Statement st= MyDatabase.getInstance().getCnx().createStatement();
        ResultSet rs=st.executeQuery(query);
        while (rs.next()){
            data.add(new PieChart.Data("Nom type: "+rs.getString("nom"),rs.getInt("nbr")));
        }
        piechart.setData(data);
    }
    @FXML
    void retour(ActionEvent event) {
        Stage stage=(Stage) piechart.getScene().getWindow();
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
