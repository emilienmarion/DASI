package metier.modele;

import java.util.List;
import javax.persistence.*;

/**
 * Classe qui gere l'objet metier employe
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
public class Employe extends Utilisateur {
    
    /**
     * nombre de consultations realisees par l'employe
     */
    private int nb_consultations;
    
    /**
     * true si l'employe est en ligne, false sinon
     */
    private boolean statut_en_ligne;
    
    /**
     * attribut utile pour la gestion de la concurrence lors de la creation de consultations
     */
    @Version
    private int version;
    
    /**
     * liste des consultations realises par l'employe
     */
    @OneToMany(mappedBy="employe")
    private List<Consultation> consultations;
    
    /**
     * constructeur par default, necessaire pour la couche persistence
     */
    public Employe(){
    }
    
    /**
     * constructeur principal de la classe employe
     * @param nom nom de l'employe
     * @param prenom prenom de l'employe
     * @param mail adresse email de l'employe
     * @param motDePasse mot de passe de l'employe
     * @param date_naissance date de naissance de l'employe, au format dd/mm/yyyy
     * @param num_tel numero de telephone de l'employe
     * @param genre genre de l'employe
     */
    public Employe( String nom, String prenom, String mail, String motDePasse, String date_naissance, String num_tel, String genre) {
        super(nom, prenom, mail, motDePasse, date_naissance, num_tel, genre);
        this.nb_consultations = 0 ;
        this.statut_en_ligne = false;
    }



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

    /**
     * Get the value of consultations
     * @return the value of consultations
     */
    public List<Consultation> getConsultations() {
        return consultations;
    }

    /**
     * Set the value of consultations
     * @param consultations the new value of consultations
     */
    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
    
    /**
     * rajoute une nouvelle consultation a la liste de consultations de l'employe
     * @param consultation consultations a rajouter
     */
    public void addConsultations( Consultation consultation ) {
        this.consultations.add(consultation);
    }

 @Override
    public String toString(){
        return super.toString() + " Type=employé  statut en ligne="+statut_en_ligne+" nb consultation="+ nb_consultations;
    }
    
}
