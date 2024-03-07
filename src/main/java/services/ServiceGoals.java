package services;

import entities.Cours;
import entities.Goals;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceGoals {
    Connection connection;


    public ServiceGoals() {
        connection = MyDatabase.getInstance().getConnection();

    }

   /* public List<Goals> afficher(Connection connection) {
        List<Goals> goalss = new ArrayList<>();
        String req = "SELECT a.nom, a.salle, b.objective, b.calories FROM cours a INNER JOIN type_cours b ON a.type_cours_id = b.id";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Goals g = new Goals();
                g.setNom(rs.getString("nom"));
                g.setSalle(rs.getString("salle"));
                g.setObjective(rs.getString("objective"));
                g.setCalories(rs.getInt("calories"));
                goalss.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goalss;
    } */
   public List<Goals> getAllGoals() throws SQLException {
       List<Goals> goalsList = new ArrayList<>();
       String query = "SELECT c.nom, c.salle, c.duree, c.horaire , tc.objective, tc.calories " +
               "FROM cours c " +
               "INNER JOIN type_cours tc ON c.type_cours_id = tc.id";
       try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
           while (resultSet.next()) {
               String nom = resultSet.getString("nom");
               String salle = resultSet.getString("salle");
               int duree = resultSet.getInt("duree");
               String horaire = resultSet.getString("horaire");
               String objective = resultSet.getString("objective");
               int calories = resultSet.getInt("calories");
               Goals goal = new Goals(nom, salle,horaire,objective, calories, duree);
               goalsList.add(goal);
           }
       }
       return goalsList;
   }
}
