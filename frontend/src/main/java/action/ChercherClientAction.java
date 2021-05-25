/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.*;
import metier.modele.*;
import metier.service.Service;

/**
 *
 * @author ithan
 */
public class ChercherClientAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        Service service = new Service();
        Client cli = service.chercherClient(nom, prenom);
        boolean succes = false;
        
        if(cli != null){
            succes = true;
            request.setAttribute("utilisateur", cli );
        }
        
        System.out.println("Je suis dans l'action chercher medium");
        System.out.println(cli);
        request.setAttribute("connexion", succes);
    }
    
}
