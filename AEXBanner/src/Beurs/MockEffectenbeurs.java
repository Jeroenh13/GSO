/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beurs;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Jeroen Hendriks
 */
public class MockEffectenbeurs  implements IEffectenbeurs {
    
    //example fonds
    private ArrayList<IFonds> exampleFonts;
    
    /**
     * 
     * constructor that adds a few fiction Fonds, Fonds will get a fake value in a later state
     */
    public MockEffectenbeurs(){
        exampleFonts.add(new Fonds("Fontys"));
        exampleFonts.add(new Fonds("Bravo"));
        exampleFonts.add(new Fonds("Avans"));
        exampleFonts.add(new Fonds("VVtamar"));
        exampleFonts.add(new Fonds("Overflow"));
        exampleFonts.add(new Fonds("BeastlyRiders"));
        exampleFonts.add(new Fonds("Jumbo"));
        exampleFonts.add(new Fonds("DataExpand"));
        exampleFonts.add(new Fonds("Herbers"));
        exampleFonts.add(new Fonds("LotOfStuff"));
    }
    
    /**
     * 
     * 
     * @return the list of example Fonts
     */
    @Override
    public ArrayList<IFonds> getKoersen() {
        
        
        return exampleFonts;
    }
    
    /**
     * 
     * @param fonds
     * @return a random double
     */
    private double calculateKoers(IFonds fonds){
        double koers;
        Random r = new Random();
        koers = r.nextDouble();
        return koers;
    }
}
