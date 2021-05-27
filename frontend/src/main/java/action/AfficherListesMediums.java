/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.MediumAstro;
import metier.modele.MediumCarto;
import metier.modele.MediumSpirit;
import metier.service.Service;

/**
 *
 * @author jdfab
 */
public class AfficherListesMediums extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Action Listes Mediums");
        Service service = new Service();
        
        List<MediumAstro> listeAstro = service.listeMediumAstro();
        List<MediumCarto> listeCarto = service.listeMediumCarto();
        List<MediumSpirit> listeSpirit = service.listeMediumSpirit();
        
        for (MediumSpirit s : listeSpirit){
             System.out.println(s);
            
        }
                
        request.setAttribute("astros", listeAstro);
        request.setAttribute("cartos", listeCarto);
        request.setAttribute("spirits", listeSpirit);
    }
}
