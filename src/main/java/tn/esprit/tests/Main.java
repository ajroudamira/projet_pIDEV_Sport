package tn.esprit.tests;

import tn.esprit.entities.Equipement;
import tn.esprit.services.ServiceEquipement;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ServiceEquipement se=new ServiceEquipement();
        Equipement e=new Equipement("bbb","bbbb","bbb","abbba","aaa","aaa","aaa",new Date(),"aaa",true,1);
        //se.ajouter(e);

        //se.modifier(1,e);
        //se.supprimer(1);
        System.out.println(se.afficher());
    }
}