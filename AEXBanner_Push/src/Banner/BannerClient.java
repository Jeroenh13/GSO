/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banner;

import Shared.IEffectenbeurs;
import Shared.IFonds;
import fontys.observer.RemotePropertyListener;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Jeroen Hendriks
 */
public class BannerClient extends UnicastRemoteObject implements Serializable, RemotePropertyListener {

    public IEffectenbeurs effectenbeurs = null;
    private static final String bindingName = "MockBeurs";
    AEXBanner banner;

    public BannerClient() throws RemoteException{
        
    }
    
    public boolean Connect(String ipAddress, int portNumber, AEXBanner banner)
    {
        this.banner = banner;
        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try {
            effectenbeurs = (IEffectenbeurs) Naming.lookup("rmi://" + ipAddress + ":" + portNumber + "/" + bindingName);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot bind Effectenbeurs");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            effectenbeurs = null;
        } catch (MalformedURLException ex) {
            System.out.println("Client: Cannot bind Effectenbeurs");
            System.out.println("Client: MalformedURLException: " + ex.getMessage());
            effectenbeurs = null;
        } catch (NotBoundException ex) {
            System.out.println("Client: Cannot bind Effectenbeurs");
            System.out.println("Client: NotBoundException: " + ex.getMessage());
            effectenbeurs = null;
        }

        // Print result locating registry
        if (effectenbeurs != null) {
            System.out.println("Client: Effectenbeurs located");
        } else {
            System.out.println("Client: Effectenbeurs is null pointer");
        }

        try {
            effectenbeurs.addListener(this, "koers");
            System.out.println("Added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        StringBuilder sb = new StringBuilder();
        try {
            ArrayList<IFonds> fondsen = (ArrayList<IFonds>) evt.getNewValue();
            for (IFonds iF : fondsen) {
                sb.append(iF.getNaam()).append(":").append(String.format("%.2f", iF.getKoers())).append("        ");
            }
            System.out.println("Updated");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        banner.setKoersen(sb.toString());
    }
}
