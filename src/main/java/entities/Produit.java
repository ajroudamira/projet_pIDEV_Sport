package entities;

public class Produit {


    private int id_produit;
    private String nom;
    private double prix;
    private String description;
    private int id_categorie;

    private String ImagePath ;


    public Produit()
    {

    }

    public Produit(int id_produit, String nom, double prix, String description, int id_categorie, String imagePath) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.id_categorie = id_categorie;
        ImagePath = imagePath;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", id_categorie=" + id_categorie +
                ", ImagePath='" + ImagePath + '\'' +
                '}';
    }
}

