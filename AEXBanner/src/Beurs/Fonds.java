/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beurs;

/**
 *
 * @author Anna-May
 */
class Fonds implements IFonds {
    
    private final String naam;
    private double koers;
    
    public Fonds(String naam) {
        this.naam = naam;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }
    
    public void setKoers(double newKoers){
        koers = newKoers;
    }
}
