/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import dao.UtilisateurDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.*;
import metier.service.Service;

/**
 *
 * @author ithan
 * @deprecated 
 */
public class ObtenirConsultations extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("je suis dans l'action obtenirConsultations");
        //
        //
        //A changer apres avoir discuter!!!!!!!!!!!!!!!
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Service service = new Service();
        Utilisateur u = service.chercherClient(nom, prenom);
        //ATENTION!!!!!!!!!!!!!!
        //
        //
        boolean connex = false;
        List<Consultation> listeCons = null;
        
        if(u instanceof Client){
            Client c = (Client) u;
            listeCons = c.getConsultations();
            connex = true;
        }else if(u instanceof Employe){
            Employe e = (Employe) u;
            listeCons = e.getConsultations();
            connex = true;
        }     
        
        System.out.println(u);
        request.setAttribute("connexion", connex);
        request.setAttribute("utilisateur",u );
        request.setAttribute("consultation", listeCons);
    }
    
}
