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
public class RefreshDemandeconsultSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Consultation cons =   (Consultation) request.getAttribute("Demandeconsultation");
        boolean succes = (boolean) request.getAttribute("succes");
        
        JsonObject container = new JsonObject();
        JsonObject containerCons = new JsonObject();
        
        container.addProperty("succes", succes);
        
        if(succes && cons!=null){
            JsonObject jsonConsMed = new JsonObject(); //conteneur avec le medium de la consultation
            JsonObject jsonConsCli = new JsonObject(); //conteneur avec le medium de la consultation
            
            //Infos du client -----------------------------------------------------
            Client c = (Client) cons.getClient();
            jsonConsCli.addProperty("id", c.getId());
            jsonConsCli.addProperty("nom", c.getNom());
            jsonConsCli.addProperty("Prenom", c.getPrenom());
            jsonConsCli.addProperty("mail", c.getMail());
            jsonConsCli.addProperty("dateNaissance", c.getDate_naissance());
            jsonConsCli.addProperty("genre", c.getGenre());
            jsonConsCli.addProperty("adressePostale", c.getAdresse_postale());
            jsonConsCli.addProperty("numTel", c.getNum_tel());
            //Profil astral du client
            JsonObject profilAstral = new JsonObject();
            profilAstral.addProperty("signeChinois", c.getProfilAstral().getSigne_chinois());
            profilAstral.addProperty("signeZodiaque", c.getProfilAstral().getSigne_zodiac());
            profilAstral.addProperty("couleurBonheur", c.getProfilAstral().getCouleurPB());
            profilAstral.addProperty("animalTotem", c.getProfilAstral().getAnimal_totem());
            jsonConsCli.add("profilAstral", profilAstral);
            
            //----------------------------------------------------------------------

            //Infos du medium de la consultation------------------------------------
            Medium medcons = cons.getMedium();
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
            //------------------------------------------------------------------------

            //On mets tout dans la consultation---------------------------------------
            container.addProperty("id", cons.getId() );
            container.add("medium", jsonConsMed);
            container.add("client", jsonConsCli);
            container.addProperty("DateDemande", cons.getDemandeConsult().toString() );
            //------------------------------------------------------------------------
        }
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
