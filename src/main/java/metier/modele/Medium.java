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
@Inheritance(strategy=InheritanceType.JOINED)
public  abstract class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String denomination;

    private String genre;

    private String presentation;
    
        private int nbConsultation;

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

    
    
     @OneToMany(mappedBy="medium")
   private List<Consultation> consultations;

     
    public Medium(String denomination, String genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
         this.nbConsultation = 0 ;
    }
    
    
 public Medium(){
     
 }

    public Long getId() {
        return id;
    }

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
        //return "Medium: id="+id+";denomination="+denomination+";genre="+genre+";presentation="+presentation+"nbConsultation"+nbConsultation;
         return "Medium: id="+id+"nbConsultation=  "+nbConsultation;
    }
    

}