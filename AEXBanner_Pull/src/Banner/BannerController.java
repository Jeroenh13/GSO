package Banner;

import BeursServer.MockEffectenbeurs;
import Shared.IEffectenbeurs;
import Shared.IFonds;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BannerController extends Application {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs = null;
    private Registry registry = null;
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
        
        //create a timer which polls every 2 seconds
        Timer pollingTimer = new Timer("KoersUpdate");
        pollingTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        updateKoersen();
                    }

                });
            }
        }, 0, 2000);

        //remove pollingTimer as soon as primaryStage is closing:
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
            pollingTimer.cancel();
        });
    }

    /**
     * unfortunately, is needed pro forma to act as runnable class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    void updateKoersen() {
        try
        {
            banner.setKoersen(client.getKoersen());
        }
        catch(RemoteException ex){banner.setKoersen("Nothing to display");}
    }

}
