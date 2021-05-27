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
import metier.modele.MediumAstro;
import metier.modele.MediumCarto;

/**
 *
 * @author emilienmarion
 */
public class ListeEmployeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        
 System.out.println("je suis dans liste emps serialisation"); 
        
        JsonObject container = new JsonObject();
        boolean succes = (boolean) request.getAttribute("sucess"); 
        container.addProperty("succes", succes);
        JsonArray listeEmps = new JsonArray();
        if(succes){
            List<Employe> emps =   (List<Employe>) request.getAttribute("listemps");
            for(int i = 0; i<emps.size(); i++){
                
                JsonObject myEmps = new JsonObject();
                
                
                myEmps.addProperty("prenom", emps.get(i).getPrenom());
                myEmps.addProperty("nom", emps.get(i).getNom());
                myEmps.addProperty("nbConsultations", emps.get(i).getNb_consultations());
                
                listeEmps.add(myEmps);
            }
            container.add("Employe",listeEmps);
        }
         PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}

/*
{
  "succes": true,
  "Employe": [
    {
      "prenom": "Renaud",
      "nom": "JULOON",
      "nbConsultations": 8
    },
    {
      "prenom": "Stéphanie",
      "nom": "SOUMOELLIN",
      "nbConsultations": 7
    },
    {
      "prenom": "EKIN",
      "nom": "Yachen",
      "nbConsultations": 6
    },
    {
      "prenom": "emile",
      "nom": "Zola",
      "nbConsultations": 3
    },
    {
      "prenom": "Luffy",
      "nom": "monkey D",
      "nbConsultations": 1
    }
  ]
}*/