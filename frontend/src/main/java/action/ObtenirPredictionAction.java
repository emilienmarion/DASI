/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.*;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class ObtenirPredictionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
         System.out.println("Je suis dans ObtenirIprediction Action");
        String amour = request.getParameter("amour");
         System.out.println("amour : "+amour);
        
        /*String mdp = request.getParameter("password");
         Service service = new Service();

        boolean suces;
        Utilisateur u = service.authentifierClient(mail, mdp);
        Client c = null;
        Employe e=null;
        String user = null;
      
        if (u == null) {
            conex = false;
        } else {
            conex = true;
             HttpSession session = request.getSession(true);
             session.setAttribute("id", u.getId());
        }
         System.out.println("je suis dans l'action");
        System.out.println(u);
         request.setAttribute("connexion", conex);
         request.setAttribute("utilisateur",u );
         
        
         
    }
       */ 
        
    }
    
}
