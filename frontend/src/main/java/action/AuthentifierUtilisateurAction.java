/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import metier.modele.*;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class AuthentifierUtilisateurAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
         System.out.println("je suis dans l'action");
          String mail = request.getParameter("login");
        String mdp = request.getParameter("password");
         Service service = new Service();

        boolean conex;
        Utilisateur u = service.authentifierClient(mail, mdp);
        Client c = null;
        Employe e=null;
        String user = null;
       /*
        if (u instanceof Client) {
            c = (Client) u;
            user="client";
        }else if(u instanceof Employe){
            e= (Employe) e;
            user="employe";
        }
*/
        if (u == null) {
            conex = false;
        } else {
            conex = true;
        }
         System.out.println("je suis dans l'action");
        System.out.println(u);
         request.setAttribute("connexion", conex);
         request.setAttribute("utilisateur",u );
         
         
         
    }
    
}
