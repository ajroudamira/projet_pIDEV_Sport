package services;

import entities.Categorie;
import entities.Rating;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingService {

    private Connection cnx;

    public RatingService()
    {
        this.cnx = MyDatabase.getInstance().getConnection();
    }

    public void create(Rating rating) throws SQLException {
        String req ="INSERT INTO `rating`(`id_user`, `id_produit`, `value`) VALUES (? , ?, ?)";
        PreparedStatement st= cnx.prepareStatement(req);
        st.setInt(1,rating.getId_user());
        st.setInt(2,rating.getId_produit());
        st.setString(3,rating.getValue().toString());
        st.executeUpdate();
        System.out.println("rating ajouté !");
    }

    public void update(Rating rating) throws SQLException{
        String req ="UPDATE `rating` SET `value`=? WHERE `id_user`=? AND `id_produit`=?";
        PreparedStatement st= cnx.prepareStatement(req);
        st.setString(1,rating.getValue().toString());
        st.setInt(2,rating.getId_user());
        st.setInt(3,rating.getId_produit());
        st.executeUpdate();
        System.out.println("rating updated");
    }

    public Double GetRatingParProduit(int id_produit , int id_user) throws SQLException{
        Double tot = 0.0 ;
        String req ="SELECT * FROM `rating` where id_produit ="+id_produit +" AND id_user ="+id_user;
        Statement st= cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            Double one = Double.parseDouble(rs.getString("value"));
            tot += one ;
        }
        System.out.println("totale = " + tot);
        return tot;
    }






}
