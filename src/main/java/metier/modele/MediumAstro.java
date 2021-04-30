/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author emilienmarion
 */
@Entity
public class MediumAstro extends Medium implements Serializable {
    
    private String formation;
    private String promotion;

    public MediumAstro( String denomination, String genre, String presentation,String formation, String promotion) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }
     public MediumAstro(){
     }
     
    
    /**
     * Get the value of promotion
     *
     * @return the value of promotion
     */
    public String getPromotion() {
        return promotion;
    }

    /**
     * Set the value of promotion
     *
     * @param promotion new value of promotion
     */
    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    /**
     * Get the value of formation
     *
     * @return the value of formation
     */
    public String getFormation() {
        return formation;
    }

    /**
     * Set the value of formation
     *
     * @param formation new value of formation
     */
    public void setFormation(String formation) {
        this.formation = formation;
    }
    
    @Override
    public String toString(){
        return super.toString() + " Type=Astro Formation="+formation+" Promotion"+promotion;
    }

    
    
}
