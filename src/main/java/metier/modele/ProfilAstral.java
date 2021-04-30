/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author emilienmarion
 */
@Entity
public class ProfilAstral {
     @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
     private long id;
    
    private String signe_zodiac;
        
    private String signe_chinois;
    
    private String couleurPB;

    public ProfilAstral(String signe_zodiac, String signe_chinois, String couleurPB, String animal_totem) {
        this.signe_zodiac = signe_zodiac;
        this.signe_chinois = signe_chinois;
        this.couleurPB = couleurPB;
        this.animal_totem = animal_totem;
    }

    public ProfilAstral() {
    }
    
    private String animal_totem;

    /**
     * Get the value of animal_totem
     *
     * @return the value of animal_totem
     */
    public String getAnimal_totem() {
        return animal_totem;
    }


    /**
     * Get the value of couleurPB
     *
     * @return the value of couleurPB
     */
    public String getCouleurPB() {
        return couleurPB;
    }


    /**
     * Get the value of signe_chinois
     *
     * @return the value of signe_chinois
     */
    public String getSigne_chinois() {
        return signe_chinois;
    }


    /**
     * Get the value of signe_zodiac
     *
     * @return the value of signe_zodiac
     */
    public String getSigne_zodiac() {
        return signe_zodiac;
    }

    
 public String toString() {
        return "animal_totem=" +animal_totem + ",  couleurPB=" + couleurPB + ", signe_chinois=" + signe_chinois + ", signe_zodiac=" + signe_zodiac ;
    }

    
}
