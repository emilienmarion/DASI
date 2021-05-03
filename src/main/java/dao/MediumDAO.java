/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.MediumAstro;
import metier.modele.MediumCarto;
import metier.modele.MediumSpirit;
import metier.modele.Utilisateur;

/**
 *
 * @author emilienmarion
 */
public class MediumDAO {
    
    
    public void createMedium(Medium medium){
       JpaUtil.obtenirContextePersistance().persist(medium);
       
      
   }
     public Medium modify(Medium medium){
        
        return JpaUtil.obtenirContextePersistance().merge(medium);
        
    }
     
     public List<Medium> obtenirMedium(){
         
          EntityManager em = JpaUtil.obtenirContextePersistance();
           
        String jpql="select m from Medium m ORDER BY m.nbConsultation DESC  ";
       
        TypedQuery query=em.createQuery(jpql, Medium.class);
        
         List<Medium> result = query.getResultList();
          
         
       
         return result;
     }
     
     
     
     public Medium chercherMediumParDenomination(String mediumDenomination){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m WHERE m.denomination = :denomination", Medium.class);
        query.setParameter("denomination", mediumDenomination); // correspond au paramètre ":denomination" dans la requête
        Medium result = query.getSingleResult();
        return result;
     }
     
     
     public List<MediumCarto> obtenirCarto(){
        EntityManager em = JpaUtil.obtenirContextePersistance();   
        String jpql="select m from MediumCarto m ";
        TypedQuery query=em.createQuery(jpql, MediumCarto.class);
        List<MediumCarto> result = query.getResultList();
        return result;
     }
     
     public List<MediumAstro> obtenirAstro(){
        EntityManager em = JpaUtil.obtenirContextePersistance();   
        String jpql="select m from MediumAstro m ";
        TypedQuery query=em.createQuery(jpql, MediumAstro.class);
        List<MediumAstro> result = query.getResultList();
        return result;
     }
     
     public List<MediumSpirit> obtenirSpirit(){
        EntityManager em = JpaUtil.obtenirContextePersistance();   
        String jpql="select m from MediumSpirit m ";
        TypedQuery query=em.createQuery(jpql, MediumSpirit.class);
        List<MediumSpirit> result = query.getResultList();
        return result;
     }
    
}
