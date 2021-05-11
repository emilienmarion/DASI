package metier.modele;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Classe abstraite qui definit les attributs en commun entre les employes et les clients
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Utilisateur {

    /**
     * Identifiant de l'utilisateur, autogenere lors de la persistance en base de donnes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * nom de l'utilisateur
     */
    protected String nom;

    /**
     * prenom de l'utilisateur
     */
    protected String prenom;

    /**
     * adresse email de l'utilisateur
     */
    @Column(unique = true)
    protected  String mail;

    /**
     * mot de passe de l'utilisateur, dans le cadre de ce TP celui-ci ne sera pas encypte
     */
    protected String motDePasse;

    /**
     * date de naissance de l'utilisateur, au format dd/mm/yyyy
     */
    protected String date_naissance;

    /**
     * numero de telephone de l'utilisateur
     */
    protected String num_tel;

    /**
     * genre de l'utilisateur
     */
    protected String genre;
    
    /**
     * constructeur par defaut de la classe utilisateur, necessaire pour la couche persistence
     */
    protected Utilisateur() {
    }

    /**
     * constructeur principal de la classe utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     * @param mail adresse email de l'utilisateur
     * @param motDePasse mot de passe de l'utilisateur
     * @param date_naissance date de naissance de l'utilisateur, au format dd/mm/yyyy 
     * @param num_tel numero de telephone de l'utilisateur
     * @param genre genre de l'utilisateur
     */
    public Utilisateur(String nom, String prenom, String mail, String motDePasse, String date_naissance, String num_tel, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.date_naissance = date_naissance;
        this.num_tel = num_tel;
        this.genre = genre;
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
     * Get the value of num_tel
     *
     * @return the value of num_tel
     */
    public String getNum_tel() {
        return num_tel;
    }

    /**
     * Set the value of num_tel
     *
     * @param num_tel new value of num_tel
     */
    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }


    /**
     * Get the value of date_naissance
     *
     * @return the value of date_naissance
     */
    public String getDate_naissance() {
        return date_naissance;
    }

    /**
     * Set the value of date_naissance
     *
     * @param date_naissance new value of date_naissance
     */
    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    /**
     * Get the value of id
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the value of nom
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     * @param nom the new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the value of prenom
     * @return the value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the value of prenom
     * @param prenom the new value of prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Get the value of mail
     * @return the value of mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Set the value of mail
     * @param mail the new value of mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Get the value of motDePasse (not encrypted)
     * @return the value of motDePasse (not encrypted)
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Set the value of motDePasse
     * @param motDePasse the new value of MotDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

     
    @Override
    public String toString(){
        return "id="+id+";denomination="+nom+";genre="+genre;
    }
}
