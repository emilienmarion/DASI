/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.*;

/**
 *
 * @author emilienmarion
 */
public class ProfilUtilisateurSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Je suis dans utilisateur serialisation");
        
        JsonObject container = new JsonObject();
        
        Utilisateur u =   (Utilisateur) request.getAttribute("utilisateur");
        boolean conex = (boolean) request.getAttribute("connexion");
        System.out.println("conex ="+ conex);
        System.out.println("utilisateur"+  u);
        
        
        Client c = null;
        Employe e=null;
        String user=null;
        
        JsonObject client = new JsonObject();
        JsonObject employe = new JsonObject();

         
        if (u instanceof Client) {
            //il s'agit d'un client
            c = (Client) u;
            user="client";
            client.addProperty("id", c.getId());
            client.addProperty("nom", c.getNom());
            client.addProperty("Prenom", c.getPrenom());
            client.addProperty("mail", c.getMail());
            client.addProperty("dateNaissance", c.getDate_naissance());
            client.addProperty("genre", c.getGenre());
            client.addProperty("adressePostale", c.getAdresse_postale());
            client.addProperty("numTel", c.getNum_tel());
            client.addProperty("motDePasse", c.getMotDePasse());
            
        }else if(u instanceof Employe){
            e= (Employe) e;
            user="employe";
            employe.addProperty("id", e.getId());
            employe.addProperty("nom", e.getNom());
            employe.addProperty("Prenom", e.getPrenom());
            employe.addProperty("mail", e.getMail());
            employe.addProperty("dateNaissance", e.getDate_naissance());
            employe.addProperty("genre", e.getGenre());
            employe.addProperty("numTel", e.getNum_tel());
            employe.addProperty("motDePasse", e.getMotDePasse());
            employe.addProperty("nbConsultations", e.getNb_consultations());
            
        }
         
        container.addProperty("connexion", conex);
        if(conex && c != null){
            //il s'agit d'un client
            JsonObject userO = new JsonObject();
            userO.addProperty("type", user);
            userO.add("infos", client);
            container.add("UserO", userO);
        }else if (conex && e != null){
            //il s'agit d'un employe
            JsonObject userO = new JsonObject();
            userO.addProperty("type", user);
            userO.add("infos", employe);
            container.add("UserO", userO);
        }
         System.out.println(container);
        
        List<Consultation> listeCons =   (List<Consultation>) request.getAttribute("consultation");
        
        JsonObject containerCons = new JsonObject();
        
        if(conex){
            JsonArray jsonListeConsDemandee = new JsonArray();
            JsonArray jsonListeConsTerminee = new JsonArray();
            JsonArray jsonListeConsEnCours = new JsonArray();
            for(Consultation cons : listeCons){
                JsonObject jsonCons = new JsonObject();
                jsonCons.addProperty("id", cons.getId() );
                jsonCons.addProperty("employeID", cons.getEmploye().getId() );
                jsonCons.addProperty("clientID", cons.getClient().getId() );
                jsonCons.addProperty("MediumID", cons.getMedium().getId() );
                if(cons.getDateDeb()==null && cons.getDateFin()==null && cons.getDemandeConsult()!=null){    
                    jsonCons.addProperty("DateDemande", cons.getDemandeConsult().toString() );
                    jsonListeConsDemandee.add(jsonCons);
                } else if (cons.getDateDeb()!=null && cons.getDateFin()==null && cons.getDemandeConsult()!=null){
                    jsonCons.addProperty("DateDemande", cons.getDemandeConsult().toString() );
                    jsonCons.addProperty("DateDebut", cons.getDateDeb().toString());
                    jsonListeConsEnCours.add(jsonCons);
                }else if (cons.getDateDeb()!=null && cons.getDateFin()!=null && cons.getDemandeConsult()!=null){
                    jsonCons.addProperty("DateDemande", cons.getDemandeConsult().toString() );
                    jsonCons.addProperty("DateDebut", cons.getDateDeb().toString());
                    jsonCons.addProperty("DateFin", cons.getDateFin().toString());
                    jsonListeConsTerminee.add(jsonCons);
                }
            }
            containerCons.add("consDemandee", jsonListeConsDemandee);
            containerCons.add("consEnCours", jsonListeConsEnCours);
            containerCons.add("consTerminee", jsonListeConsTerminee);
        }
        
        
        container.add("consultations", containerCons);
        
        System.out.println("fini la sérialisation");
        System.out.println(container);
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
    
    
}
