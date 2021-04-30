/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.*;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Utilisateur;

/**
 *
 * @author emilienmarion
 */
public class UtilisateurDao {
    
      public Utilisateur chercherParId(Long clientId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Utilisateur.class, clientId); // renvoie null si l'identifiant n'existe pas
    }

    
    
    public void createUser(Utilisateur utilisateur){
       JpaUtil.obtenirContextePersistance().persist(utilisateur);
       
      
   }
    
    
    public Utilisateur modify(Utilisateur utilisateur){
        
        return JpaUtil.obtenirContextePersistance().merge(utilisateur);
        
    }
    
    public Utilisateur cherchermail(String mail){
         EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql="select c from Utilisateur c where c.mail= :mail ";
        TypedQuery query=em.createQuery(jpql, Utilisateur.class);
         query.setParameter("mail",mail);
          List<Utilisateur> Users = query.getResultList(); //getsingleresult
           Utilisateur result = null;
        if (!Users.isEmpty()) {
            result = Users.get(0); // premier de la liste
        }
        return result;
    }

    //match entre le medium demandé par le client et un employé
    //( pas en  ligne avec un autre client, genre medium=genre emp, et prend l’employe qui rempli ces critère qui a le moins de consultation)

   
   
    public Employe matchMedium(Medium medium){
        Employe result=null;
        String genreMedium=medium.getGenre();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql="select e from Employe e where e.genre= :genreMedium and e.statut_en_ligne = false ORDER BY e.nb_consultations  ";
        TypedQuery query=em.createQuery(jpql, Employe.class);
         query.setParameter("genreMedium",genreMedium);
         List<Employe> emps = query.getResultList();
         /*
         if(!emps.isEmpty()){
            if (emps.size()>1){
                int nb=10000;
                    for (Employe emp1 : emps) {// prendre l'employe avec le moins de nb de consultations
                        System.out.println(emps);
                            if (emp1.getNb_consultations()<nb){
                                nb=emp1.getNb_consultations();
                                result=emp1;  
                                
                            }
                    }
            }else{*/
         
         if(emps.isEmpty()){
             result=null;
         }else{
             result = emps.get(0);
            }
         
        // }else{
            // result=null;
        // }
        return result;
        
         
}
    

}