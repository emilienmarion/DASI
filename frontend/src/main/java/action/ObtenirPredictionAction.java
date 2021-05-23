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
        String amourS = request.getParameter("amour");
        String santeS = request.getParameter("sante");
        String travailS = request.getParameter("travail");
        int amour = Integer.parseInt(amourS);
        int sante = Integer.parseInt(santeS);
        int travail = Integer.parseInt(travailS);

        Service service = new Service();
        
        
        
       // service.obtenirPrédiction(consultation, amour, sante, travail);

       //on récupère la liste de List<String> des prdiction et on l'envoi en serialisation
    }

}
