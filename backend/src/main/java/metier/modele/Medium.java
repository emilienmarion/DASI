package metier.modele;

import java.util.List;
import javax.persistence.*;

/**
 * Classe abstraite les attributs communs a tous les types de mediums
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public  abstract class Medium {
    
    /**
     * identifiant du medium, autogenere lors de la persistance en base de donnes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    /**
     * lien de la photo du medium, utile lors de l'affichage de son profil
     */
    private String photo;
    
    /**
     * denomination du medium
     */
    private String denomination;
    
    /**
     * genre du medium
     */
    private String genre;
    
    /**
     * texte de presentation du medium
     */
    private String presentation;
    
    /**
     * nombre de consultations realisees par ce medium
     */
    private int nbConsultation;
    
    /**
     * liste de toutes les consultations realisees par ce medium
     */
    @OneToMany(mappedBy="medium")
    private List<Consultation> consultations;
    
    /**
     * constructeur par defaut de la classe medium, necessaire pour la couche persistence
     */
    public Medium(){
     
    }
    /**
     * constructeur principal de la classe medium
     * @param denomination denomination du medium
     * @param genre genre du medium
     * @param presentation texte de presentation du medium
     * @param photo lien de la photo du medium, utile lors de l'affichage de son profil
     */
    public Medium(String denomination, String genre, String presentation,String photo) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
        this.nbConsultation = 0 ;
        this.photo=photo;
    }

    /**
     * Get the value of photo
     *
     * @return the value of photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Set the value of photo
     *
     * @param photo new value of photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Get the value of nbConsultation
     *
     * @return the value of nbConsultation
     */
    public int getNbConsultation() {
        return nbConsultation;
    }

    /**
     * Set the value of nbConsultation
     *
     * @param nbConsultation new value of nbConsultation
     */
    public void setNbConsultation(int nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    /**
     * Get the value of id
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     * @param id the value of id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Get the value of presentation
     *
     * @return the value of presentation
     */
    public String getPresentation() {
        return presentation;
    }

    /**
     * Set the value of presentation
     *
     * @param presentation new value of presentation
     */
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    /**
     * Get the value of genre
     *
     * @return the value of genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the value of genre
     *
     * @param genre new value of genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Get the value of denomination
     *
     * @return the value of denomination
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Set the value of denomination
     *
     * @param denomination new value of denomination
     */
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    /**
     * Get la liste de consultations du medium
     * @return la liste de consultations du medium
     */
    public List<Consultation> getConsultations() {
        return consultations;
    }

    /**
     * Set la liste de consultations du medium
     * @param consultations nouvelle liste de consultaions pour le medium
     */
    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    /**
     * rajouter une consultation a la liste de consultations du medium
     * @param consultation consultation a rajouter a la liste
     */
    public void addConsultations( Consultation consultation ) {
        this.consultations.add(consultation);
    }



    @Override
    public String toString(){
        //return "Medium: id="+id+";denomination="+denomination+";genre="+genre+";presentation="+presentation+"nbConsultation"+nbConsultation;
        return "Medium: id="+id+"nbConsultation=  "+nbConsultation;
    }
}