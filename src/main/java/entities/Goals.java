package entities;

public class Goals {
    private String nom, salle,horaire;
    private String objective;
    private int calories,duree;
    private int idC, idT;

    public Goals() {
    }

    public Goals(String nom, String salle, String horaire, String objective, int calories, int duree, int idC, int idT) {
        this.nom = nom;
        this.salle = salle;
        this.horaire = horaire;
        this.objective = objective;
        this.calories = calories;
        this.duree = duree;
        this.idC = idC;
        this.idT = idT;
    }
    public Goals(String nom, String salle, String horaire, String objective, int calories, int duree) {
        this.nom = nom;
        this.salle = salle;
        this.horaire = horaire;
        this.objective = objective;
        this.calories = calories;
        this.duree = duree;

    }


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSalle() {
        return this.salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getObjective() {
        return this.objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Goals{" +
                "nom='" + nom + '\'' +
                ", salle='" + salle + '\'' +
                ", horaire='" + horaire + '\'' +
                ", objective='" + objective + '\'' +
                ", calories=" + calories +
                ", duree=" + duree +
              //  ", idC=" + idC +
               // ", idT=" + idT +
                '}';
    }
}