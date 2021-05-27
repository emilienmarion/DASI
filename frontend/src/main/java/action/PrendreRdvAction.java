/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Utilisateur;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class PrendreRdvAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Je suis dans PrendreRDV");
        
        Service service = new Service();
        HttpSession session = request.getSession();
        Long idClient= (Long) session.getAttribute("id");
        
        Client c=  (Client) service.chercherParId(idClient);
        
        String idMediumS = request.getParameter("id");
        long idMedium = Long.parseLong(idMediumS);
        
        Medium m=service.chercherMediumtParID(idMedium);
        
        
        Employe emp=service.demanderconsultation(m, c);
        boolean sucess=true;
        
        if(emp==null){
            sucess=false;
        }
         request.setAttribute("succes", sucess);
       
        
        
    }
    
}
