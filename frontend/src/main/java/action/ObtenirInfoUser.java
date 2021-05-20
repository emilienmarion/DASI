/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import metier.modele.Employe;
import metier.modele.Utilisateur;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class ObtenirInfoUser extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        
       Service service = new Service();
        HttpSession session = request.getSession();
       long id= (long) session.getAttribute("id");
       
      Utilisateur u=  service.chercherParId(id);
      boolean conex;
      if(u==null){
          conex=false;
      }else{
          conex= true;
          request.setAttribute("utilisateur", u);
      }
      request.setAttribute("connexion", conex);
       
       
       
       
       
        



    }
    
}
