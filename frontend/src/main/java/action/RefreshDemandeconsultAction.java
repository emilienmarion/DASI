/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.*;
import metier.modele.Utilisateur;
import metier.service.*;

/**
 *
 * @author emilienmarion
 */
public class RefreshDemandeconsultAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
         System.out.println("Je suis dans RefreshDemandeconsultAction");
        
        Service service = new Service();
        
        HttpSession session = request.getSession();
        Long id= (Long) session.getAttribute("id");
        
        System.out.println("id" + id);
        Employe emp=  (Employe) service.chercherParId(id);
         boolean sucess=true;
         if(emp==null){
            sucess=false;
        }else{
        Consultation consult=service.obtenirDemandeConsultation(emp);
        request.setAttribute("Demandeconsultation", consult);
        if(consult!=null){
            long idConsult=consult.getId();
        session.setAttribute("idConsult", idConsult);
        }
         }
         request.setAttribute("succes", sucess);
    }
    
}
