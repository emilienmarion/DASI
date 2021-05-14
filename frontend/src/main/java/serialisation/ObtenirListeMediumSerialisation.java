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
public class ObtenirListeMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> listeTop3Medium = (List<Medium>) request.getAttribute("top3Medium");

        JsonObject container = new JsonObject();
        JsonArray jsonListeTop3Medium = new JsonArray();
        for (Medium m : listeTop3Medium) {
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("numero", m.getId());
            jsonMedium.addProperty("nom", m.getDenomination());
            jsonMedium.addProperty("genre", m.getGenre());
            jsonMedium.addProperty("nbConsultation", m.getNbConsultation());
            jsonMedium.addProperty("photo", m.getPhoto());
            jsonMedium.addProperty("description", m.getPresentation());
            if (m instanceof MediumAstro) {
                jsonMedium.addProperty("type", "Astrologue");
                jsonMedium.addProperty("formation", ((MediumAstro) m).getFormation());
                jsonMedium.addProperty("promotion", ((MediumAstro) m).getPromotion());
            } else if (m instanceof MediumCarto) {
                jsonMedium.addProperty("type", "Cartomancien(ne)");
            } else if (m instanceof MediumSpirit) {
                jsonMedium.addProperty("type", "Spirit");
                jsonMedium.addProperty("support", ((MediumSpirit) m).getSupport());
            }
            jsonListeTop3Medium.add(jsonMedium);
        }
        container.add("mediums", jsonListeTop3Medium);

        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
