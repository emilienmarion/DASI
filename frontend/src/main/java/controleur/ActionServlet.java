/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;
import action.*;

import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serialisation.*;

/**
 *
 * @author emilienmarion
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // try ( PrintWriter out = response.getWriter()) {

        JsonObject container = new JsonObject();
        Action action = null;
        Serialisation serialisation = null;

      //System.out.println("je suis avant le case");
         String todo = request.getParameter("todo");
         
         System.out.println("****" + todo);
        switch(todo){
            case "connecter" :{
                System.out.println("je suis dans le case connecter");
                action = new AuthentifierUtilisateurAction();
                serialisation=new ProfilUtilisateurSerialisation();
                
            }
            break;
            
            case "creerCompte" :{
                System.out.println("Je suis dans le case creerCompte");
                action = new CreerUtilisateurAction();
                serialisation = new ProfilUtilisateurSerialisation();
            }
            
            case "retrouverConsultations" : {
                System.out.println("Je suis dans le case retrouverConsultations");
                action = new ObtenirConsultations();
                serialisation = new ProfilUtilisateurSerialisation();
            }
            
             case "obtenirInfoEmploye" :{
                 
                action=new ObtenirInfoUser();
                serialisation = new ProfilUtilisateurSerialisation();
                
                
            }
            
            
        }
        if (action != null && serialisation != null) {
            action.executer(request); // Exécuter l'Action
            serialisation.serialiser(request, response); // Sérialiser le résultat de l'Action
        }else { // Erreur: pas d'Action ou de Sérialisation pour traiter cette requête
            response.sendError(400, "Bad Request (pas d'Action ou de Serialisation pour traiter cette requete)");
        }
    }

       
      

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
