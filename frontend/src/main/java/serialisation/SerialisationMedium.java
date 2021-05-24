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
 * @author ithan
 */
public class SerialisationMedium extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Je suis dans utilisateur serialisation");
        
        JsonObject container = new JsonObject();
        
        Medium m = (Medium) request.getAttribute("medium");
        boolean succes = (boolean) request.getAttribute("connexion");
        
        System.out.println("succes ="+ succes);
        System.out.println("medium"+  m);
        
        container.addProperty("succes", succes);
        if(succes && m != null){
            String type=null;
            JsonObject medium = new JsonObject();

            //Infos du medium a serialiser
            medium.addProperty("id", m.getId());
            medium.addProperty("denomination", m.getDenomination());
            medium.addProperty("genre", m.getGenre());
            medium.addProperty("nbConsultation", m.getNbConsultation());
            medium.addProperty("photo", m.getPhoto());
            medium.addProperty("presentation", m.getPresentation());
            
            if(m instanceof MediumAstro){
                MediumAstro ma = (MediumAstro) m;
                type = "Astrologue";
                medium.addProperty("promotion", ma.getPromotion());
                medium.addProperty("formation", ma.getFormation());
            }else if(m instanceof MediumCarto){
                type = "Cartomencien";
            }else if(m instanceof MediumSpirit){
                MediumSpirit ms = (MediumSpirit) m;
                type = "Spirit";
                medium.addProperty("support", ms.getSupport());
            }

            JsonObject Medium0 = new JsonObject();
            Medium0.addProperty("type", type);
            Medium0.add("infos", medium);
            container.add("medium", Medium0);
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
 * format de la reponse
{
  "succes": true,
  "medium": {
    "type": "Astrologue",
    "infos": {
      "id": 2,
      "denomination": "Serena",
      "genre": "H",
      "nbConsultation": 1,
      "photo": "URLphoto",
      "presentation": "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre\npassé",
      "promotion": "2006",
      "formation": "École Normale Supérieure d’Astrologie (ENS-Astro)"
    }
  }
}
 */
