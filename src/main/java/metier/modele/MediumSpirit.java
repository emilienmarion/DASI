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
public class MediumSpirit extends Medium implements Serializable {
    
        private String support;

    public MediumSpirit(){
    }

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
