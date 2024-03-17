package tn.esprit.services;

import tn.esprit.entities.Equipement;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceEquipement implements IService<Equipement>{
    private Connection cnx;
    public ServiceEquipement(){
        cnx= MyDataBase.getInstance().getCnx();
    }
    @Override
    public void ajouter(Equipement equipement)  {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String formatteddDate= sdf.format(equipement.getDate_de_fabrication());
        //int etat=equipement.isEtat()?1:0;
        int etat=1;
        if(equipement.isEtat()==false){
            etat=0;
        }
        try{
            String query="INSERT INTO `equipement`" +
                    "(`nom`, `description`," +
                    " `marque`, `modele`, " +
                    "`couleur`, `matiere`, " +
                    "`taille`, `date_de_fabrication`," +
                    " `image`, `etat`, `idType`) " +
                    "VALUES ('"+equipement.getNom()+"','"+equipement.getDescription()+"'," +
                    "'"+equipement.getMarque()+"','"+equipement.getModele()+"'," +
                    "'"+equipement.getCouleur()+"','"+equipement.getMatiere()+"'," +
                    "'"+equipement.getTaille()+"','"+formatteddDate+"'," +
                    "'"+equipement.getImage()+"','"+etat+"'," +
                    "'"+equipement.getIdType()+"')";
            Statement st= cnx.createStatement();
            st.executeUpdate(query);
        }catch (SQLException e){
            System.out.println("erreur:"+e.getMessage());
        }

    }

    @Override
    public void modifier(int id, Equipement equipement) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String formatteddDate= sdf.format(equipement.getDate_de_fabrication());
        //int etat=equipement.isEtat()?1:0;
        int etat=1;
        if(equipement.isEtat()==false){
            etat=0;
        }
        try{
            String query="UPDATE `equipement` SET " +
                    "`nom`='"+equipement.getNom()+"',`description`='"+equipement.getDescription()+"'," +
                    "`marque`='"+equipement.getMarque()+"',`modele`='"+equipement.getModele()+"'," +
                    "`couleur`='"+equipement.getCouleur()+"',`matiere`='"+equipement.getMatiere()+"'," +
                    "`taille`='"+equipement.getTaille()+"',`date_de_fabrication`='"+formatteddDate+"'," +
                    "`image`='"+equipement.getImage()+"',`etat`='"+etat+"'," +
                    "`idType`='"+equipement.getIdType()+"' WHERE id="+id;
            Statement st= cnx.createStatement();
            st.executeUpdate(query);
        }catch (SQLException e){
            System.out.println("erreur:"+e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try{
            String query="DELETE FROM `equipement` WHERE id="+id;
            Statement st= cnx.createStatement();
            st.executeUpdate(query);
        }catch (SQLException e){
            System.out.println("erreur:"+e.getMessage());
        }
    }

    @Override
    public List<Equipement> afficher() {
        List<Equipement> le=new ArrayList<>();
        try{
            String query="SELECT * FROM `equipement`";
            Statement st= cnx.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Equipement equipement=new Equipement();
                equipement.setId(rs.getInt("id"));
                equipement.setNom(rs.getString("nom"));
                equipement.setDescription(rs.getString("description"));
                equipement.setMarque(rs.getString("marque"));
                equipement.setModele(rs.getString("modele"));
                equipement.setCouleur(rs.getString("couleur"));
                equipement.setMatiere(rs.getString("matiere"));
                equipement.setTaille(rs.getString("taille"));
                equipement.setDate_de_fabrication(rs.getDate("date_de_fabrication"));
                equipement.setImage(rs.getString("image"));
                equipement.setEtat(rs.getBoolean("etat"));
                equipement.setIdType(rs.getInt("idType"));
                le.add(equipement);

            }
        }catch(SQLException e){
            System.out.println("erreur:"+e.getMessage());
        }

        return le;
    }
    public Equipement getEquipementById(int id){
        return afficher().stream().filter(e->e.getId()==id).findFirst().orElse(null);
    }
    public List<Equipement> triEquipementParCritere(String critere){
        switch (critere){
            case "Nom":
                return afficher().stream().sorted(Comparator.comparing(Equipement::getNom)).collect(Collectors.toList());
            case "Description":
                return afficher().stream().sorted(Comparator.comparing(Equipement::getDescription)).collect(Collectors.toList());
            case "Date":
                return afficher().stream().sorted(Comparator.comparing(Equipement::getDate_de_fabrication)).collect(Collectors.toList());
            case "Type":
                return afficher().stream().sorted(Comparator.comparing(Equipement::getIdType)).collect(Collectors.toList());
            case "Marque":
                return afficher().stream().sorted(Comparator.comparing(Equipement::getMarque)).collect(Collectors.toList());
        }
        return afficher();
    }
    public List<Equipement> getEquipementsParType(int idType){
        return afficher().stream().filter(e->e.getIdType()==idType).collect(Collectors.toList());
    }
}
