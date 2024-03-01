package entities;

public class Cours {
    private int id;
    private int duree;
    private String nom;
    private String salle;
    private String horaire;
    private int typeCoursId; // Nouvel attribut pour la clé étrangère
    private TypeCours typeCours; // Utilisation de l'objet TypeCours


    public Cours() {

    }

  /*  public Cours(int id, int duree, String nom, String salle, String horaire, int typeCoursId, TypeCours typeCours) {
        this.id = id;
        this.duree = duree;
        this.nom = nom;
        this.salle = salle;
        this.horaire = horaire;
        this.typeCoursId = typeCoursId;
        this.typeCours = typeCours;
    }*/
    public Cours( int duree, String nom, String salle, String horaire, /*int typeCoursId,*/ TypeCours typeCours) {

        this.duree = duree;
        this.nom = nom;
        this.salle = salle;
        this.horaire = horaire;
       // this.typeCoursId = typeCoursId;
        this.typeCours = typeCours;
    }

    public Cours(int id, String nom, String salle, int duree, String horaire, TypeCours typeCours) {
        this.id = id;
        this.duree = duree;
        this.nom = nom;
        this.salle = salle;
        this.horaire = horaire;
     //   this.typeCoursId = typeCoursId;
        this.typeCours = typeCours;
    }

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

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public int getTypeCoursId() {
        return typeCoursId;
    }

    public void setTypeCoursId(int typeCoursId) {
        this.typeCoursId = typeCoursId;
    }

    public TypeCours getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(TypeCours typeCours) {
        this.typeCours = typeCours;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", duree=" + duree +
                ", nom='" + nom + '\'' +
                ", salle='" + salle + '\'' +
                ", horaire='" + horaire + '\'' +
              //  ", typeCoursId=" + typeCoursId +
                ", typeCours=" + typeCours +
                '}';
    }
}


