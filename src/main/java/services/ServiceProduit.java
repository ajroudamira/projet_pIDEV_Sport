package services;

import entities.Categorie;
import entities.Produit;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceProduit implements ICrud<Produit> {

    private Connection cnx;

    public ServiceProduit() {
        this.cnx = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void create(Produit Produit) throws SQLException {
        String req ="INSERT INTO `produit`(`nom`, `prix`, `description`, `id_categorie`,`ImagePath`) VALUES (?,?,?,?,?)";
        PreparedStatement st= cnx.prepareStatement(req);
        System.out.println(Produit);
        st.setString(1,Produit.getNom());
        st.setDouble(2,Produit.getPrix());
        st.setString(3, Produit.getDescription());
        st.setInt(4, Produit.getId_categorie());
        st.setString(5,Produit.getImagePath());
        st.executeUpdate();
        System.out.println("ajouté Produit");
    }

    @Override
    public List<Produit> getAll() throws SQLException{
        List<Produit> Produits = new ArrayList<>();
        String req ="SELECT * FROM `produit`";
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            int id_produit = rs.getInt("id_produit");
            String nom = rs.getString("nom");
            Double prix = rs.getDouble("prix");
            String desc = rs.getString("description");
            int id_categorie = rs.getInt("id_categorie");
            String imagePath = rs.getString("ImagePath");
            Produits.add(new Produit(id_produit,nom,prix,desc,id_categorie,imagePath));
        }
        System.out.println("afficher produit");
        return Produits;
    }

    @Override
    public void update(Produit Produit) throws SQLException{

        String req ="UPDATE `produit` SET `nom`=?,`prix`=?,`description`=?,`id_categorie`=? `ImagePath` = ? WHERE`id_produit`=?";
        PreparedStatement st= cnx.prepareStatement(req);
        st.setInt(6, Produit.getId_produit());
        st.setString(1,Produit.getNom());
        st.setDouble(2,Produit.getPrix());
        st.setString(3, Produit.getDescription());
        st.setInt(4, Produit.getId_categorie());
        st.setString(5, Produit.getImagePath());
        st.executeUpdate();
        System.out.println("modifier Produit");
    }

    @Override
    public void delete(int id) throws SQLException{
        String req ="DELETE FROM `produit` WHERE `id_produit`=?";
        PreparedStatement st= cnx.prepareStatement(req);
        st.setInt(1,id);
        st.executeUpdate();
        System.out.println("supprimer produit");
    }

    public Produit findOneById(int id) throws SQLException {
        Produit p = new Produit();
        String req ="SELECT * FROM `produit` where id_produit = " + id;
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            int id_produit = rs.getInt("id_produit");
            String nom = rs.getString("nom");
            Double prix = rs.getDouble("prix");
            String desc = rs.getString("description");
            int id_categorie = rs.getInt("id_categorie");
            String imagePath = rs.getString("ImagePath");
            p = new Produit(id_produit,nom,prix,desc,id_categorie,imagePath);
        }
        System.out.println("afficher produit");
        return p;
    }
}



