package entities;

import java.sql.Time;
import java.time.LocalTime;

public class Cours {
    private String nom, salle;
    private int id,/*id_type*/ duree ;
    private String horaire;
    public Cours() {

    }
    /*public Cours(int id_cours, int duree, String nom, String type, String horaire ) {
        this.id_cours = id_cours;
        this.duree = duree;
        this.nom = nom;
        this.type = type;
        this.horaire=horaire;
    }

    public Cours(int duree, String nom, String type, String horaire) {
        this.duree = duree;
        this.nom = nom;
        this.type = type;
        this.horaire=horaire;
    }

    public int getId() {
        return id_cours;
    }

    public void setId(int id) {
        this.id_cours = id_cours;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id_cours +
                ", duree=" + duree +
                ", horaire='" + horaire + '\'' +
                '}';
    } */
    public Cours(int id/*,int id_type */,int duree, String nom, String salle, String horaire ) {
        this.id = id;
      //  this.id_type = id_type;
        this.duree = duree;
        this.nom = nom;
        this.salle = salle;
        this.horaire=horaire;
    }

    public Cours(int duree,/*int id_type,*/ String nom, String salle, String horaire) {
        this.duree = duree;
      //  this.id_type=id_type;
        this.nom = nom;
        this.salle = salle;
        this.horaire=horaire;

    }

   /* public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle ) {
        this.salle = salle;
    }
    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "nom='" + nom + '\'' +
                ", salle='" + salle + '\'' +
                ", id=" + id +
              //  ", id_type=" + id_type +
                ", duree=" + duree +
                ", horaire='" + horaire + '\'' +
                '}';
    }
}

