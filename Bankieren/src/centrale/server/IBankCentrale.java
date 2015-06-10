/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale.server;

import bank.internettoegang.IBalie;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Cypher
 */
public interface IBankCentrale extends Remote {
    
    public boolean addBank(String name, IBalie bank) throws RemoteException;
    
    public boolean removeBank(String name) throws RemoteException;
    
    public IBalie getBank(String name) throws RemoteException;
}
