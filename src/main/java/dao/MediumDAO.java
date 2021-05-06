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
 * Classe qui gere la DAO pour les mediums. Elle s'occupe de persister un medium en memoire, modifier un medium
 * deja existant et de retrouver un medium
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
public class MediumDAO {
    
    /**
     * Methode qui persiste un medium en base de donnes
     * @param medium medium a persister
     */
    public void createMedium(Medium medium){
       JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
    /**
     * Methode qui modifie un medium deja present en base de donnes
     * @param medium medium deja modifie en tant qu'objet, et qu'il faut donc mettre en base de donnes
     * @return la medium modifie
     */
    public Medium modify(Medium medium){
        return JpaUtil.obtenirContextePersistance().merge(medium);
    }
    
    /**
     * Methode qui renvoie une liste de mediums triees en fonction de leur nombre de leurs
     * consultations par ordre decroissant, utilie nottament lors des statistiques
     * @return liste des mediums triee en fonction de leur nombre de consultations par ordre decroissant 
     */
    public List<Medium> obtenirMedium(){
         
        EntityManager em = JpaUtil.obtenirContextePersistance();
           
        String jpql="select m from Medium m ORDER BY m.nbConsultation DESC  ";
       
        TypedQuery query=em.createQuery(jpql, Medium.class);
        
        List<Medium> result = query.getResultList();
        
        return result;
    }
    
    
    
     
    
    
    /**
     * Retrouve un medium en base de donnes grace a sa denomination
     * @param mediumDenomination denomination du medium a retrouver
     * @return objet medium qui a la denomination passe en parametre
     */
    public Medium chercherMediumParDenomination(String mediumDenomination){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m WHERE m.denomination = :denomination", Medium.class);
        query.setParameter("denomination", mediumDenomination); // correspond au paramètre ":denomination" dans la requête
        Medium result = query.getSingleResult();
        return result;
    }
     
    /**
     * Methode qui renvoie une liste de tous les mediums cartomenciens presentes en base de donnees
     * @return liste de tous les mediums cartomenciens
     */
    public List<MediumCarto> obtenirCarto(){
        EntityManager em = JpaUtil.obtenirContextePersistance();   
        String jpql="select m from MediumCarto m ";
        TypedQuery query=em.createQuery(jpql, MediumCarto.class);
        List<MediumCarto> result = query.getResultList();
        return result;
    }
    /**
     * Methode qui renvoie une liste de tous les mediums astrologues presentes en base de donnees
     * @return liste de tous les mediums astrologue
     */
    public List<MediumAstro> obtenirAstro(){
        EntityManager em = JpaUtil.obtenirContextePersistance();   
        String jpql="select m from MediumAstro m ";
        TypedQuery query=em.createQuery(jpql, MediumAstro.class);
        List<MediumAstro> result = query.getResultList();
        return result;
    }
    
    /**
     * Methode qui renvoie une liste de tous les mediums spirites presentes en base de donnees
     * @return liste de tous les mediums spirite
     */
    public List<MediumSpirit> obtenirSpirit(){
        EntityManager em = JpaUtil.obtenirContextePersistance();   
        String jpql="select m from MediumSpirit m ";
        TypedQuery query=em.createQuery(jpql, MediumSpirit.class);
        List<MediumSpirit> result = query.getResultList();
        return result;
    }
    
}
