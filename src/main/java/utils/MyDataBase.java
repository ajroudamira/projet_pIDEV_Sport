package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
    private final String URL="jdbc:mysql://127.0.0.1:3306/gymsync";
    private final String USER="root";
    private final String PWD="";
    private Connection cnx;
    private static MyDataBase instance;
    private MyDataBase(){
        try {
            cnx= DriverManager.getConnection(URL,USER,PWD);
            System.out.println("Connexion etablie");
        } catch (SQLException e) {
            System.out.println("erreur:"+e.getMessage());
        }
    }
    public static MyDataBase getInstance(){
        if(instance==null){
            instance=new MyDataBase();
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

