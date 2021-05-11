package metier.modele;

import java.util.Date;
import javax.persistence.*;

/**
 * classe qui gere l'objet metier consultation
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
public class Consultation {
    
    /**
     * identifiant de la consultation, autogenere lors de la persistence
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    /**
     * date de debut de la consultation
     */
    private Date dateDeb;
    
    /**
     * date de fin de la consultation
     */
    private Date dateFin;
    
    /**
     * commentaire sur la seance fait par l'employe incarnant le medium choisi par le client
     */
    private String commentaire;
     /**
      * date a laquelle le client a fait la demande de consultation
      */
    private Date demandeConsult;
    
    /**
     * client qui a demande la consultation
     */
    @ManyToOne
    private Client client;

    /**
     * employe qui incarne le medium demande par le client
     */
    @ManyToOne
    private Employe employe;
     /**
      * medium choisi par le client pour la consultation
      */
    @ManyToOne
    private Medium medium;

    /**
     * constructeur par defaut de la classe consutlation, necessaire pour la couche persistence
     */
    public Consultation() {
    }

    /**
     * constructeur principal de la classe consultation
     * @param DemandeConsult date a laquelle le client a demande la consultation
     * @param client client qui a passe la demande
     * @param employe employe choisi par l'application pour incarner le medium
     * @param medium medium choisi par le client pour la consultation
     */
    public Consultation(Date DemandeConsult, Client client, Employe employe, Medium medium) {
        this.demandeConsult = DemandeConsult;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
        this.dateDeb=null;
        dateFin=null;
        commentaire=null;
        
    }
    
    /**
     * Get the value of id
     * @return the value of id
     */
    public Long getId() {
        return id;
    }
    

    /**
     * Get the value of commentaire
     *
     * @return the value of commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Set the value of commentaire
     *
     * @param commentaire new value of commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    /**
     * Get the value of dateFin
     *
     * @return the value of dateFin
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Set the value of dateFin
     *
     * @param dateFin new value of dateFin
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


    /**
     * Get the value of dateDeb
     *
     * @return the value of dateDeb
     */
    public Date getDateDeb() {
        return dateDeb;
    }

    /**
     * Set the value of dateDeb
     *
     * @param dateDeb new value of dateDeb
     */
    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    /**
     * Get the value of client
     * @return the value of client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Set the value of client
     * @param client the new value of client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Get the value of employe
     * @return the value of employe
     */
    public Employe getEmploye() {
        return employe;
    }

    /**
     * Set the value of employe
     * @param employe the new value of employe
     */
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    /**
     * Get the value of medium
     * @return the value of medium
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * Set the value of medium
     * @param medium the new value of medium
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     * Get the value of demandeConsult
     * @return the value of demandeConsult
     */
    public Date getDemandeConsult() {
        return demandeConsult;
    }

    /**
     * Set the value of demandeConsult
     * @param DemandeConsult the new value of demandeConsult
     */
    public void setDemandeConsult(Date DemandeConsult) {
        this.demandeConsult = DemandeConsult;
    }

    public String toString(){
        return "Consultation " + id + " datedemande "+ demandeConsult+ " datedebut: " + dateDeb + " datefin: "+ dateFin + " Emp:" +employe;
    }
}
