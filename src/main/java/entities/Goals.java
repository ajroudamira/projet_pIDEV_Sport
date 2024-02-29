package entities;

public class Goals {
    private String nom, salle;
    private String objective;
    private int calories;
    private int idC, idT;

    public Goals() {
    }

    public Goals(String nom, String salle, String objective, int calories, int idC, int idT) {
        this.nom = nom;
        this.salle = salle;
        this.objective = objective;
        this.calories = calories;
        this.idC = idC;
        this.idT = idT;
    }

    public Goals(String nom, String salle, String objective, int calories) {
        this.nom = nom;
        this.salle = salle;
        this.objective = objective;
        this.calories = calories;
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

    @Override
    public String toString() {
        return "Goals{" +
                "nom='" + nom + '\'' +
                ", salle='" + salle + '\'' +
                ", objective='" + objective + '\'' +
                ", calories=" + calories +
                ", idC=" + idC +
                ", idT=" + idT +
                '}';
    }
}