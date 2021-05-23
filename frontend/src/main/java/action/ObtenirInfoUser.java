/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;

import javax.servlet.http.*;

import metier.modele.*;

import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class ObtenirInfoUser extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Je suis dans ObtenirInfoUser");
        
        Service service = new Service();
        HttpSession session = request.getSession();
        Long id= (Long) session.getAttribute("id");
        System.out.println("id" + id);
        Utilisateur u=  service.chercherParId(id);
        boolean conex;
        if(u==null){
            conex=false;
        }else{
            conex= true;
            request.setAttribute("utilisateur", u);
            if(u instanceof Client){
                Client c = (Client) u;
                List<Consultation> listeCons = c.getConsultations();
                request.setAttribute("consultation", listeCons);
            }else if(u instanceof Employe){
                Employe e = (Employe) u;
                List<Consultation> listeCons = e.getConsultations();
                request.setAttribute("consultation", listeCons);
            }
        }
        request.setAttribute("connexion", conex);
    }
    
}