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
import java.text.DateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
         boolean client = (boolean) request.getAttribute("clientemp");
         
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
                    DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                        DateFormat.SHORT,
                        DateFormat.SHORT);
                    long diffInMillies = Math.abs(cons.getDateFin().getTime() - cons.getDateDeb().getTime());
                    long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    //On mets tout dans la consultation
                    jsonCons.addProperty("id", cons.getId() );
                    jsonCons.add("medium", jsonConsMed );
                    jsonCons.addProperty("DateDemande", shortDateFormat.format(cons.getDemandeConsult()).toString() );
                    jsonCons.addProperty("DateDebut", shortDateFormat.format(cons.getDateDeb()).toString());
                    jsonCons.addProperty("DateFin", shortDateFormat.format(cons.getDateFin()).toString());
                    jsonCons.addProperty("Duree", diff);
                     if(!client){
                        jsonCons.addProperty("commentaire", cons.getCommentaire());
                    }
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
