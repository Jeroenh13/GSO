/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale.server;

import bank.internettoegang.IBalie;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jurgen
 */
public class BankCentrale extends UnicastRemoteObject implements IBankCentrale {

    private final Map<String, IBalie> banks;
    private final Console console;

    private Registry registry;

    public BankCentrale(Console console) throws RemoteException {
        this.console = console;
        banks = new HashMap<>();
        registry = null;
    }

    public boolean register() {
        try {
            String address = java.net.InetAddress.getLocalHost().getHostAddress();
            int port = 1099;
            String rmiCentrale = address + ":" + port + "/" + "Centrale";
            try {
                registry = LocateRegistry.getRegistry(port);
                registry.list();
            } catch (RemoteException e) {
                registry = LocateRegistry.createRegistry(port);
            }
            Naming.rebind("Centrale", this);
            
            console.println("Bank Centrale online @ " + rmiCentrale);
            return true;
        } 
        catch(Exception ex) {
            console.printError(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean addBank(String name, IBalie bankBalie) {

        if (!banks.containsKey(name)) {
            banks.put(name, bankBalie);

            return banks.containsKey(name);
        }
        return false;
    }

    @Override
    public boolean removeBank(String name) {
        if (banks.containsKey(name)) {
            banks.remove(name);

            return !banks.containsKey(name);
        }
        return false;
    }

    @Override
    public IBalie getBank(String name) {

        if (banks.containsKey(name)) {
            return banks.get(name);
        }

        return null;
    }

    public void listBanks() {

        console.println("Current registered banks:");
        for (Map.Entry<String, IBalie> bank : banks.entrySet()) {
            console.println(" -> " + bank);
        }
    }
}
