/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.ConsultationDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.UtilisateurDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.MediumAstro;
import metier.modele.MediumCarto;
import metier.modele.MediumSpirit;
import metier.modele.ProfilAstral;
import metier.modele.Utilisateur;
import util.AstroNetApi;
import util.Message;

/**
 *
 * @author emilienmarion
 */
public class Service {

    UtilisateurDao utilisateurDAO = new UtilisateurDao();

    public long creerCompteClient(Client client) throws ParseException, IOException {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        //faire le calcul du profil astral ici et modifeier le clinet et apres le persister
        ProfilAstral profilAstral = AstroNetApi.obtenirProfilAstral(client);
        client.setProfilAstral(profilAstral);

        try {
            JpaUtil.ouvrirTransaction();
            utilisateurDAO.createUser(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
            Message.envoyer_mail_success(client);
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Message.envoyer_mail_echec(client);
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service créercompteclient(client)", ex);

            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public boolean authentifierClient(String mail, String mdp) {

        JpaUtil.creerContextePersistance();
        boolean result = false;

        try {

            Utilisateur user = utilisateurDAO.cherchermail(mail);

            if (user != null) {

                if (user.getMotDePasse().equals(mdp)) {

                    result = true;
                }
            }
        } catch (Exception ex) {

            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,mdp)", ex);
            result = false;
        } finally {

            JpaUtil.fermerContextePersistance();

        }
        return result;

    }

    public void initialiserBD() {
        // MediumAstro ma = new MediumAstro("Irma", "F", "un medium astro", "INSA", "35");

        MediumAstro ma1 = new MediumAstro("Mr M", "H", " Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!",
                "Institut des Nouveaux Savoirs Astrologiques", "2010","URLphoto");

        MediumCarto mc = new MediumCarto("Mme Irma", "F", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.","URLphoto");
        MediumCarto mc1 = new MediumCarto("Endora", "F", "Mes cartes répondront à toutes vos questions personnelles.","URLphoto");

        MediumSpirit ms = new MediumSpirit("Professeur Tran", "H", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.",
                "Boule de cristal","URLphoto");
        MediumSpirit ms1 = new MediumSpirit("Gwenaëlle", "F", ": Votre avenir est devant vous : regardons-le ensemble !",
                "Marc de café, boule de cristal, oreilles de lapin","URLphoto");

        Employe emp1 = new Employe("Zola", "emile", "ezola@insa-lyon.fr ", "mdpEmp", "24/01/2000", "0781618187", "H");
        Employe emp2 = new Employe("monkey D", "Luffy", "monkeyDLuffy@insa-lyon.fr ", "mdpEmp2", "24/01/2001", "07815587", "H");

        Employe e3 = new Employe("JULOON", "Renaud", "rjuloon@gmail.com", "mdp1", "12/10/1996", "0340084975", "H");

        Employe e4 = new Employe("SOUMOELLIN", "Stéphanie", "ssoumoellin3265@yahoo.com", "azerty", "22/05/1983", "0239372281", "F");
        Employe e5 = new Employe("Yachen", "EKIN", "yekin6704@yahoo.com", "abc123", "16/11/1983", "0406224761", "H");

        emp1.setNb_consultations(3);
        emp2.setNb_consultations(1);
        e3.setNb_consultations(8);
        e4.setNb_consultations(0);
        e5.setNb_consultations(6);
        emp1.setStatut_en_ligne(true);
        e4.setStatut_en_ligne(true);
        //e5.setStatut_en_ligne(true);
        ma1.setNbConsultation(8);
        ms1.setNbConsultation(3);

        // long id = creerMedium(ma);
        long id = creerMedium(ma1);
        id = creerMedium(mc);
        id = creerMedium(mc1);
        id = creerMedium(ms);
        id = creerMedium(ms1);
        id = creerEmp(emp1);
        id = creerEmp(emp2);
        id = creerEmp(e3);
        id = creerEmp(e4);
        id = creerEmp(e5);

    }

    public long creerMedium(Medium medium) {
        MediumDAO mediumDAO = new MediumDAO();
        Long resultat = null;
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            mediumDAO.createMedium(medium);

            JpaUtil.validerTransaction();
            resultat = medium.getId();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service créerMedium)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return resultat;

    }

    public long creerEmp(Employe emp) {

        Long resultat = null;
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            utilisateurDAO.createUser(emp);

            JpaUtil.validerTransaction();
            resultat = emp.getId();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service créeremployee)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return resultat;

    }

    public Employe demanderconsultation(Medium medium, Client client) {
//match entre le medium demandé par le client et un employé( pas en  ligne avec un autre client, genre medium=genre emp, et prend l’employe qui rempli ces critère qui a le moins de consultation)
//envoyer un sms à l’employé chois
        Employe emp = null;
        List<Employe> emps;
         ConsultationDAO consultationDAO = new ConsultationDAO();
         MediumDAO mediumDAO = new MediumDAO();
        try {
            JpaUtil.creerContextePersistance();
            //Trouver un employe qui correspond
            emps = utilisateurDAO.matchMedium(medium);
             if (!emps.isEmpty() ) {
             JpaUtil.ouvrirTransaction();
             Date maintenant = new Date();
             Consultation consultation = new Consultation(maintenant, client, emps.get(0), medium);
            consultationDAO.createConsultation(consultation);
            client.addConsultations(consultation);
            medium.addConsultations(consultation);
            emps.get(0).addConsultations(consultation);
            emps.get(0).setStatut_en_ligne(true);
            utilisateurDAO.modify(client);
            utilisateurDAO.modify(emps.get(0));
            mediumDAO.modify(medium);
            
            JpaUtil.validerTransaction();
            Message.envoyerSmsEmp(medium, emps.get(0), client);
            emp=emps.get(0);
             }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderconsultation(Medium medium)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
           
          
        }

        return emp;
    }



    //déclencher au momoment ou l'employé indique qu'il est prêt
    //mettre consultation en attribut
    public void demarrerConsultation(Consultation consultation) {
         ConsultationDAO consultationDAO = new ConsultationDAO();
        
         Date maintenant = new Date();
        consultation.setDateDeb(maintenant);
        JpaUtil.creerContextePersistance();
        try {
            
        JpaUtil.ouvrirTransaction();
        consultationDAO.modify(consultation);
        JpaUtil.validerTransaction();
        
        }catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service DémarerConsultation", ex);
        }finally {
             JpaUtil.fermerContextePersistance();
            Message.envoyerSmsClient(consultation);
        }
        
     
        
    }

   
    public Utilisateur rechercherClientParId(Long id) {
        Utilisateur resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = utilisateurDAO.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    //ce service est déclanché aprés que l'employe ai clické sur fin consultation et a rempli le commenatire 
    //si le commentaire est vide -> PoP up d'erreur ou alors commentaire null
    public void finConsultation(Consultation consultation, String commentaire) {
        JpaUtil.creerContextePersistance();
         ConsultationDAO consultationDAO = new ConsultationDAO();
         
        MediumDAO mediumDAO = new MediumDAO();
        Employe emp=consultation.getEmploye();
        emp.setNb_consultations(emp.getNb_consultations() + 1);
        Medium medium = consultation.getMedium();
        medium.setNbConsultation(medium.getNbConsultation() + 1);
        consultation.setCommentaire(commentaire);
        emp.setStatut_en_ligne(false);
        Date maintenant = new Date();
        consultation.setDateFin(maintenant);
      
        
        try{
            JpaUtil.ouvrirTransaction();
            consultationDAO.modify(consultation);
            utilisateurDAO.modify(emp);
            mediumDAO.modify(medium);
            JpaUtil.validerTransaction();
            
        }  catch (Exception ex) {
             JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service créerConsultation", ex);
        
             } finally {
             JpaUtil.fermerContextePersistance();
              
        }
        

    }
    //se déclanche quand l'employé est en ligne et quand il demande des prédiction pour son client

    public void obtenirPrédiction(Consultation consultation, int niveauAmour, int niveauSante, int niveauTravail) throws IOException {
        
        Client client1=consultation.getClient();
        AstroNetApi astroApi = new AstroNetApi();
        List<String> predictions = astroApi.getPredictions(client1.getProfilAstral().getCouleurPB(), client1.getProfilAstral().getAnimal_totem(), niveauAmour, niveauSante, niveauTravail);

        System.out.println("");
        System.out.println("~<[ Prédictions ]>~");
        System.out.println("[ Amour ] " + predictions.get(0));
        System.out.println("[ Santé ] " + predictions.get(1));
        System.out.println("[Travail] " + predictions.get(2));
        System.out.println("");

    }

    public List<Medium> top3medium() {
        
        MediumDAO mediumDAO = new MediumDAO();
        JpaUtil.creerContextePersistance();

        List<Medium> result = mediumDAO.obtenirMedium();
        List<Medium> topMedium = new ArrayList<Medium>();
       
         for(int i=0;i<3;i++){
             topMedium.add(result.get(i));
         }
        JpaUtil.fermerContextePersistance();
        return topMedium;
    }

    
    public List<Medium> obtenirMedium() {
        MediumDAO mediumDAO = new MediumDAO();
        JpaUtil.creerContextePersistance();

        List<Medium> result = mediumDAO.obtenirMedium();
        
        JpaUtil.fermerContextePersistance();
        return result;
    }
    
    public List<Employe> obtenirEmploye() {
        
        JpaUtil.creerContextePersistance();
        

        List<Employe> result = utilisateurDAO.obtenirEmploye();
        
        JpaUtil.fermerContextePersistance();
        return result;
    }
    
    
    public Consultation obtenirDemandeConsultation(Employe emp){
        JpaUtil.creerContextePersistance();
        ConsultationDAO consdao = new ConsultationDAO();
        Consultation c = consdao.obtenirConsultationEmp(emp);
        JpaUtil.fermerContextePersistance();
        return c;
    }
    
    
    public Medium chercherMedium(String denomination){
        MediumDAO mediumDAO = new MediumDAO();
        Medium m;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            m = mediumDAO.chercherMediumParDenomination(denomination);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service cherhcerMedium", ex);
            m = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return m;
    }
    
    
    
    
    
     //Obtenir un client à partir de son nom et son prénom
    public Client chercherClient(String userNom, String userPrenom){
        UtilisateurDao userDAO = new UtilisateurDao();
        Client c;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            c = userDAO.chercherUserParDenomination(userNom, userPrenom);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service cherhcerClient", ex);
            c = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return c;
    }
    
}
