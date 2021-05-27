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
 * @author taha
 */
public class ObtenirListesMediumsSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MediumAstro> listeMediumAstro = (List<MediumAstro>) request.getAttribute("astros");
        List<MediumCarto> listeMediumCarto = (List<MediumCarto>) request.getAttribute("cartos");
        List<MediumSpirit> listeMediumSpirit = (List<MediumSpirit>) request.getAttribute("spirits");
        
        JsonObject container = new JsonObject();
        JsonArray jsonListeAstros = new JsonArray();
        JsonArray jsonListeCartos = new JsonArray();
        JsonArray jsonListeSpirits = new JsonArray();
        
        for (MediumAstro m : listeMediumAstro) {
            JsonObject jsonAstro = new JsonObject();
            System.out.println(m.getDenomination());
            jsonAstro.addProperty("numero", m.getId());
            jsonAstro.addProperty("nom", m.getDenomination());
            jsonAstro.addProperty("genre", m.getGenre());
            jsonAstro.addProperty("nbConsultation", m.getNbConsultation());
            jsonAstro.addProperty("photo", m.getPhoto());
            jsonAstro.addProperty("description", m.getPresentation());
            
            jsonAstro.addProperty("type", "Astrologue");
            jsonAstro.addProperty("formation", ((MediumAstro) m).getFormation());
            jsonAstro.addProperty("promotion", ((MediumAstro) m).getPromotion());
            jsonListeAstros.add(jsonAstro);
        }
        
        for (MediumCarto c : listeMediumCarto){
            JsonObject jsonCarto = new JsonObject();
            jsonCarto.addProperty("numero", c.getId());
            jsonCarto.addProperty("nom", c.getDenomination());
            jsonCarto.addProperty("genre", c.getGenre());
            jsonCarto.addProperty("nbConsultation", c.getNbConsultation());
            jsonCarto.addProperty("photo", c.getPhoto());
            jsonCarto.addProperty("description", c.getPresentation());
                
            jsonCarto.addProperty("type", "Cartomancien(ne)");
            jsonListeCartos.add(jsonCarto);
        }
        
        for (MediumSpirit s : listeMediumSpirit){
            JsonObject jsonSpi = new JsonObject();
            jsonSpi.addProperty("numero", s.getId());
            jsonSpi.addProperty("nom", s.getDenomination());
            jsonSpi.addProperty("genre", s.getGenre());
            jsonSpi.addProperty("nbConsultation", s.getNbConsultation());
            jsonSpi.addProperty("photo", s.getPhoto());
            jsonSpi.addProperty("description", s.getPresentation());
            
            jsonSpi.addProperty("type", "Spirit");
            jsonSpi.addProperty("support", ((MediumSpirit) s).getSupport());
            jsonListeSpirits.add(jsonSpi);
        }
            
        
        container.add("cartos", jsonListeCartos);
        container.add("astros", jsonListeAstros);
        container.add("spirits", jsonListeSpirits);

        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}