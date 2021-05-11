package metier.modele;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe qui gere l'objet metier medium astrologue
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
public class MediumAstro extends Medium implements Serializable {
    
    /**
     * formation suivie par le medium astrologue
     */
    private String formation;
    
    /**
     * promotion au sein de son ecole du medium astrologue
     */
    private String promotion;
    
    /**
     * constructeur par default, necessaire pour la couche persistence
     */
    public MediumAstro(){
    }

    /**
     * constructeur principal
     * @param denomination denomination du medium
     * @param genre genre du medium
     * @param presentation texte presentation du medium
     * @param formation formation suivie par le medium astrologue
     * @param promotion promotion au sein de son ecole
     * @param photo lien de la photo du medium, utile lors de l'affichage de son profil
     */
    public MediumAstro( String denomination, String genre, String presentation,String formation, String promotion,String photo) {
        super(denomination, genre, presentation,photo);
        this.formation = formation;
        this.promotion = promotion;
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
