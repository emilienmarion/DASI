package dao;

import java.util.List;
import javax.persistence.*;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Utilisateur;

/**
 * Classe qui gere la dao pour tous les utilisateurs. Elle s'occupe de persister les utilisateurs en memoire,
 * les modifier et de les retrouver
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
public class UtilisateurDao {
    
    /**
     * Methode qui retrouve un utilisateur en base de donnes grace a son identifian
     * @param clientId identifiant de l'utilisateur a retrouver en base de donnees
     * @return objet utilisateur qui a l'identifiant passe en parametre, renvoie null s'il
     * n'existe aucun utilisateur avec un tel identifiant
     */
    public Utilisateur chercherParId(Long clientId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Utilisateur.class, clientId);
    }

    /**
     * Methode qui persiste un utilisateur en base de donnes
     * @param utilisateur utilisateur a persister
     */
    public void createUser(Utilisateur utilisateur){
        JpaUtil.obtenirContextePersistance().persist(utilisateur);
    }
    
    /**
     * Methode qui modifie un utilisateur deja present en base de donnes
     * @param utilisateur utilisateur deja modifie en tant qu'objet, et qu'il faut donc mettre en base de donnes
     * @return l'utilisateur modifie
     */
    public Utilisateur modify(Utilisateur utilisateur){
        return JpaUtil.obtenirContextePersistance().merge(utilisateur);
    }
    
    /**
     * Methode qui retrouve un utilisateur a partir de son adresse email
     * @param mail email de l'utilisateur qu'on souhaite trouver
     * @return l'utilisateur qui a comme adresse mail celle passe en parametre, null si un tel utilisateur 
     * n'existe pas
     */
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
    
    /**
     * Methode qui retrouve un utilisateur a partir de son nom et prenom
     * @param userNom nom de l'utilisateur a retrouver
     * @param userPrenom prenom de l'utilisateur a retrouver
     * @return utilisateur qui a le nom et prenom passes en parametre, null si un tel utilisateur n'existe pas
     */
    public Client chercherUserParDenomination(String userNom, String userPrenom){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Utilisateur c WHERE c.nom = :nom and c.prenom = :prenom", Client.class);
        query.setParameter("nom", userNom);
        query.setParameter("prenom", userPrenom); 
        Client result = query.getSingleResult();
        return result;
    }

   /**
    * Methode qui fait correspondre un employe au medium demande pour une consultation par un client
    * @param medium medium souhaite par le clien pour la consultation
    * @return liste d'employes qui ont le meme sexe que le medium et sont en ligne, triee en fonction du nombre des 
    * consultation de chaque employe par ordre croissant
    */
    public List<Employe> matchMedium(Medium medium){
        Employe result=null;
        String genreMedium=medium.getGenre();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql="select e from Employe e where e.genre= :genreMedium and e.statut_en_ligne = false ORDER BY e.nb_consultations  ";
        TypedQuery query=em.createQuery(jpql, Employe.class);
        query.setParameter("genreMedium",genreMedium);
        List<Employe> emps = query.getResultList();
        return emps;
    }
    
    /**
     * Methode qui renvoie tous les employes en base de donnes triees en fonction de leur nombre de consultations
     * par ordre decroissant, utilie notament lors de l'affichage des statistiques
     * @return liste triee des employes en fonctoin de leur nombre de consultations par ordre decroissant
     */
    public List<Employe> obtenirEmploye(){
         
        EntityManager em = JpaUtil.obtenirContextePersistance();
           
        String jpql="select e from Employe e ORDER BY e.nb_consultations DESC  ";
       
        TypedQuery query=em.createQuery(jpql, Employe.class);
        
        List<Employe> result = query.getResultList();
   
        return result;
    }
    
    

}