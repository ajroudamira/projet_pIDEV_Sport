package services;

import entities.Cours;
import entities.TypeCours;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ServiceCours implements IService<Cours> {

    Connection connection;

    public ServiceCours() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Cours cours) throws SQLException {
        String req = "INSERT INTO cours (nom, salle, duree, horaire, type_cours_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, cours.getNom());
            ps.setString(2, cours.getSalle());
            ps.setInt(3, cours.getDuree());
            ps.setString(4, cours.getHoraire());

            //  lezm lena ndakhl id type bch nzid type iguess
            ps.setInt(5, cours.getTypeCours().getId());

            ps.executeUpdate();
            System.out.println("Cours ajouté avec succès !");
        }
    }

    @Override
    public List<Cours> afficher() throws SQLException {
        List<Cours> courss = new ArrayList<>();
        String req = "SELECT c.*, tc.* FROM cours c INNER JOIN type_cours tc ON c.type_cours_id = tc.id";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            TypeCours types = new TypeCours(rs.getInt("tc.id"), rs.getString("tc.nom"), rs.getString("tc.objective"), rs.getString("tc.description"), rs.getInt("tc.calories"));
            Cours c = new Cours(rs.getInt("c.id"), rs.getString("c.nom"), rs.getString("c.salle"),rs.getInt("c.duree"), rs.getString("c.horaire"), types);
            courss.add(c);
        }
        return courss;
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM type_cours WHERE id=?";
        try {
            //  nfasakh cours marbout b typee
            String deleteCoursQuery = "DELETE FROM cours WHERE type_cours_id=?";
            PreparedStatement deleteCoursStatement = connection.prepareStatement(deleteCoursQuery);
            deleteCoursStatement.setInt(1, id);
            deleteCoursStatement.executeUpdate();

            // lena chnfasakh type kemel jemla
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Type deleted!");
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

    public boolean isNomExist(String nom) throws SQLException {
        // req pr verif si nom existe
        String query = "SELECT COUNT(*) FROM cours WHERE nom = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nom);
        ResultSet resultSet = statement.executeQuery();

        // nrecuperi result
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0; // ken akber m 0 nom deja mawjoud
        }

        return false; // ken vide nom n'existe pas
    }


    public void ModifierCours(String newNomText, int id) {
    }
    public TypeCours getTypeCoursByNom(String nom) {
        try {
            // Utilisez une requête SQL pour récupérer le TypeCours correspondant au nom
            String query = "SELECT * FROM type_cours WHERE nom = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();

            // Si un TypeCours correspondant est trouvé, retournez-le
            if (resultSet.next()) {
                return new TypeCours(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("objective"),
                        resultSet.getString("description"),
                        resultSet.getInt("calories")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si aucun TypeCours correspondant n'est trouvé
    }
}