package services;

import entities.Cours;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCours implements  IService<Cours> {
    Connection connection;
    public ServiceCours(){
        connection= MyDatabase.getInstance().getConnection();

    }
    /* @Override
    public void ajouter(Cours cours) throws SQLException {
        String req ="insert into cours (nom,type,duree,horaire)"+
                "values('"+cours.getNom()+"','"+cours.getType()+"',"+cours.getHoraire()+"','"+cours.getDuree()+")";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("cours ajouté");
    } */
   /* @Override
    public void ajouter(Cours cours) throws SQLException {
        String req = "INSERT INTO cours (nom, type, duree, horaire) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, cours.getNom());
        ps.setString(2, cours.getType());
        ps.setInt(3, cours.getDuree());
        ps.setString(4, cours.getHoraire());

        ps.executeUpdate();
        System.out.println("Coach ajouté");
        ps.close(); // N'oubliez pas de fermer la PreparedStatement
    }
    @Override
    public void modifier(Cours cours) throws SQLException {
        String req="update cours set nom=? , type=? ,duree=?  , horaire=? where id_cours=?";
        PreparedStatement ps= connection.prepareStatement(req);
        ps.setString(1, cours.getNom());
        ps.setString(2, cours.getType());
        ps.setString(3, cours.getHoraire());
        ps.setInt(4, cours.getDuree());
        ps.setInt(5, cours.getId());
        ps.executeUpdate();
        System.out.println("Cours modifie");

    }
   /* @Override
    public void delete(Cours c) {
        String requete = "DELETE FROM Cours WHERE ID_COURS = '"+c.getId()+"' ";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(requete);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/

/*
        @Override
        public List<Cours> afficher() throws SQLException {

            List<Cours> courss= new ArrayList<>();
            String req="select * from cours";
            Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Cours c = new Cours();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString("nom"));
                c.setType(rs.getString("type"));
                c.setDuree(rs.getInt("duree"));
                c.setHoraire(rs.getString("horaire"));
                courss.add(c);
            }
            return courss;
        } */

    @Override
    public void ajouter(Cours cours) throws SQLException {
        String req = "INSERT INTO cours (nom, salle, duree, horaire/*,id_type*/) " +
                "VALUES (?, ?, ?, ?  /*,?*/)";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, cours.getNom());
        ps.setString(2, cours.getSalle());
        ps.setInt(3, cours.getDuree());
        ps.setString(4, cours.getHoraire());
       // ps.setInt(5, cours.getId_type());

        ps.executeUpdate();
        System.out.println("type ajouté");
        ps.close(); // N'oubliez pas de fermer la PreparedStatement
    }

    /*@Override
    public void delete(Cours c) {
        String requete = "DELETE FROM Cours WHERE ID_COURS = '"+c.getId()+"' ";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(requete);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    } */


        @Override
        public List<Cours> afficher() throws SQLException {

            List<Cours> courss= new ArrayList<>();
            String req="select * from cours";
            Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setSalle(rs.getString("salle"));
                c.setDuree(rs.getInt("duree"));
                c.setHoraire(rs.getString("horaire"));
                courss.add(c);
            }
            return courss;
        }
    /*public void supprimer(Cours cours) {
        String requete = "DELETE FROM COURS WHERE ID_COURS = ?";
        try (PreparedStatement ps = connection.prepareStatement(requete)) {
            ps.setInt(1, cours.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM COURS WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id); // Utilisez le nom passé en paramètre
            ps.executeUpdate();
            System.out.println("Cours deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean modifier(String nom, int id) throws SQLException {
        try {
            PreparedStatement pre = connection.prepareStatement("update Cours set nom =? where id=? ;");

            pre.setString(1, nom);
            pre.setInt(2, id);

            if (pre.executeUpdate() != 0) {
                System.out.println(" updated");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id  not found!!!");
        return false;

    }

}
