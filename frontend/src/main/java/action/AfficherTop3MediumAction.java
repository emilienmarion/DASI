/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author taha
 */
public class AfficherTop3MediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Action top 3 Medium");
        Service service = new Service();
        
        List<Medium> listeTop3Medium = service.top3medium();
        boolean aff = false;
        if(!listeTop3Medium.isEmpty()){
            aff = true;
        }
        
        System.out.println(listeTop3Medium.get(0));
        request.setAttribute("affichage", aff);
        request.setAttribute("top3Medium", listeTop3Medium);
    }
    
}
