/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Utilisateur;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class HistoriqueClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        Service service = new Service();
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute("id");
        Utilisateur u = service.chercherParId(id);
        List<Consultation> listeConsultation = service.obtenirHistoriqueClient((Client) u);
        boolean conex;
        if(u==null || listeConsultation.isEmpty()){
            conex=false;
        }else{
            conex= true;
            request.setAttribute("client", u);
        }
        request.setAttribute("succes", conex);
        request.setAttribute("clientemp", true);
        request.setAttribute("consultation", listeConsultation);

    }

}
