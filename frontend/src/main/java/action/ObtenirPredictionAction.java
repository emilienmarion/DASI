/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            System.out.println("Je suis dans ObtenirIprediction Action");
            String amourS = request.getParameter("amour");
            String santeS = request.getParameter("sante");
            String travailS = request.getParameter("travail");
            int amour = Integer.parseInt(amourS);
            int sante = Integer.parseInt(santeS);
            int travail = Integer.parseInt(travailS);
            Service ser = new Service();
            HttpSession session = request.getSession();
            Long idConsult = (Long) session.getAttribute("idConsult");
            Consultation consultation = ser.chercherConsultParID(idConsult);
            
            List<String> predictions = ser.obtenirPrédiction(consultation, amour, sante, travail);
            request.setAttribute("prediction",predictions);
            
            //on récupère la liste de List<String> des prdiction et on l'envoi en serialisation
        } catch (IOException ex) {
            Logger.getLogger(ObtenirPredictionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
