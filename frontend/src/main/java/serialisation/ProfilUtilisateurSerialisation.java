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
        
        Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
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
            JsonObject profilAstral = new JsonObject();
            profilAstral.addProperty("signeChinois", c.getProfilAstral().getSigne_chinois());
            profilAstral.addProperty("signeZodiaque", c.getProfilAstral().getSigne_zodiac());
            profilAstral.addProperty("couleurBonheur", c.getProfilAstral().getCouleurPB());
            profilAstral.addProperty("animalTotem", c.getProfilAstral().getAnimal_totem());
            client.add("profilAstral", profilAstral);
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
            employe.addProperty("motDePasse", e.getMotDePasse());
            employe.addProperty("nbConsultations", e.getNb_consultations());
            
        }
        System.out.println("je suis la 111");
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
        
        if(conex && listeCons!=null){
            JsonArray jsonListeConsDemandee = new JsonArray();
            JsonArray jsonListeConsTerminee = new JsonArray();
            JsonArray jsonListeConsEnCours = new JsonArray();
            for(Consultation cons : listeCons){
                
                Client clicons = cons.getClient();
                Employe empcons = cons.getEmploye();
                Medium medcons = cons.getMedium();
                
                JsonObject jsonCons = new JsonObject(); //conteneur de la consultation
                
                JsonObject jsonConsCli = new JsonObject(); //conteneur avec le client de la consultation
                JsonObject jsonConsEmp = new JsonObject(); //conteneur avec l'employe de la consultation
                JsonObject jsonConsMed = new JsonObject(); //conteneur avec le medium de la consultation
                
                if(c==null){
                    //Infos du client de la consultation
                    jsonConsCli.addProperty("id", clicons.getId());
                    jsonConsCli.addProperty("nom", clicons.getNom());
                    jsonConsCli.addProperty("Prenom", clicons.getPrenom());
                    jsonConsCli.addProperty("mail", clicons.getMail());
                    jsonConsCli.addProperty("dateNaissance", clicons.getDate_naissance());
                    jsonConsCli.addProperty("genre", clicons.getGenre());
                    jsonConsCli.addProperty("adressePostale", clicons.getAdresse_postale());
                    jsonConsCli.addProperty("numTel", clicons.getNum_tel());
                    jsonCons.add("client", jsonConsCli );
                }
                
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
            container.add("consultations", containerCons);
        }
        
        
      
        
        System.out.println("fini la sérialisation");
        System.out.println(container);
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    } 
}
