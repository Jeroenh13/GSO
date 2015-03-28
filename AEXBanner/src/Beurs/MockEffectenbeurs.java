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
    private ArrayList<IFonds> exampleFonds;
    
    /**
     * 
     * constructor that adds a few fiction Fonds, Fonds will get a fake value in a later state
     */
    public MockEffectenbeurs(){
        exampleFonds = new ArrayList<>();
        exampleFonds.add(new Fonds("Fontys"));
        exampleFonds.add(new Fonds("Bravo"));
        exampleFonds.add(new Fonds("Avans"));
        exampleFonds.add(new Fonds("VVtamar"));
        exampleFonds.add(new Fonds("Overflow"));
        exampleFonds.add(new Fonds("BeastlyRiders"));
        exampleFonds.add(new Fonds("Jumbo"));
        exampleFonds.add(new Fonds("DataExpand"));
        exampleFonds.add(new Fonds("Herbers"));
        exampleFonds.add(new Fonds("LotOfStuff"));
    }
    
    /**
     * 
     * 
     * @return the list of example Fonts
     */
    @Override
    public ArrayList<IFonds> getKoersen() {
        
        
        return exampleFonds;
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
