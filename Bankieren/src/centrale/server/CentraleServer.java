/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale.server;

import java.awt.Color;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

/**
 *
 * @author Cypher
 */
public class CentraleServer {
    
    private static BankCentrale centrale;
    private static Console console;
    
    public CentraleServer() throws RemoteException
    {
        console = new Console();
        centrale = new BankCentrale(console);
        console.setServer(centrale);
        
        centrale.register();
    }
    
    public static void main(String[] args) throws UnknownHostException, RemoteException{
        CentraleServer server = new CentraleServer();
        
        console.println("System self destruction initiated...", new Color(200, 50, 50));
        
    }
}
