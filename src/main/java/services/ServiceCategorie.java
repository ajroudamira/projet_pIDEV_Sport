package services;

import entities.Categorie;
import entities.Produit;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategorie implements ICrud<Categorie> {


    private Connection cnx;

    public ServiceCategorie() {
        this.cnx = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void create(Categorie categorie) throws SQLException {
        String req ="INSERT INTO `categorie`(`id_categorie`, `nom`, `description`) VALUES (? , ?, ?)";
        PreparedStatement st= cnx.prepareStatement(req);
        st.setInt(1,categorie.getId_categorie());
        st.setString(2,categorie.getNom());
        st.setString(3,categorie.getDescription());
        st.executeUpdate();
        System.out.println("ajouté category");
    }

    @Override
    public List<Categorie> getAll() throws SQLException{
        List<Categorie> Categories = new ArrayList<>();
        String req ="SELECT * FROM `categorie`";
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            int id_categorie = rs.getInt("id_categorie");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            Categories.add(new Categorie(id_categorie,nom,description));
        }
        System.out.println("afficher category");
        return Categories;
    }

    @Override
    public void update(Categorie categorie) throws SQLException{
        String req ="UPDATE `categorie` SET `nom`=?,`description`=? WHERE `id_categorie`=?";
        PreparedStatement st= cnx.prepareStatement(req);
        st.setInt(3,categorie.getId_categorie());
        st.setString(1,categorie.getNom());
        st.setString(2,categorie.getDescription());

        st.executeUpdate();
        System.out.println("modifier category");
        System.out.println("Nom: " + categorie.getNom());
        System.out.println("Description: " + categorie.getDescription());
        System.out.println("ID Catégorie: " + categorie.getId_categorie());
    }


    @Override
    public void delete(int id) throws SQLException{
        String req ="DELETE FROM `categorie` WHERE `id_categorie`=?";
        PreparedStatement st= cnx.prepareStatement(req);
        st.setInt(1,id);
        st.executeUpdate();
        System.out.println("supprimer category");
    }
    public Categorie findOneById(int id) throws SQLException {
        Categorie c = new Categorie();
        String req ="SELECT * FROM `categorie` where id_categorie  = "+ id;
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            int id_categorie = rs.getInt("id_categorie");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
             c = new Categorie(id_categorie,nom,description);
        }
        System.out.println("afficher category");
        return c;
    }
}

