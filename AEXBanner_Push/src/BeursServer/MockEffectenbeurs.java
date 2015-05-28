/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeursServer;

import Shared.IEffectenbeurs;
import Shared.IFonds;
import fontys.observer.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Jeroen Hendriks
 */
public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs, Serializable {

    //example fonds
    private final ArrayList<IFonds> exampleFonds;
    public BasicPublisher bp;

    /**
     *
     * constructor that adds a few fiction Fonds, Fonds will get a fake value in
     * a later state
     */
    public MockEffectenbeurs() throws RemoteException {
        bp = new BasicPublisher(new String[]{"koers"});
        exampleFonds = new ArrayList<>();
        exampleFonds.add(new Fonds("Fontys", 200));
        exampleFonds.add(new Fonds("Bravo", 5.4));
        exampleFonds.add(new Fonds("Avans", 120.3));
        exampleFonds.add(new Fonds("VVtamar", 40));
        exampleFonds.add(new Fonds("Overflow", 29.2));
        exampleFonds.add(new Fonds("BeastlyRiders", 421.2));
        exampleFonds.add(new Fonds("Jumbo", 405));
        exampleFonds.add(new Fonds("DataExpand", 14));
        exampleFonds.add(new Fonds("Herbers", 23.4));
        exampleFonds.add(new Fonds("LotOfStuff", 123));

        Timer pollingTimer = new Timer("randomize");
        pollingTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                ArrayList<IFonds> old = new ArrayList<>(exampleFonds);
                RandomizeKoersen();
                bp.inform(this, "koers", old, exampleFonds);
            }
        }, 0, 2000);
    }

    /**
     *
     * @return the list of example Fonts
     */
    @Override
    public ArrayList<IFonds> getKoersen() throws RemoteException {
        return exampleFonds;
    }

    public void RandomizeKoersen() {
        for (IFonds iF : exampleFonds) {
            iF.setKoers(iF.getKoers() + calculateKoers(iF));
        }
    }

    /**
     *
     * @param fonds
     * @return a random double
     */
    private double calculateKoers(IFonds fonds) {
        double koers;
        Random r = new Random();
        koers = r.nextDouble();
        Random r2 = new Random();
        if (r2.nextInt(2) == 0) {
            return -koers;
        }
        return koers;
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        bp.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        bp.removeListener(listener, property);
    }
}