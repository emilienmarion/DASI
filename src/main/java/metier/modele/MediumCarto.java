package metier.modele;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Classe qui gere l'objet metier medium cartomencien
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
public class MediumCarto extends Medium implements Serializable {
    
    /**
     * constructeur par default, necessaire pour la couche persistence
     */
    public MediumCarto(){
    }

    /**
     * constructeur principal
     * @param denomination denomination du medium
     * @param genre genre du medium
     * @param presentation texte de presentation du medium
     * @param photo lien de la photo du medium, utile lors de l'affichage de son profil
     */
    public MediumCarto(String denomination, String genre, String presentation,String photo) {
        super(denomination, genre, presentation,photo);
    }

    
    @Override
    public String toString(){
        return super.toString() + " Type=Cartomencien";
    }

}
    
    

