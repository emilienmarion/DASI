/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.modele.Consultation;
import metier.modele.Employe;

/**
 *
 * @author emilienmarion
 */
public class ConsultationDAO {
    
    
     public void createConsultation(Consultation consultation){
       JpaUtil.obtenirContextePersistance().persist(consultation);
       
   }
     
        public Consultation modify(Consultation consultation){
        
        return JpaUtil.obtenirContextePersistance().merge(consultation);
        
    }
        
        
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
}
