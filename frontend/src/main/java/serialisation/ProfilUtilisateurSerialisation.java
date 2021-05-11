/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
         JsonObject container = new JsonObject();
         Utilisateur u =   (Utilisateur) request.getAttribute("utilisateur");
         boolean conex= (boolean) request.getAttribute("connexion");
        Client c = null;
        Employe e=null;
        String user;
        
         
         
          if (u instanceof Client) {
            c = (Client) u;
           
        }else if(u instanceof Employe){
            e= (Employe) e;
            
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
        
        PrintWriter out = this.getWriter(response);
         Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
         gson.toJson(container, out);
        out.close();
    }
    
    
    
}
