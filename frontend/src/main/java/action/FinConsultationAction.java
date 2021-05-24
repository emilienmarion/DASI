/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class FinConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Service ser = new Service();
         System.out.println("Je suis dans fin de consultation action");

        String com = request.getParameter("commentaire");

        HttpSession session = request.getSession();
        Long idConsult = (Long) session.getAttribute("idConsult");
        Consultation consultation = ser.chercherConsultParID(idConsult);
        boolean sucess;

        if (consultation == null) {
            sucess = false;
        } else {
            sucess = true;
            ser.finConsultation(consultation, com);

        }
         request.setAttribute("succes", sucess);
    }

    }
