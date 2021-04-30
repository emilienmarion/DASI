/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author emilienmarion
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Utilisateur {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nom;
    protected String prenom;
    @Column(unique = true)
    protected  String mail;
    protected String motDePasse;
    protected String date_naissance;
    protected String num_tel;
    protected String genre;

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


    protected Utilisateur() {
    }

    
    public Long getId() {
        return id;
    }
     public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

     
    @Override
    public String toString(){
        return "id="+id+";denomination="+nom+";genre="+genre;
    }

    
    
}
