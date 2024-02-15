package service;
import java.sql.SQLException;
import java.util.List;
public interface IService<T> {
    void ajouter(T t) ;
    void modifier(int id,T t);
    void supprimer(int id);
    List<T> afficher();
}
