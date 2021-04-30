/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

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
import metier.modele.Utilisateur;
import metier.service.Service;
import util.AstroNetApi;
import util.Message;

/**
 *
 * @author emilienmarion
 */
public class Main {

    
    public static void testerconnexion(){
         Service service=new Service();
         if(service.authentifierClient("lemail2@.fr", "mdp1")){
              System.out.println("connexion reussi");
         }else{
              System.out.println("connexion échoué");
         }
         
         }
         
         public static void testerInscrireClient(Client client) throws ParseException, IOException{
             
              Service service=new Service();
        long id =service.creerCompteClient(client);
        System.out.println(client.getProfilAstral());
         }
    
       public static void initBD(){
           Service service=new Service();
           service.initialiserBD();
       }
       
       
       public static Employe demandeConsultation(Medium ma,Client client){
            Service service=new Service();
           
        long id = service.creerMedium(ma);
        Employe emp=service.demanderconsultation(ma,client);
        if (emp==null){
             System.out.println("PAs d'employé disp");
        }
           System.out.println("l'employé choisi est "+emp);
                return emp;
       }
       
       
       public static void demarrerConsultation(Consultation consultation){
           
            Service service=new Service();
            service.demarrerConsultation(consultation);
           
       }
       
       
       public static void obtenirPrediction(Consultation consultation) throws IOException{
           
            Service service=new Service();
            service.obtenirPrédiction(consultation, 5, 1, 2);
           
       }
       
       
       public static void finConsultation(Consultation cons,String commentaire){
            Service service=new Service();
            service.finConsultation(cons, commentaire);
       }
       
       
       public static void testTop3Medium(){
           Service service=new Service();
          List<Medium> top3 = service.top3medium();
           for (Medium m : top3) {
            System.out.println(m);
        }
           
       }
       public static void obtenirMedium(){
           Service service=new Service();
          List<Medium> mediums = service.obtenirMedium();
           for (Medium m : mediums) {
            System.out.println(m);
        }
       }
       
       
        public static void obtenirEmp(){
           Service service=new Service();
          List<Employe> emps = service.obtenirEmploye();
           for (Employe e : emps) {
            System.out.println(e);
        }
       }
        
       public static Consultation testObtenirConsultation(Employe emp){
           Service ser = new Service();
           Consultation cons = ser.obtenirDemandeConsultation(emp);
           System.out.println(cons);
           return cons;
       }
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JpaUtil.init();
        try{
       Client client1=new Client("marion","léa","lemail@.fr","mdp1","20/08/2000","0782728262","F","26 rue des routes");
       Client client2=new Client("messi","lionel","lemail2@.fr","mdp2","19/03/1998","0782728262","H","26 rue des routes");
       
        
        MediumAstro ma = new MediumAstro("Serena", "H", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre\n" +
                                          "passé", "École Normale Supérieure d’Astrologie (ENS-Astro)", "2006","URLphoto");
        
       initBD();
           
       
       
            testerInscrireClient(client1);
            testerInscrireClient(client2);
            //testerconnexion();
        
         Employe empTest=demandeConsultation(ma,client2);
         System.out.println();
         
       Consultation consultationTest=testObtenirConsultation(empTest);
         
         demarrerConsultation(consultationTest);
         //demandeConsultation(ma,client1);
         
         
         obtenirPrediction(consultationTest);
         
         
        finConsultation(consultationTest, "ca s'est trés bien passé");
  
         // méthodes pour tester l'affichage de statistique
        testTop3Medium();
        obtenirMedium();
       obtenirEmp();
        
        
       
        
       } catch (Exception ex) {
       System.out.println("erreur main");
       }
         JpaUtil.destroy();
        // TODO code application logic here
    }
    
}
