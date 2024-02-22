package tn.esprit.test;

import tn.esprit.entities.Equipement;
import tn.esprit.entities.Type;
import tn.esprit.service.ServiceEquipement;
import tn.esprit.service.ServiceType;

import java.util.Date;
public class Main {
    public static void main(String[] args) {
        ServiceEquipement se=new ServiceEquipement();
        Equipement e=new Equipement("ccc","vvv","bbb","abba","aaa","aaa","aaa",new Date(),"aaa",true,1);
        //se.ajouter(e);
        //se.modifier(1,e);
        //se.supprimer(1);
        System.out.println(se.afficher());
        ServiceType st=new ServiceType();
        Type t=new Type("bb","bb");
        //st.ajouter(t);
        //st.modifier(1,t);
        //st.supprimer(1);
        System.out.println(st.afficher());
    }
}


