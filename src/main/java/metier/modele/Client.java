package metier.modele;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 * Classe qui gere l'objet metier client
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
public class Client extends Utilisateur {
     
    /**
     * adresse postale du client
     */
    @Column(nullable = false)
    private String adresse_postale;
   
    /**
     * profil astral du client
     */
    @OneToOne(cascade = CascadeType.ALL)
    private ProfilAstral profilAstral;
   
    /**
     * liste de toutes les consultations demandees par le client
     */
    @OneToMany(mappedBy="client")
    private List<Consultation> consultations;

    /**
     * constructeur par defaut de la classe client, necessaire pour la couche persistence
     */
    public Client() {
    }
    
    /**
     * constructeur principal de la classe client
     * @param nom nom du client
     * @param prenom prenom du client
     * @param mail adresse email du client
     * @param motDePasse mot de passe du client
     * @param date_naissance date de naissance du client, au forma dd/mm/yyyy
     * @param num_tel numero de telephone du client
     * @param genre genre du client
     * @param adresse_postale adresse postale du client
     */
    public Client(String nom, String prenom, String mail, String motDePasse, String date_naissance, String num_tel, String genre,String adresse_postale ) {
        super(nom, prenom, mail, motDePasse, date_naissance, num_tel, genre);
        this.adresse_postale=adresse_postale;
        
    }

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

    /**
     * Get la liste de consultations du client
     * @return liste de consultations du client
     */
    public List<Consultation> getConsultations() {
        return consultations;
    }

    /**
     * rajoute une nouvelle consultations a la liste des consultatinos demandees par le client
     * @param consultation consutation a rajouter
     */
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
