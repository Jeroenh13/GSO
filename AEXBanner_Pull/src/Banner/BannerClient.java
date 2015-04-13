/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banner;

import Shared.IEffectenbeurs;
import Shared.IFonds;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeroen Hendriks
 */
public class BannerClient implements Serializable {

    public IEffectenbeurs effectenbeurs = null;
    private static final String bindingName = "MockBeurs";

    public BannerClient(String ipAddress, int portNumber) {
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
        }
        catch (MalformedURLException ex) {
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
    }

    public String getKoersen() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        try {
            ArrayList<IFonds> fondsen = effectenbeurs.getKoersen();
            for (IFonds iF : fondsen) {
                sb.append(iF.getNaam()).append(":").append(String.format("%.2f", iF.getKoers())).append("        ");
            }
            System.out.println("Updated");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return sb.toString();
    }

}
