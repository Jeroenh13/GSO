package Banner;

import Shared.IEffectenbeurs;
import Shared.IFonds;
import fontys.observer.RemotePropertyListener;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;

public class BannerController extends Application implements RemotePropertyListener {

    private AEXBanner banner;
    private final IEffectenbeurs effectenbeurs = null;
    private final Registry registry = null;
    private static final String bindingName = "MockBeurs";
    BannerClient client = null;

    @Override
    public void start(Stage primaryStage) {

        banner = new AEXBanner();

        //primaryStage acts as the common stage of the AEXBanner and the 
        //BannerController:
        banner.start(primaryStage);

        // Create client
        client = new BannerClient("192.168.220.1", 1099);
        try {
            UnicastRemoteObject.exportObject(this, 100);
            client.effectenbeurs.addListener(this, "koers");
            System.out.println("Added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * unfortunately, is needed pro forma to act as runnable class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    void updateKoersen() throws RemoteException {
        banner.setKoersen(client.getKoersen());
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
