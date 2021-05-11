package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe qui gere le profil astral de chaque client
 * @author Emilien Marion, Ithan Velarde, Taha Mdarhri, Tomas Fabregues
 */
@Entity
public class ProfilAstral {
    /**
     * identifiant du profil astral, autogenere lors de la persistence en base de donnees
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    /**
     * signe du zodiaque du client
     */
    private String signe_zodiac;
        
    /**
     * signe astrologique chinois du client
     */
    private String signe_chinois;
    
    /**
     * couleur porte-bonheur du client
     */
    private String couleurPB;
    
    /**
     * animal totem du client
     */
    private String animal_totem;
    
    /**
     * constructeur par default, necessaire pour la couche persistence
     */
    public ProfilAstral() {
    }

    /**
     * construceur principal
     * @param signe_zodiac signe du zodiaque du client
     * @param signe_chinois signe astrologique chinois du client
     * @param couleurPB couleur porte-bonheur du client
     * @param animal_totem animal totem du client
     */
    public ProfilAstral(String signe_zodiac, String signe_chinois, String couleurPB, String animal_totem) {
        this.signe_zodiac = signe_zodiac;
        this.signe_chinois = signe_chinois;
        this.couleurPB = couleurPB;
        this.animal_totem = animal_totem;
    }

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
