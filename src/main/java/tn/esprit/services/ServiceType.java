package tn.esprit.services;

import tn.esprit.entities.Type;
import tn.esprit.utils.MyDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceType implements IService<Type>{
    private Connection cnx;

    public ServiceType() {
        cnx = MyDatabase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Type type) {
        try {
            String query = "INSERT INTO `type` (`nom`, `description`) VALUES ('" +
                    type.getNom() + "','" + type.getDescription() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("erreur: " + e.getMessage());
        }
    }

    @Override
    public void modifier(int id, Type type) {
        try {
            String query = "UPDATE `type` SET `nom`='" + type.getNom() +
                    "',`description`='" + type.getDescription() + "' WHERE id=" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("erreur: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String query = "DELETE FROM `type` WHERE id=" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("erreur: " + e.getMessage());
        }
    }

    @Override
    public List<Type> afficher() {
        List<Type> lt = new ArrayList<>();
        try {
            String query = "SELECT * FROM `type`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt("id"));
                type.setNom(rs.getString("nom"));
                type.setDescription(rs.getString("description"));
                lt.add(type);
            }
        } catch (SQLException e) {
            System.out.println("erreur: " + e.getMessage());
        }

        return lt;
    }
    public Type getTypeParId(int id){
        return afficher().stream().filter(t->t.getId()==id).findAny().orElse(null);
    }
    public List<String> getAllTypeName(){
        List<String> lnom=new ArrayList<>();
        for(Type type:afficher()){
            lnom.add(type.getNom());
        }
        return lnom;
        //return afficher().stream().map(t->t.getNom()).collect(Collectors.toList());
    }
    public int getIdtypeByNom(String nom){
        for(Type type:afficher()){
            if(type.getNom().equals(nom)){
                return type.getId();
            }
        }
        return 0;
        //return afficher().stream().filter(t->t.getNom().equals(nom)).findAny().orElse(null).getId();
    }
}
