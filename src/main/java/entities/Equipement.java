package entities;

import java.util.Date;

public class Equipement {
    private int id;
    private String nom;
    private String description;
    private String marque;
    private String modele;
    private String couleur;
    private String matiere;
    private String taille;
    private Date date_de_fabrication;
    private String image;
    private boolean etat;
    private int  idType;

    public Equipement() {
    }

    public Equipement(String nom, String description, String marque, String modele, String couleur, String matiere, String taille, Date date_de_fabrication, String image, boolean etat, int idType) {
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.matiere = matiere;
        this.taille = taille;
        this.date_de_fabrication = date_de_fabrication;
        this.image = image;
        this.etat = etat;
        this.idType = idType;
    }

    public Equipement(int id, String nom, String description, String marque, String modele, String couleur, String matiere, String taille, Date date_de_fabrication, String image, boolean etat, int idType) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.matiere = matiere;
        this.taille = taille;
        this.date_de_fabrication = date_de_fabrication;
        this.image = image;
        this.etat = etat;
        this.idType = idType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public Date getDate_de_fabrication() {
        return date_de_fabrication;
    }

    public void setDate_de_fabrication(Date date_de_fabrication) {
        this.date_de_fabrication = date_de_fabrication;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "Equipement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", couleur='" + couleur + '\'' +
                ", matiere='" + matiere + '\'' +
                ", taille='" + taille + '\'' +
                ", date_de_fabrication=" + date_de_fabrication +
                ", image='" + image + '\'' +
                ", etat=" + etat +
                ", idType=" + idType +
                '}'+'\n';
    }
}
