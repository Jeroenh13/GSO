/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeursServer;

import Shared.IFonds;
import java.io.Serializable;

/**
 *
 * @author Anna-May
*/
public class Fonds implements IFonds {
    
    private final String naam;
    private double koers;
    
    public Fonds(String naam,double koers) {
        this.naam = naam;
        this.koers = koers;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }
    
    @Override
    public void setKoers(double newKoers){
        koers = newKoers;
    }
}
