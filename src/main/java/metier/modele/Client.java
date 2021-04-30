/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author emilienmarion
 */
@Entity
public class Client extends Utilisateur {
     
   @Column(nullable = false)
    private String adresse_postale;
   
   @OneToOne(cascade = CascadeType.ALL)
    private ProfilAstral profilAstral;
   
   @OneToMany(mappedBy="client")
   private List<Consultation> consultations;

    /**
     * Get the value of profilAstral
     *
     * @return the value of profilAstral
     */
    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    /**
     * Set the value of profilAstral
     *
     * @param profilAstral new value of profilAstral
     */
    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }


    public Client(String nom, String prenom, String mail, String motDePasse, String date_naissance, String num_tel, String genre,String adresse_postale ) {
        super(nom, prenom, mail, motDePasse, date_naissance, num_tel, genre);
        this.adresse_postale=adresse_postale;
        
    }

    public Client() {
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void addConsultations( Consultation consultation ) {
        this.consultations.add(consultation);
    }

   
   
   

    
   

    /**
     * Get the value of adresse_postale
     *
     * @return the value of adresse_postale
     */
    public String getAdresse_postale() {
        return adresse_postale;
    }

    /**
     * Set the value of adresse_postale
     *
     * @param adresse_postale new value of adresse_postale
     */
    public void setAdresse_postale(String adresse_postale) {
        this.adresse_postale = adresse_postale;
    }

}
