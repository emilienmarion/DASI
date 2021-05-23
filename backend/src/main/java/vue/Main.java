package vue;

import dao.ConsultationDAO;
import dao.JpaUtil;
import dao.UtilisateurDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.MediumAstro;
import metier.modele.MediumCarto;
import metier.modele.MediumSpirit;
import metier.modele.ProfilAstral;
import metier.modele.Utilisateur;
import metier.service.Service;
import util.AstroNetApi;
import util.Message;

/**
 * Classe main utilise pour tester nos services
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
public class Main {

    public static void testerconnexion() {
        Service service = new Service();
        if (service.authentifierClient("lemail@.fr", "mdp1")!=null) {
            System.out.println("connexion reussi");
        } else {
            System.out.println("connexion échoué");
        }
    }
    public static Client testerInscrireClient(String nom, String prenom, String mail, String motDePasse, String date_naissance, String num_tel, String genre, String adresse_postale) throws ParseException, IOException {

        Service service = new Service();
        Client client = service.creerCompteClient(nom, prenom, mail, motDePasse, date_naissance, num_tel, genre, adresse_postale);
        // System.out.println(client.getProfilAstral());
        return client;
    }

    public static void initBD() {
        Service service = new Service();
        service.initialiserBD();
    }

    public static Employe demandeConsultation(Medium ma, Client client) {
        Service service = new Service();

        Employe emp = service.demanderconsultation(ma, client);
        if (emp == null) {
            System.out.println("PAs d'employé disp");
        }
        System.out.println("l'employé choisi est " + emp);
        return emp;
    }

    public static void demarrerConsultation(Consultation consultation) {

        Service service = new Service();
        service.demarrerConsultation(consultation);

    }

    public static void obtenirPrediction(Consultation consultation) throws IOException {

        Service service = new Service();
        service.obtenirPrédiction(consultation, 5, 1, 2);

    }

    public static void finConsultation(Consultation cons, String commentaire) {
        Service service = new Service();
        service.finConsultation(cons, commentaire);
    }

    public static void testTop3Medium() {
        Service service = new Service();
        List<Medium> top3 = service.top3medium();
        System.out.println(" top 3:  " );
        for (Medium m : top3) {
            System.out.println(m);
        }

    }

    public static void obtenirMedium() {
        Service service = new Service();
        List<Medium> mediums = service.obtenirMedium();
        for (Medium m : mediums) {
            System.out.println(m);
        }
    }

    public static void obtenirEmp() {
        Service service = new Service();
        List<Employe> emps = service.obtenirEmploye();
        for (Employe e : emps) {
            System.out.println(e);
        }
    }

    public static Consultation testObtenirDemandeConsultation(Employe emp) {
        Service ser = new Service();
        Consultation cons = ser.obtenirDemandeConsultation(emp);
        if (cons == null) {
            System.out.println("l'employé n'a pas de demande de consultations");
        } else {
            System.out.println("l'employé a la demande de consultation suivante :  " + cons);
        }
        return cons;
    }

    public static void obtenirProfilAstral(Client client) {
        Service ser = new Service();
        ProfilAstral profAS = ser.obtenirProfilAstral(client);
        System.out.println("le profil astral de ce client est: " + profAS);

    }

    public static Medium chercherMedium(String denomination) {
        Service service = new Service();
        Medium m = service.chercherMedium(denomination);
        if (m == null) {
            System.out.println("Medium introuvable");

        } else {
            System.out.println("Medium trouvé");
            System.out.println(m.getDenomination());
        }
        return m;
    }

    public static void chercherClient(String userNom, String userPrenom) {
        Service service = new Service();
        Client c = service.chercherClient(userNom, userPrenom);
        if (c != null) {
            System.out.println("Client trouvé");
            System.out.println(c.getNom());
        } else {
            System.out.println("Client introuvable");
        }
    }

    public static void afficherMediumParType() {
        Service service = new Service();
        List<MediumAstro> listeAstro;
        listeAstro = service.listeMediumAstro();
        System.out.println();
        System.out.println("Liste des Astro:");
        for (MediumAstro astro : listeAstro) {
            System.out.println(astro.getDenomination());
        }
        System.out.println();

        List<MediumCarto> listeCarto;
        listeCarto = service.listeMediumCarto();
        System.out.println();
        System.out.println("Liste des Carto");
        for (MediumCarto carto : listeCarto) {
            System.out.println(carto.getDenomination());
        }
        System.out.println();

        List<MediumSpirit> listeSpirit;
        listeSpirit = service.listeMediumSpirit();
        System.out.println();
        System.out.println("Liste des Spirit");
        for (MediumSpirit spirit : listeSpirit) {
            System.out.println(spirit.getDenomination());
        }
        System.out.println();
    }
    
    public static void testerRenimdp(){
         Service service = new Service();
         boolean bool=service.reinitialiserMdp("marion", "léa", "lemail@.fr", "20/08/2000", "0782728262", "newmdp", "newmdp");
         System.out.println("le mdp reni"+bool);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JpaUtil.init();
        try {
            
            Service ser = new Service();
            /*
             //MediumAstro ma = new MediumAstro("Serena", "F", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre\n"
              // + "passé", "École Normale Supérieure d’Astrologie (ENS-Astro)", "2006", "URLphoto");
            //teste le service qui permet l'inscription de clients dans la base
            Client client1 = testerInscrireClient("marion", "léa", "lemail@.fr", "mdp1", "20/08/2000", "0782728262", "F", "26 rue des routes");
            Client client2 = testerInscrireClient("messi", "lionel", "lemail2@.fr", "mdp2", "19/03/1998", "0782728262", "H", "26 rue des routes");
//initialise notre base de données avec quelques employés et quelques médiums
            initBD();

            System.out.println();
            //permet de tester les services qui permettent d'obtenir les medium inscirt dans la base par catégories
           afficherMediumParType();

//teste le service de connexion avec un mail et un mdp choisi dans cette méthode
            testerconnexion();
            //test le service qui permet l'obtention du Profil astral d'un client ( Pour pouvoir l'afficher sur le tableau de bord du client)
            obtenirProfilAstral(client2);

            //méthode de test du service qui cherche un médium par sa dénomination
            Medium ma = chercherMedium("Endora");

            //méthode de test du service qui cherche un client par son nom et prénom
            System.out.println();
            chercherClient("marion", "léa");
            System.out.println();
            long id=2;
            

           // Client client2= (Client) ser.chercherParId(id);
            
            
            //Méthode de test qui simule la demande de consultation par un client qui choist un médium, cette méthode renvoie l'employé chosit pour la consultation
            Employe empTest = demandeConsultation(ma, client2);
            System.out.println();

            
           
            
//test le service qui permet de récupérer la consultation pour lequelle est demandé un employé, renvoi null si il n'a aucune demande de consultation
            Consultation consultationTest = testObtenirDemandeConsultation(empTest);
            System.out.println();

            //service qui permet à l'employé de démarrer la consultation quand il est prêt
            demarrerConsultation(consultationTest);

            System.out.println();
            //demandeConsultation(ma,client1);
            System.out.println();

            //service utilisable par l'employé pendant une consultation pour obtenir des prédictions sur son client
            obtenirPrediction(consultationTest);

            System.out.println();

            //Service qui permet à l'employé de mettre fin à la consultation, de laisser un commentaire
            finConsultation(consultationTest, "commentaire de test :ca s'est trés bien passé");

            System.out.println();
            System.out.println();

            //Test de retour de ce service quand l'employé n'attend pas de consultation
            Consultation consultationTest2 = testObtenirDemandeConsultation(empTest);
            System.out.println();
            // méthodes pour tester l'affichage de statistique

            System.out.println();
            //retourne la liste des 3 médium les 3 populaire
            testTop3Medium();
            System.out.println();
            //retourne tout les médium trié par odre de popularité
            obtenirMedium();
            System.out.println();
            //retourne les employés trier par nombre de consultation
            obtenirEmp();
            System.out.println();
            
          // testerRenimdp();
            */
             System.out.println("mainnn");
           
        } catch (Exception ex) {
            System.out.println("erreur main");
        }
        JpaUtil.destroy();
        // TODO code application logic here
    }

}
