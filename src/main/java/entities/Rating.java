package entities;

public class Rating {

    int id_user;
    int id_produit;
    Double value ;


    public Rating(int id_user, int id_produit, Double value) {
        this.id_user = id_user;
        this.id_produit = id_produit;
        this.value = value;
    }

    public Rating() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id_user=" + id_user +
                ", id_produit=" + id_produit +
                ", value=" + value +
                '}';
    }
}
