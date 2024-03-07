package tn.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {
    private final String URL="jdbc:mysql://127.0.0.1:3306/gym";
    private final String USER="root";
    private final String PWD="";
    private Connection cnx;
    private static MyDatabase instance;
    private MyDatabase(){
        try {
            cnx= DriverManager.getConnection(URL,USER,PWD);
            System.out.println("Connexion etablie");
        } catch (SQLException e) {
            System.out.println("erreur:"+e.getMessage());
        }
    }
    public static MyDatabase getInstance(){
        if(instance==null){
            instance=new MyDatabase();
        }
        else{
            System.out.println("deja connecter");
        }
        return instance;

    }
    public Connection getCnx(){
        return cnx;
    }


}
