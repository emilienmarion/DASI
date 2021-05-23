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
 * 
 */
public class HistoriqueConsultationSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<Consultation> listeCons =   (List<Consultation>) request.getAttribute("consultation");
        
        boolean succes = (boolean) request.getAttribute("connexion");
        JsonObject container = new JsonObject();
        JsonObject containerCons = new JsonObject();
        
        container.addProperty("succes", succes);
        if(succes && listeCons!=null){
            
            JsonArray jsonListeConsTerminee = new JsonArray();
            
            for(Consultation cons : listeCons){
                if (cons.getDateDeb()!=null && cons.getDateFin()!=null && cons.getDemandeConsult()!=null){

                    Medium medcons = cons.getMedium();

                    JsonObject jsonCons = new JsonObject(); //conteneur de la consultation

                    JsonObject jsonConsMed = new JsonObject(); //conteneur avec le medium de la consultation

                    //Infos du medium de la consultation
                    jsonConsMed.addProperty("id", medcons.getId());
                    jsonConsMed.addProperty("denomination", medcons.getDenomination());
                    jsonConsMed.addProperty("genre", medcons.getGenre());
                    jsonConsMed.addProperty("nbConsultation", medcons.getNbConsultation());
                    jsonConsMed.addProperty("photo", medcons.getPhoto());
                    jsonConsMed.addProperty("presentation", medcons.getPresentation());

                    if(medcons instanceof MediumAstro){
                        MediumAstro ma = (MediumAstro) medcons;
                        jsonConsMed.addProperty("type", "Astrologue");
                        jsonConsMed.addProperty("promotion", ma.getPromotion());
                        jsonConsMed.addProperty("formation", ma.getFormation());
                    }else if(medcons instanceof MediumCarto){
                        jsonConsMed.addProperty("type", "Cartomencien");
                    }else if(medcons instanceof MediumSpirit){
                        MediumSpirit ms = (MediumSpirit) medcons;
                        jsonConsMed.addProperty("type", "Spirit");
                        jsonConsMed.addProperty("support", ms.getSupport());
                    }

                    //On mets tout dans la consultation
                    jsonCons.addProperty("id", cons.getId() );
                    jsonCons.add("medium", jsonConsMed );
                    jsonCons.addProperty("DateDemande", cons.getDemandeConsult().toString() );
                    jsonCons.addProperty("DateDebut", cons.getDateDeb().toString());
                    jsonCons.addProperty("DateFin", cons.getDateFin().toString());
                    jsonListeConsTerminee.add(jsonCons);
                }
            }
            container.add("consultations", jsonListeConsTerminee);
        }
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
/**
 * Format de la reponse, faut remarquer que l'attrbut consultations est un tableau
 * {
 * "succes": true,
 * "consultations": [
 *   {
 *     "id": 1,
 *     "medium": {
 *       "id": 2,
 *       "denomination": "Serena",
 *       "genre": "H",
 *       "nbConsultation": 1,
 *       "photo": "URLphoto",
 *       "presentation": "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre\npassé",
 *      "type": "Astrologue",
 *       "promotion": "2006",
 *       "formation": "École Normale Supérieure d’Astrologie (ENS-Astro)"
 *     },
 *     "DateDemande": "Thu May 06 11:25:22 CEST 2021",
 *     "DateDebut": "Thu May 06 11:25:23 CEST 2021",
 *     "DateFin": "Thu May 06 11:25:23 CEST 2021"
 *   }
 * ]
 * }
 */
