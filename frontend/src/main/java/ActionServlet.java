/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import action.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import metier.service.Service;
import metier.modele.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serialisation.ProfilUtilisateurSerialisation;
import serialisation.Serialisation;

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
        switch(todo){
            case "connecter" :{
                System.out.println("je suis dans le case");
                action = new AuthentifierUtilisateurAction();
                serialisation=new ProfilUtilisateurSerialisation();
                
            }
            break;
        }
        if (action != null && serialisation != null) {
 action.executer(request); // Exécuter l'Action
 serialisation.serialiser(request, response); // Sérialiser le résultat de l'Action
 }
 else { // Erreur: pas d'Action ou de Sérialisation pour traiter cette requête
 response.sendError(400, "Bad Request (pas d'Action ou de Serialisation pour traiter cette requete)");
 }
 }

       
       /* Service service = new Service();
        boolean conex;
//        Utilisateur u = service.authentifierClient(mail, mdp);
        Client c = null;
        System.out.println();
        if (u instanceof Client) {
            c = (Client) u;
        }
        if (u == null) {
            conex = false;
        } else {
            conex = true;
        }
        container.addProperty("connexion", conex);
        if(conex){
        JsonObject client = new JsonObject();
        client.addProperty("id", c.getId());
        client.addProperty("nom", c.getNom());
        client.addProperty("Prenom", c.getPrenom());
        client.addProperty("mail", c.getMail());

        container.add("client", client);
        }
        System.out.println("je suis laaaa");
System.out.println(container);
        response.setContentType(" application / json ; charset =UTF -8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
*/
        //System.out.println(c);
        /* TODO output your page here. You may use following sample code. */
 /* out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
             out.println("<h1> Coucou je suis une servlet de bain</h1>");
              out.println(mail);
              out.println(mdp);
              out.println(u.getDate_naissance());
              out.println(c.getProfilAstral());
              
            out.println("</body>");
            out.println("</html>");*/
    

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
