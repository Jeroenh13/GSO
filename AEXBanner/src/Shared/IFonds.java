/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.io.Serializable;

/**
 *
 * @author Jeroen Hendriks
 */

public interface IFonds extends Serializable {
        
    /**
     * Gets the name of the Fonds
     * @return The name
     */
    String getNaam();
    
    /**
     * Gets the amount  
     * @return the koers
     */
    double getKoers();
    
    public void setKoers(double newKoers);
    
}
