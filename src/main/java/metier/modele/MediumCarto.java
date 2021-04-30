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
public class MediumCarto extends Medium implements Serializable {

    public MediumCarto(String denomination, String genre, String presentation) {
        super(denomination, genre, presentation);
    }
    
    public MediumCarto(){
    }
    
    
    @Override
    public String toString(){
        return super.toString() + " Type=Cartomencien";
    }

}
    
    

