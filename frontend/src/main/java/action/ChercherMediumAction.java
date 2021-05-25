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
public class ChercherMediumAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        String denomination = (String) request.getParameter("denomination");
        Service service = new Service();
        Medium med = service.chercherMedium(denomination);
        boolean succes = false;
        
        if(med != null){
            succes = true;
            request.setAttribute("medium", med );
        }
        
        System.out.println("Je suis dans l'action chercher medium");
        System.out.println(med);
        request.setAttribute("connexion", succes);
    }
    
}
