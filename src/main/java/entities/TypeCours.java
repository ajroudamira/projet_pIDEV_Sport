package entities;

public class TypeCours {
   private int id ;
   private String nom, objective, description;

   private int calories;

   public TypeCours() {

   }

   public int getId() { return id;
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
   public String getObjective() {
      return objective;
   }

   public void setObjective(String objective) {
      this.objective = objective;
   }
   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }



   public int getCalories() {
      return calories;
   }

   public void setCalories(int calories) {
      this.calories = calories;
   }

   public TypeCours(int id, String nom, String objective, String description, int calories) {
      this.id = id;
      this.nom = nom;
     this.objective = objective;
      this.description = description;
      this.calories = calories;
   }

   public TypeCours(String nom, String objective, String description, int calories) {

      this.nom = nom;
    this.objective = objective;
      this.description = description;
      this.calories = calories;
   }


   @Override
   public String toString() {
      return "TypeCours{" +
             // "id=" + id +
              " nom='" + nom + '\'' +
             ", objective='" + objective + '\'' +
              ", description='" + description + '\'' +
              ", calories=" + calories +
              '}';
   }
}


