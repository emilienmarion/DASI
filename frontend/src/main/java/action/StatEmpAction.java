/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.modele.*;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class StatEmpAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        
          System.out.println("je suis dans l'action stat Medium");
        Service ser = new Service();
        List<Employe> emps= ser.obtenirEmploye();
        boolean succes= true;
        
        if (emps==null){
            succes=false;
        }
         request.setAttribute("sucess", succes);
         request.setAttribute("listemps",emps);
    }
        
        
    }
    

