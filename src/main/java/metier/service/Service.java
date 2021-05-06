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
import javax.persistence.OptimisticLockException;
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
 * Classe qui gere la couche service, API a travers laquelle le fornt end va recueillir toutes les donnees necessaires
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
public class Service {

    /**
     * objet qui s'occupe de la partie DAO
     */
    UtilisateurDao utilisateurDAO = new UtilisateurDao();

    /**
     * methode qui cree un nouveau compte client. Elle s'occupe de calculer le profil astral de ce client 
     * qui sera garde en base de donnes ainsi que de lui envoyer un mail pour confirmer la creation du compte.
     * Le profil astral est genere par astroApi.
     * @param nom nom du client
     * @param prenom prenom du client
     * @param mail adresse email du client
     * @param motDePasse mot de passe pour le logind du client
     * @param date_naissance date de naissance du client, au format dd/mm/yyyy
     * @param num_tel numero de telephone du client
     * @param genre genre du client
     * @param adresse_postale adresse postale du client
     * @return l'objet client apres l'avoir rajoute en base de donnees
     * @throws ParseException
     * @throws IOException
     */
    public Client creerCompteClient(String nom, String prenom, String mail, String motDePasse, String date_naissance, String num_tel, String genre, String adresse_postale) throws ParseException, IOException {// mettre en argument direct les strings de chaque champs et creer le client ici
        Client resultat = null;
        Client client = new Client(nom, prenom, mail, motDePasse, date_naissance, num_tel, genre, adresse_postale);
        resultat = client;
        JpaUtil.creerContextePersistance();
        //faire le calcul du profil astral ici et modifeier le clientt et apres le persister
        ProfilAstral profilAstral = AstroNetApi.obtenirProfilAstral(client);
        client.setProfilAstral(profilAstral);

        try {
            JpaUtil.ouvrirTransaction();
            utilisateurDAO.createUser(client);
            JpaUtil.validerTransaction();
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

    /**
     * methode qui authentifie un client pour qu'il puisse se conecter a son espace client
     * @param mail adresse mail du client qui souhaite se connecter
     * @param mdp mot de passe du client
     * @return true si succes, false sinon
     */
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

    /**
     * methode qui initialise la base de donnes en ajoutant des mediums et des employes lors du lancement de 
     * l'application
     */
    public void initialiserBD() {
        MediumDAO mediumDAO = new MediumDAO();
        // MediumAstro ma = new MediumAstro("Irma", "F", "un medium astro", "INSA", "35");

        MediumAstro ma = new MediumAstro("Serena", "H", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre\n"
                + "passé", "École Normale Supérieure d’Astrologie (ENS-Astro)", "2006", "URLphoto");

        MediumAstro ma1 = new MediumAstro("Mr M", "H", " Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!",
                "Institut des Nouveaux Savoirs Astrologiques", "2010", "URLphoto");

        MediumCarto mc = new MediumCarto("Mme Irma", "F", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.", "URLphoto");
        MediumCarto mc1 = new MediumCarto("Endora", "F", "Mes cartes répondront à toutes vos questions personnelles.", "URLphoto");

        MediumSpirit ms = new MediumSpirit("Professeur Tran", "H", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.",
                "Boule de cristal", "URLphoto");
        MediumSpirit ms1 = new MediumSpirit("Gwenaëlle", "F", ": Votre avenir est devant vous : regardons-le ensemble !",
                "Marc de café, boule de cristal, oreilles de lapin", "URLphoto");

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

        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            mediumDAO.createMedium(ma);
            mediumDAO.createMedium(ma1);
            mediumDAO.createMedium(mc1);
            mediumDAO.createMedium(ms);
            mediumDAO.createMedium(ms1);
            utilisateurDAO.createUser(emp1);
            utilisateurDAO.createUser(emp2);
            utilisateurDAO.createUser(e3);
            utilisateurDAO.createUser(e4);
            utilisateurDAO.createUser(e5);

            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initBD()", ex);

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    /**
     * service qui ajoute un medium a la base de donnees
     * @param medium medium ajouter
     * @return identifiant du medium apres l'ajout
     */
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

    /**
     * service qui ajoute un employe en base de donnees
     * @param emp employe a ajouter
     * @return id de l'employe apres l'ajout
     */
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
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service créer employee)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return resultat;

    }

    /**
     * sercvice qui gere les consultations. Apres que le client formule sa demande de consultation avec un medium
     * specifique, cette methode enregistre la date de demande et propose un employe en ligne et du meme genre que le medium
     * de prendre la consultation. Le service prendra l'employe qui reponds a ces deux criteres et qui a le moins de 
     * consultations accomplies. Ensuite, un SMS est envoye a l'employe pour lui notifier qu'il a une 
     * nouvelle demande de consultation. La methode gere aussi les eventuels conflits de concurrence entre deux clients qui 
     * souhaitent consulter le meme medium et donc le meme employe est choisi. Dans ce cas la, le deuxiemme employe 
     * avec le moins de consultations sera choisi pour l'autre client. Le service rajoute aussi la consultation a 
     * l'historique du client, de l'employe et du medium. Le service mets a jour le statut en ligne de l'employe choisi.
     * La date de demande est enregistre dans la consultation.
     * @param medium medium souhaite par le client pour la consultation
     * @param client client qui passe la demande
     * @return l'employe qui devra s'occuper de la consultation
     */
    public Employe demanderconsultation(Medium medium, Client client) {
        //match entre le medium demandé par le client et un employé( pas en  ligne avec un autre client, genre medium=genre emp, et prend l’employe qui rempli ces critère qui a le moins de consultation)
        //envoyer un sms à l’employé choisi
        Employe emp = null;
        List<Employe> emps;
        ConsultationDAO consultationDAO = new ConsultationDAO();
        MediumDAO mediumDAO = new MediumDAO();
        int i = 0;
        int listlen = 1;
        boolean sucess = false;
        while (i < listlen && !sucess)
            try {
            JpaUtil.creerContextePersistance();
            emps = utilisateurDAO.matchMedium(medium);//Trouver un employe qui correspond
            listlen = emps.size();
            if (!emps.isEmpty()) {
                JpaUtil.ouvrirTransaction();
                Date maintenant = new Date();
                Consultation consultation = new Consultation(maintenant, client, emps.get(i), medium);
                consultationDAO.createConsultation(consultation);
                client.addConsultations(consultation);
                medium.addConsultations(consultation);
                emps.get(i).addConsultations(consultation);
                emps.get(i).setStatut_en_ligne(true);
                utilisateurDAO.modify(client);
                utilisateurDAO.modify(emps.get(i));
                mediumDAO.modify(medium);
                JpaUtil.validerTransaction();
                Message.envoyerSmsEmp(medium, emps.get(i), client);
                emp = emps.get(i);
            }
            sucess = true;
        } catch (OptimisticLockException ex) {
            JpaUtil.annulerTransaction();
            i++;
            sucess = false;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderconsultation(Medium medium)", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return emp;
    }

    /**
     * ce service gere le demarage de la consultation. Une fois que l'employe declare qu'il est pret pour demarer 
     * la seance, le service mets a jout la date de debut de la consultation, ensuite envoie un sms au client
     * pour lui dire que le medium est pret et qu'il sera appelle bientot.
     * @param consultation
     */
    public void demarrerConsultation(Consultation consultation) {
        ConsultationDAO consultationDAO = new ConsultationDAO();
        Date maintenant = new Date();
        consultation.setDateDeb(maintenant);
        JpaUtil.creerContextePersistance();
        try {

            JpaUtil.ouvrirTransaction();
            consultationDAO.modify(consultation);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service DémarerConsultation", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
            Message.envoyerSmsClient(consultation);
        }

    }

    /**
     * ce service gere la fin de la consultation. Une fois que l'employe delclare que la consultation est finie,
     * le service mets a jour la date de fin de la consultation. Mais aussi, l'employe doit saisir un commentaire
     * sur la seance qui sera aussi rajoute a la consultation qui viens de finir. Le service rajoute aussi la 
     * consultation au nombre de consultations accomplies par l'employe et par le medium.
     * L'employe est finalement declare disponible pour des consultations futures
     * @param consultation
     * @param commentaire
     */
    public void finConsultation(Consultation consultation, String commentaire) {
        JpaUtil.creerContextePersistance();
        ConsultationDAO consultationDAO = new ConsultationDAO();

        MediumDAO mediumDAO = new MediumDAO();
        Employe emp = consultation.getEmploye();
        emp.setNb_consultations(emp.getNb_consultations() + 1);
        Medium medium = consultation.getMedium();
        medium.setNbConsultation(medium.getNbConsultation() + 1);
        consultation.setCommentaire(commentaire);
        emp.setStatut_en_ligne(false);
        Date maintenant = new Date();
        consultation.setDateFin(maintenant);

        try {
            JpaUtil.ouvrirTransaction();
            consultationDAO.modify(consultation);
            utilisateurDAO.modify(emp);
            mediumDAO.modify(medium);
            JpaUtil.validerTransaction();

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service créerConsultation", ex);

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }
    
    /**
     * ce service ce charge de fournir des predictions a l'employe lorsqu'il les demande pendant qu'il est dans
     * une consultations. Les predictions sont fournies par astroApi.
     * @param consultation la consultation a laquelle l'employe participe
     * @param niveauAmour note sur 5 pour le niveau d'amour
     * @param niveauSante note sur 5 pour le niveau de sante
     * @param niveauTravail note sur 5 pour le niveau de travail
     * @throws IOException
     */
    public void obtenirPrédiction(Consultation consultation, int niveauAmour, int niveauSante, int niveauTravail) throws IOException {

        Client client1 = consultation.getClient();
        AstroNetApi astroApi = new AstroNetApi();
        List<String> predictions = astroApi.getPredictions(client1.getProfilAstral().getCouleurPB(), client1.getProfilAstral().getAnimal_totem(), niveauAmour, niveauSante, niveauTravail);

        System.out.println("");
        System.out.println("~<[ Prédictions ]>~");
        System.out.println("[ Amour ] " + predictions.get(0));
        System.out.println("[ Santé ] " + predictions.get(1));
        System.out.println("[Travail] " + predictions.get(2));
        System.out.println("");

    }

    /**
     * service qui gere les statistiques sur les mediums. Elle renvoie une liste des trois mediums les plus
     * consultes
     * @return liste des trois mediums les plus consultes
     */
    public List<Medium> top3medium() {

        MediumDAO mediumDAO = new MediumDAO();
        JpaUtil.creerContextePersistance();

        List<Medium> result = mediumDAO.obtenirMedium();
        List<Medium> topMedium = new ArrayList<Medium>();

        for (int i = 0; i < 3; i++) {
            topMedium.add(result.get(i));
        }
        JpaUtil.fermerContextePersistance();
        return topMedium;
    }

    /**
     * Methode qui renvoie une liste de mediums triees en fonction de leur nombre de leurs
     * consultations par ordre decroissant, utilie nottament lors des statistiques
     * @return liste des mediums triee en fonction de leur nombre de consultations par ordre decroissant 
     */
    public List<Medium> obtenirMedium() {
        MediumDAO mediumDAO = new MediumDAO();
        JpaUtil.creerContextePersistance();

        List<Medium> result = mediumDAO.obtenirMedium();

        JpaUtil.fermerContextePersistance();
        return result;
    }

    /**
     * Methode qui renvoie tous les employes en base de donnes triees en fonction de leur nombre de consultations
     * par ordre decroissant, utilie notament lors de l'affichage des statistiques
     * @return liste triee des employes en fonctoin de leur nombre de consultations par ordre decroissant
     */
    public List<Employe> obtenirEmploye() {

        JpaUtil.creerContextePersistance();

        List<Employe> result = utilisateurDAO.obtenirEmploye();

        JpaUtil.fermerContextePersistance();
        return result;
    }

    /**
     * Cette methode se charge d'obtenir une consultation en cours pour un employe donne, pour qu'il puisse la
     * reprendre par la suite
     * @param emp employe qui doit prendre la consultation
     * @return la consultation qui a ete demande par le clien mais n'as pas encore ete traite
     */
    public Consultation obtenirDemandeConsultation(Employe emp) {
        JpaUtil.creerContextePersistance();
        ConsultationDAO consdao = new ConsultationDAO();
        Consultation c = consdao.obtenirConsultationEmp(emp);
        JpaUtil.fermerContextePersistance();
        return c;
    }

    /**
     * Retrouve un medium en base de donnes grace a sa denomination
     * @param denomination denomination du medium a retrouver
     * @return objet medium qui a la denomination passe en parametre
     */
    public Medium chercherMedium(String denomination) {
        MediumDAO mediumDAO = new MediumDAO();
        Medium m;
        JpaUtil.creerContextePersistance();
        try {
            m = mediumDAO.chercherMediumParDenomination(denomination);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service cherhcerMedium", ex);
            m = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return m;
    }


    /**
     * Methode qui retrouve un client a partir de son nom et prenom
     * @param userNom nom de l'utilisateur a retrouver
     * @param userPrenom prenom de l'utilisateur a retrouver
     * @return utilisateur qui a le nom et prenom passes en parametre, null si un tel utilisateur n'existe pas
     */
    public Client chercherClient(String userNom, String userPrenom) {
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

    /**
     * service qui renvoie l'historique des toutes le consultations d'un client
     * @param client client pour lequel on souhaite connaitre l'historique des consultations
     * @return liste qui contiens les consultaions du client passe en parametre
     */
    public List<Consultation> obtenirHistoriqueClient(Client client) {
        return client.getConsultations();
    }

    /**
     * service qui renvoie le profil astral d'un client donnes
     * @param client client pour lequel on souhaite connaitre son profil astral
     * @return profil astral du client en parametre
     */
    public ProfilAstral obtenirProfilAstral(Client client) {
        return client.getProfilAstral();
    }

    /**
     * service qui renvoie une liste avec tous les mediums cartomenciens en base de donnees
     * @return liste qui contiens tous les mediums cartomenciens en base de donees
     */
    public List<MediumCarto> listeMediumCarto() {
        MediumDAO mediumDAO = new MediumDAO();
        List<MediumCarto> listeCarto;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            listeCarto = mediumDAO.obtenirCarto();
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listeMediumCarto", ex);
            listeCarto = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeCarto;
    }

    /**
     * service qui renvoie une liste avec tous les mediums astrologues en base de donnees
     * @return liste qui contiens tous les mediums astrologues en base de donees
     */
    public List<MediumAstro> listeMediumAstro() {
        MediumDAO mediumDAO = new MediumDAO();
        List<MediumAstro> listeAstro;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            listeAstro = mediumDAO.obtenirAstro();
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listeMediumAstro", ex);
            listeAstro = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeAstro;
    }

    /**
     * service qui renvoie une liste avec tous les mediums spirits en base de donnees
     * @return liste qui contiens tous les mediums spirits en base de donees
     */
    public List<MediumSpirit> listeMediumSpirit() {
        MediumDAO mediumDAO = new MediumDAO();
        List<MediumSpirit> listeSpirit;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            listeSpirit = mediumDAO.obtenirSpirit();
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listeMediumSpirit", ex);
            listeSpirit = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeSpirit;
    }

    /**
     * service qui reinitialise le mot de passe d'un client
     * @param nom nom du client
     * @param prenom prenom du client
     * @param mail adresse email du client
     * @param date_naissance date de naissance du client au format dd/mm/yyyy
     * @param num_tel numero de telephone du client
     * @param newmdp nouveau mot de passe
     * @param confNewMdp confiramtion du nouveau mot de passe
     * @return true si la reinitialisatoin a su succes, false sinon
     */
    public boolean renitialiserMdp(String nom,String prenom,  String mail,  String date_naissance, String num_tel,String newmdp, String confNewMdp){
        Client client=chercherClient(nom, prenom);
        boolean bool=false;
        if(client!=null && client.getMail()==mail && client.getDate_naissance()== date_naissance && client.getNum_tel()==num_tel && newmdp==confNewMdp){
            client.setMotDePasse(newmdp);
            bool=true;
        }
      
        return bool;
    } 
}
