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
import metier.modele.Consultation;


/**
 *
 * @author taha
 * @deprecated
 */
public class ConsultationSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Consultation> listeCons =   (List<Consultation>) request.getAttribute("consultation");
        boolean conex = (boolean) request.getAttribute("connexion");
        
        JsonObject container = new JsonObject();
        if(!conex){
            container.addProperty("connexion", conex);
        }else{
            container.addProperty("connexion", conex);
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
            container.add("consDemandee", jsonListeConsDemandee);
            container.add("consEnCours", jsonListeConsEnCours);
            container.add("consTerminee", jsonListeConsTerminee);
        }
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
