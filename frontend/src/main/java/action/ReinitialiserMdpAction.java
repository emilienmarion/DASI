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
 * @author jdfab
 */
public class ReinitialiserMdpAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("je suis dans l'action");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("login");
        String date = request.getParameter("date");
        String tel = request.getParameter("tel");
        String mdp = request.getParameter("newpassword");
        String mdp2 = request.getParameter("newpassword2");
        
        Service service = new Service();

        boolean u = service.reinitialiserMdp(nom, prenom, mail, date, tel, mdp, mdp2);
        
       
        System.out.println("je suis dans l'action");
        System.out.println(u);
        request.setAttribute("reinitialisation", u);
    }
}
