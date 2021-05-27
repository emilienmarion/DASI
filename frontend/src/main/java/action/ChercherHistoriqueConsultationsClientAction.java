
package action;

import javax.servlet.http.*;
import metier.modele.*;
import metier.service.Service;

/**
 *
 * @author ithan
 */
public class ChercherHistoriqueConsultationsClientAction extends Action{

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
            request.setAttribute("consultation", cli.getConsultations());
            request.setAttribute("clientemp", false);
        }
        
        System.out.println("Je suis dans l'action chercher medium");
        System.out.println(cli);
        request.setAttribute("succes", succes);
    }
    
}