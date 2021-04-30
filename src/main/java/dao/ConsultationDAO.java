/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import metier.modele.Consultation;

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
}
