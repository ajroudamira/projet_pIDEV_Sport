package service;

import entities.Type;
import utils.MyDataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceType implements IService<Type> {
    private Connection conx;

    public ServiceType() {
        conx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void ajouter(Type type) {
        try {
            String query = "INSERT INTO `type` (`nom`, `description`) VALUES ('"
                    + type.getNom() + "','" + type.getDescription() + "')";
            Statement st = conx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("erreur: " + e.getMessage());
        }
    }

    @Override
    public void modifier(int id, Type type) {
        try {
            String query = "UPDATE `type` SET `nom`='" + type.getNom()
                    + "', `description`='" + type.getDescription() + "' WHERE id=" + id;
            Statement st = conx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("erreur: " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String query = "DELETE FROM `type` WHERE id=" + id;
            Statement st = conx.createStatement();
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
            Statement st = conx.createStatement();
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
}
