/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author ithan
 */
public class CreerUtilisateurAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        System.out.println("Je suis dans l'action pour creer un utilisateur");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String datenaissance = request.getParameter("datenaissance");
        String addpostale = request.getParameter("addpostale");
        String numtel = request.getParameter("numtel");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String genre = request.getParameter("genre");
        
        System.out.println(nom + prenom + datenaissance);
        
        boolean succes = true;
        Client cli = null;
        
        Service service = new Service();
        try{
            cli = service.creerCompteClient(nom, prenom, email, password, datenaissance, numtel, genre, addpostale);
        }catch(Exception e){
            System.out.println("Exception lors de la creation du compte du nouveau client");
            succes = false;
        }
        
        if(cli == null){
            System.out.println("Exception lors de la creation du compte du nouveau client");
            succes = false;
        }
        
        request.setAttribute("connexion", succes);
        request.setAttribute("client", cli);
    }
    
}
