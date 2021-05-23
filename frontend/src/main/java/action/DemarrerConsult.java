/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.*;
import metier.service.Service;

/**
 *
 * @author emilienmarion
 */
public class DemarrerConsult extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        Service ser = new Service();

        HttpSession session = request.getSession();
        Long idConsult = (Long) session.getAttribute("idConsult");
        Consultation consultation = ser.chercherConsultParID(idConsult);
        boolean sucess = ser.demarrerConsultation(consultation);

        request.setAttribute("succes", sucess);

    }

}
