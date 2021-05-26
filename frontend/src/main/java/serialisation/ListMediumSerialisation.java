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
 * @author ithanvel
 */
public class ListMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        System.out.println("je suis dans liste medium serialisation"); 
        
        JsonObject container = new JsonObject();
        boolean succes = (boolean) request.getAttribute("sucess"); 
        container.addProperty("succes", succes);
        JsonArray top3 = new JsonArray();
        if(succes){
            List<Medium> mediums =  (List<Medium>) request.getAttribute("listmediums");
            for(int i = 0; i<mediums.size(); i++){
                
                JsonObject mymedium = new JsonObject();
                String medtype = "Spirit";
                
                if(mediums.get(i) instanceof MediumAstro){
                    medtype = "Astrologue";
                }else if(mediums.get(i) instanceof MediumCarto){
                    medtype = "Cartomencien";
                }
                
                mymedium.addProperty("denomination", mediums.get(i).getDenomination());
                mymedium.addProperty("type", medtype);
                mymedium.addProperty("nbConsultations", mediums.get(i).getNbConsultation());
                
                top3.add(mymedium);
            }
            container.add("top3",top3);
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
  "top3": [
    {
      "denomination": "Mr M",
      "type": "Astrologue",
      "nbConsultations": 8
    },
    {
      "denomination": "Endora",
      "type": "Cartomencien",
      "nbConsultations": 7
    },
    {
      "denomination": "Gwenaëlle",
      "type": "Spirit",
      "nbConsultations": 3
    }
  ]
}
*/