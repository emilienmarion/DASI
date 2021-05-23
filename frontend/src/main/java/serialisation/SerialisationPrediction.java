/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialisation;

import com.google.gson.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.*;

/**
 *
 * @author emilienmarion
 */
public class SerialisationPrediction extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
         System.out.println("Je suis dans Predicion serialisation");
          List<String> predictions = (List<String>) request.getAttribute("prediction");
          boolean sucess=(boolean) request.getAttribute("sucess");
          JsonObject container = new JsonObject();
          container.addProperty("sucess", sucess);
          JsonObject Predictions = new JsonObject();
           Predictions.addProperty("amour",predictions.get(0));
           Predictions.addProperty("sante",predictions.get(1));
            Predictions.addProperty("travail",predictions.get(2));
           
            container.add("Predictions",Predictions);

            
            
        System.out.println("~<[ Prédictions ]>~");
        System.out.println("[ Amour ] " + predictions.get(0));
        System.out.println("[ Santé ] " + predictions.get(1));
        System.out.println("[Travail] " + predictions.get(2));


        
        
    }
    
}
