/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author emilienmarion
 */
@Entity
public class Employe extends Utilisateur {
    
        private int nb_consultations;
        private boolean statut_en_ligne;
    @Version
    private int version;

    /**
     * Get the value of version
     *
     * @return the value of version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set the value of version
     *
     * @param version new value of version
     */
    public void setVersion(int version) {
        this.version = version;
    }

         @OneToMany(mappedBy="employe")
   private List<Consultation> consultations;

    public Employe( String nom, String prenom, String mail, String motDePasse, String date_naissance, String num_tel, String genre) {
        super(nom, prenom, mail, motDePasse, date_naissance, num_tel, genre);
        this.nb_consultations = 0 ;
        this.statut_en_ligne = false;
        
    }

    public Employe(){
        
    }

    /**
     * Get the value of nb_consultations
     *
     * @return the value of nb_consultations
     */
    public int getNb_consultations() {
        return nb_consultations;
    }

    /**
     * Set the value of nb_consultations
     *
     * @param nb_consultations new value of nb_consultations
     */
    public void setNb_consultations(int nb_consultations) {
        this.nb_consultations = nb_consultations;
    }

        

    /**
     * Get the value of statut_en_ligne
     *
     * @return the value of statut_en_ligne
     */
    public boolean isStatut_en_ligne() {
        return statut_en_ligne;
    }

    /**
     * Set the value of statut_en_ligne
     *
     * @param statut_en_ligne new value of statut_en_ligne
     */
    public void setStatut_en_ligne(boolean statut_en_ligne) {
        this.statut_en_ligne = statut_en_ligne;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
    
     public void addConsultations( Consultation consultation ) {
        this.consultations.add(consultation);
    }

 @Override
    public String toString(){
        return super.toString() + " Type=employé  statut en ligne="+statut_en_ligne+" nb consultation="+ nb_consultations;
    }
    
}
