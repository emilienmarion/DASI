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
import javax.servlet.http.*;
import metier.modele.*;

/**
 *
 * @author ithan
 */
public class ProfilUtilisateurSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Je suis dans utilisateur serialisation");
        
        JsonObject container = new JsonObject();
        
        Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
        boolean conex = (boolean) request.getAttribute("connexion");
        System.out.println("conex = "+ conex);
        System.out.println("utilisateur"+  u);
        
        container.addProperty("connexion", conex);
        
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
            JsonObject profilAstral = new JsonObject();
            profilAstral.addProperty("signeChinois", c.getProfilAstral().getSigne_chinois());
            profilAstral.addProperty("signeZodiaque", c.getProfilAstral().getSigne_zodiac());
            profilAstral.addProperty("couleurBonheur", c.getProfilAstral().getCouleurPB());
            profilAstral.addProperty("animalTotem", c.getProfilAstral().getAnimal_totem());
            client.add("profilAstral", profilAstral);
            JsonObject userO = new JsonObject();
            userO.addProperty("type", user);
            userO.add("infos", client);
            container.add("user", userO);
        }else if(u instanceof Employe){
            e= (Employe) u;
            user="employe";
            employe.addProperty("id", e.getId());
            employe.addProperty("nom", e.getNom());
            employe.addProperty("Prenom", e.getPrenom());
            employe.addProperty("mail", e.getMail());
            employe.addProperty("dateNaissance", e.getDate_naissance());
            employe.addProperty("genre", e.getGenre());
            employe.addProperty("numTel", e.getNum_tel());
            employe.addProperty("nbConsultations", e.getNb_consultations());
            JsonObject userO = new JsonObject();
            userO.addProperty("type", user);
            userO.add("infos", employe);
            container.add("user", userO);
        }
        System.out.println(container);
        System.out.println("fini la sérialisation");

        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
}
/**
 * Format Json de la reponse de la serialisation en cas de succes
 * {
 * "connexion": true,
 * "user": {
 *   "type": "client",
 *   "infos": {
 *    "id": 1,
 *    "nom": "marion",
 *    "Prenom": "léa",
 *     "mail": "lemail@.fr",
 *     "dateNaissance": "20/08/2000",
 *     "genre": "F",
 *     "adressePostale": "26 rue des routes",
 *     "numTel": "0782728262",
 *     "profilAstral": {
 *       "signeChinois": "Dragon",
 *       "signeZodiaque": "Lion",
 *       "couleurBonheur": "Mauve",
 *       "animalTotem": "Pécaris"
 *     }
 *   }
 *  }
 * }
 */
