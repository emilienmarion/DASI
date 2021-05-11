package util;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Utilisateur;

/**
 *
 * @author DASI Team
 */
public class Message {
    
    private final static PrintStream OUT = System.out;
    private final static SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd~HH:mm:ss");
    private final static SimpleDateFormat HORODATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
    private final static String mailExpediteur="contact@predict.if";
    
    private static void debut() {
        Date maintenant = new Date();
        OUT.println();
        OUT.println();
        OUT.println("---<([ MESSAGE @ " + TIMESTAMP_FORMAT.format(maintenant) + " ])>---");
        OUT.println();
    }
    
    private static void fin() {
        OUT.println();
        OUT.println("---<([ FIN DU MESSAGE ])>---");
        OUT.println();
        OUT.println();
    }
    
    public static void envoyerMail( String mailDestinataire, String objet, String corps) {
        
        Date maintenant = new Date();
        Message.debut();
        OUT.println("~~~ E-mail envoyé le " + HORODATE_FORMAT.format(maintenant) + " ~~~");
        OUT.println("De : " + mailExpediteur);
        OUT.println("À  : " + mailDestinataire);
        OUT.println("Obj: " + objet);
        OUT.println();
        OUT.println(corps);
        Message.fin();
    }

    public static void envoyerSmsEmp(Medium medium, Employe emp,Client client) {
        
        Date maintenant = new Date();
        Message.debut();
        OUT.println("~~~ Notification envoyée le " + HORODATE_FORMAT.format(maintenant) + " ~~~");
        OUT.println("Pour  : " + trouverCivilite(emp)+ emp.getPrenom()+" "+emp.getNom());
        OUT.println("TEL  : " + emp.getNum_tel());
        OUT.println();
        OUT.println( "Bonjour "+emp.getPrenom()+",consultation requise pour "+client.getPrenom()+" "+client.getNom());
        OUT.println();
        OUT.println( "Medium à incarner: "+medium.getDenomination());
        Message.fin();
    }
    public static void envoyerSmsClient(Consultation consultation) {
        
        Date maintenant = new Date();
        Message.debut();
        OUT.println("~~~ Notification envoyée le " + HORODATE_FORMAT.format(maintenant) + " ~~~");
        OUT.println("Pour  : " + trouverCivilite(consultation.getClient()) + consultation.getClient().getPrenom()+" "+consultation.getClient().getNom());
        OUT.println("TEL  : " + consultation.getClient().getNum_tel());
        OUT.println();
        OUT.println( "Bonjour "+consultation.getClient().getPrenom()+", j’ai bien reçu votre demande de consultation du "+HORODATE_FORMAT.format(consultation.getDemandeConsult())+" .");
        OUT.println();
        OUT.println( "Vous pouvez dès à présent me contacter au "+consultation.getEmploye().getNum_tel()+". A tout de suite! ");
        OUT.println();
        OUT.println( " Médiumiquement vôtre,  "+consultation.getMedium().getDenomination());
        Message.fin();
    }
    
   
    public static void envoyer_mail_success(Client client){
        
         StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        
        mailWriter.println("Bonjour "+ trouverCivilite(client) + client.getPrenom()+ client.getNom() + ", nous vous confirmons votre inscription au service PREDICT’IF. Rendezvous vite sur notre site pour consulter votre profil astrologique et profiter des dons\n" +
"incroyables de nos mediums");
        mailWriter.println();
       
        
        envoyerMail( client.getMail()," Bienvenue chez PREDICT’IF", corps.toString());
    }
    
    


    public static void envoyer_mail_echec(Client client){
           
            StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        
        mailWriter.println("Bonjour"+trouverCivilite(client) + client.getPrenom()+ client.getNom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué...\n" +
"Merci de recommencer ultérieurement.");
        mailWriter.println();
       
        
        envoyerMail( client.getMail(),"Echec de l’inscription chez PREDICT’IF", corps.toString());
           
    }
    
    private static String trouverCivilite(Utilisateur u){
        String civ;
        if("H".equals(u.getGenre())){
            civ = "M. ";
        }else{
            civ = "Mme. ";
        }
        return civ;
    }
} 