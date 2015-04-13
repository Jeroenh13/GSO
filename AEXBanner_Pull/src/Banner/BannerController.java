package Banner;

import Shared.IEffectenbeurs;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.stage.Stage;

public class BannerController extends Application {

    private AEXBanner banner;
    private final IEffectenbeurs effectenbeurs = null;
    private final Registry registry = null;
    private static final String bindingName = "MockBeurs";
    BannerClient client = null;

    @Override
    public void start(Stage primaryStage) throws RemoteException {

        banner = new AEXBanner();

        //primaryStage acts as the common stage of the AEXBanner and the 
        //BannerController:
        banner.start(primaryStage);

        // Create client
        client = new BannerClient();
        client.Connect("10.0.0.10", 1099, banner);
    }

    /**
     * unfortunately, is needed pro forma to act as runnable class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
