package services;

import entities.TypeCours;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceType implements  IService<TypeCours>{
    Connection connection;
    public ServiceType(){
        connection= MyDatabase.getInstance().getConnection();

    }

   @Override
   public void ajouter(TypeCours type) throws SQLException {
       String req = "INSERT INTO type_cours (nom, objective, description, calories) " +
               "VALUES (?, ?, ?, ?)";
       PreparedStatement ps = connection.prepareStatement(req);
       ps.setString(1, type.getNom());
       ps.setString(2, type.getObjective());
       ps.setString(3, type.getDescription());
       ps.setInt(4, type.getCalories());



       ps.executeUpdate();
       System.out.println("type ajouté");
       ps.close();
   }




    /*public void supprimer(Coach coach) {
        String requete = "DELETE FROM COACH WHERE ID_COACH = ?";
        try (PreparedStatement ps = connection.prepareStatement(requete)) {
            ps.setInt(1, coach.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/



        @Override
        public List<TypeCours> afficher() throws SQLException {

            List<TypeCours> types = new ArrayList<>();
            String req="select * from type_cours";
            Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                TypeCours t = new TypeCours();
                t.setId(rs.getInt("id"));
                t.setNom(rs.getString("nom"));
                t.setObjective(rs.getString("objective"));
                t.setDescription(rs.getString("description"));
                t.setCalories(rs.getInt("calories"));
                types.add(t);
            }
            return types;
        }
    /*@Override
    public void supprimer(int id) {
        String req = "DELETE FROM Coach WHERE id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id); // Utilisez le nom passé en paramètre
            ps.executeUpdate();
            System.out.println("coach deleted!");
        } catch (SQLException e) {
            System.out.println(e.getCoach());
        }
    }*/
    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM TYPE_COURS WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id); // Utilisez le nom passé en paramètre
            ps.executeUpdate();
            System.out.println("type deleted!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean modifier(int calories, int id) throws SQLException {
        try {
            PreparedStatement pre = connection.prepareStatement("update type_cours set calories =? where id=? ;");

            pre.setInt(1, calories );
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
    public boolean isNomExist(String nom) throws SQLException {
        // Écrire votre requête SQL pour vérifier si le nom existe déjà dans votre base de données
        String query = "SELECT COUNT(*) FROM type_cours WHERE nom = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nom);
        ResultSet resultSet = statement.executeQuery();

        // Récupérer le résultat de la requête
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0; // Si le count est supérieur à 0, le nom existe déjà
        }

        return false; // Si le résultat est vide, le nom n'existe pas
    }
}

