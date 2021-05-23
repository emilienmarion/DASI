package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Utilisateur;

/**
 * Classe de DAO pour les consultations, elle s'occupe de persister les nouvelles consultations,
 * de les modifier mais aussi de les les trouveer dans la base de donnes
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
public class ConsultationDAO {
    
    /**
     * Methode qui persiste une consultation en base de donnes
     * @param consultation objet consultation a persister
     */
    public void createConsultation(Consultation consultation){
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
     
    /**
     * Methode qui modifie une consultation deja presente en base de donnes
     * @param consultation la consultation deja modifie en tant qu'objet, et qu'il faut donc mettre en base de donnes
     * @return la consultation modifie
     */
    public Consultation modify(Consultation consultation){
        return JpaUtil.obtenirContextePersistance().merge(consultation);
    }
        
    /**
     * Cette methode se charge d'obtenir une consultation en cours pour un employe donne, pour qu'il puisse la
     * reprendre par la suite
     * @param emp employe qui doit prendre la consultation
     * @return la consultation qui a ete demande par le clien mais n'as pas encore ete traite
     */
    public Consultation obtenirConsultationEmp(Employe emp){
        Consultation result=null;
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql="select c from Consultation c where c.employe= :emp and c.dateFin is NULL";
        TypedQuery query=em.createQuery(jpql, Consultation.class);
        query.setParameter("emp",emp);
        List<Consultation> cons = query.getResultList();

        if(!cons.isEmpty()){
            result = cons.get(0);
        }

        return result;
    }
    
     public Consultation chercherParId(Long consultId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultId);
    }
    
}
