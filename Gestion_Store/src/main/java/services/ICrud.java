package services;


import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> {
    void create(T entity) throws SQLException;
    List<T> getAll() throws SQLException;
    void update(T categorie)  throws SQLException;
    void delete(int id) throws SQLException;
}