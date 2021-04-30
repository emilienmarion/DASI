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
                "Institut des Nouveaux Savoirs Astrologiques", "2010");

        MediumCarto mc = new MediumCarto("Mme Irma", "F", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        MediumCarto mc1 = new MediumCarto("Endora", "F", "Mes cartes répondront à toutes vos questions personnelles.");

        MediumSpirit ms = new MediumSpirit("Professeur Tran", "H", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.",
                "Boule de cristal");
        MediumSpirit ms1 = new MediumSpirit("Gwenaëlle", "F", ": Votre avenir est devant vous : regardons-le ensemble !",
                "Marc de café, boule de cristal, oreilles de lapin");

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
//envoyer un sms à l’employé choisi
        Employe emp = null;
        try {

            JpaUtil.creerContextePersistance();
            //Trouver un employe qui correspond
            emp = utilisateurDAO.matchMedium(medium);
            
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderconsultation(Medium medium)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
            if (emp == null) {
                System.out.println("pas de medium de dispo");
            } else {
                Message.envoyerSmsEmp(medium, emp, client);
                Date maintenant = new Date();
                Consultation consultation = new Consultation(maintenant, client, emp, medium);
                long idConsult = creerConsultation(consultation);
                client.addConsultations(consultation);
                medium.addConsultations(consultation);
                emp.addConsultations(consultation);
                mergeUser(emp);
                mergeUser(client);
                mergeMedium(medium);

            }

        }

        return emp;
    }

    public void mergeUser(Utilisateur user) {
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            utilisateurDAO.modify(user);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service merge User", ex);

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    public void mergeMedium(Medium medium) {
        JpaUtil.creerContextePersistance();
        MediumDAO mediumDAO = new MediumDAO();
        try {
            JpaUtil.ouvrirTransaction();
            mediumDAO.modify(medium);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service merge Medium", ex);

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    public void mergeConsultation(Consultation consultation) {
        JpaUtil.creerContextePersistance();
        ConsultationDAO consultationDAO = new ConsultationDAO();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDAO.modify(consultation);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service merge Consultation", ex);

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    //déclancher au momoment ou l'employé indique qu'il est prêt
    //mettre consultation en attribut
    public void demarrerConsultation(Employe emp) {
        Consultation consultation = emp.getConsultations().get(emp.getConsultations().size() - 1);
        Message.envoyerSmsClient(consultation);
        emp.setStatut_en_ligne(true);
        Date maintenant = new Date();
        consultation.setDateDeb(maintenant);
        mergeConsultation(consultation);
        mergeUser(emp);
    }

    // redirire l'employé vers sa page de cpnsultation
    public long creerConsultation(Consultation consultation) {
        ConsultationDAO consultationDAO = new ConsultationDAO();
        Long resultat = null;
        JpaUtil.creerContextePersistance();

        try {
            JpaUtil.ouvrirTransaction();
            consultationDAO.createConsultation(consultation);

            JpaUtil.validerTransaction();
            resultat = consultation.getId();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service créerConsultation", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return resultat;

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
    public void finConsultation(Employe emp, String commentaire) {
        Consultation consultation = emp.getConsultations().get(emp.getConsultations().size() - 1);
        emp.setNb_consultations(emp.getNb_consultations() + 1);
        Medium medium = consultation.getMedium();
        medium.setNbConsultation(medium.getNbConsultation() + 1);
        consultation.setCommentaire(commentaire);
        emp.setStatut_en_ligne(false);
        Date maintenant = new Date();
        consultation.setDateFin(maintenant);
        mergeConsultation(consultation);
        mergeUser(emp);
        mergeMedium(medium);

    }
    //se déclanche quand l'employé est en ligne et quand il demande des prédiction pour son client

    public void obtenirPrédiction(Employe emp, int niveauAmour, int niveauSante, int niveauTravail) throws IOException {
        Client client1 = emp.getConsultations().get(emp.getConsultations().size() - 1).getClient();
        AstroNetApi astroApi = new AstroNetApi();
        List<String> predictions = astroApi.getPredictions(client1.getProfilAstral().getCouleurPB(), client1.getProfilAstral().getAnimal_totem(), niveauAmour, niveauSante, niveauTravail);

        System.out.println("");
        System.out.println("~<[ Prédictions ]>~");
        System.out.println("[ Amour ] " + predictions.get(0));
        System.out.println("[ Santé ] " + predictions.get(1));
        System.out.println("[Travail] " + predictions.get(2));
        System.out.println("");

    }

    public void obtenirStat() {
        //Nb de consultation par médium

        //juste un select Medium et apres on extrait le nb consultation
        //Nb de clients par employé
        //juste extraire le nombre de consultation de chaque employé apres un select* emp
        //Top 3 medium  
        MediumDAO mediumDAO = new MediumDAO();
        JpaUtil.creerContextePersistance();

        List<Medium> topMedium = mediumDAO.top3Medium();
        for (Medium m : topMedium) {
            System.out.println(m);
        }
        JpaUtil.fermerContextePersistance();

    }

}
