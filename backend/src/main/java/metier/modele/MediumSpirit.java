package metier.modele;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe qui gere l'objet metier medium spirit
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
public class MediumSpirit extends Medium implements Serializable {
    
    /**
     * support grace auquel le medium fait ses predictions
     */
    private String support;

    /**
     * constructeur par default, necessaire pour la couche persistence
     */
    public MediumSpirit(){
    }

    /**
     * constructeur principal
     * @param denomination denomination du medium
     * @param genre genre du medium
     * @param presentation texte de presentation du medium
     * @param support support grace auquel le medium fait ses predictions 
     * @param photo lien de la photo du medium, utile lors de l'affichage de son profil
     */
    public MediumSpirit( String denomination, String genre, String presentation,String support,String photo) {
        super(denomination, genre, presentation,photo);
        this.support = support;
    }

    /**
     * Get the value of support
     *
     * @return the value of support
     */
    public String getSupport() {
        return support;
    }

    /**
     * Set the value of support
     *
     * @param support new value of support
     */
    public void setSupport(String support) {
        this.support = support;
    }

   @Override
    public String toString(){
        return super.toString() + " Type=Spirit support="+support;
    }
}
