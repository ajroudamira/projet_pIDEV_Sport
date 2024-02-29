package tests;
import entities.TypeCours;
import services.ServiceType;
import entities.Cours;
import services.ServiceCours;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        ServiceType serviceType = new ServiceType();
        TypeCours t1 = new TypeCours("farah" ,"strength", "strength", 1);
        TypeCours t2 = new TypeCours("ahmed", "balance", "light", 2);
        TypeCours t3 = new TypeCours("raja", "yoga", "lighter", 2);


        try {
          //serviceType.ajouter(t1);
            // serviceType.ajouter(t2);
            // serviceType.ajouter(t3);
             //serviceType.modifier(t3);
          // serviceType.modifier( "tennis",31);
            //  ser.modifiercours(3, 22) ;
           // serviceType.supprimer(30);
            //    Type t = serviceType.getOneById(4);
            //serviceType.modifier(t2);

           // serviceType.modifier(t2);
            System.out.println(serviceType.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        ServiceCours serviceCours = new ServiceCours();
        Cours c4 = new Cours(2,32 ,"aerobic", "oxygym", "15h");
        Cours c5 = new Cours(3, 2,"strength", "california", "17h");
        Cours c6 = new Cours(2, 4,"balance", "gym", "18h");


        try {
          serviceCours.ajouter(c4);
           // serviceCours.ajouter(c5);
            // serviceCours.ajouter(c6);
           // serviceCours.modifier(c6);
            // serviceCours.supprimer(32);
          // serviceCours.modifier( "fit",40);






            System.out.println(serviceCours.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

