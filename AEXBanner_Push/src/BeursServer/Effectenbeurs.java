/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeursServer;

import Shared.IEffectenbeurs;
import Shared.IFonds;
import fontys.observer.RemotePropertyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Jeroen Hendriks
 */
public class Effectenbeurs implements IEffectenbeurs{

    @Override
    public ArrayList<IFonds> getKoersen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
