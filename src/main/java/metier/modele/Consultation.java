/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author emilienmarion
 */
@Entity
public class Consultation {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private Date dateDeb;
        
    private Date dateFin;
    
    private String commentaire;
    
    private Date demandeConsult;
    
   
    
    @ManyToOne
    private Client client;
    
    
      @ManyToOne
    private Employe employe;
    
      @ManyToOne
    private Medium medium;

    public Long getId() {
        return id;
    }

    public Consultation() {
    }

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Date getDemandeConsult() {
        return demandeConsult;
    }

    public void setDemandeConsult(Date DemandeConsult) {
        this.demandeConsult = DemandeConsult;
    }

    
}
