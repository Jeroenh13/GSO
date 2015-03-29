package Banner;

import Beurs.*;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class BannerController extends Application {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;

    @Override
    public void start(Stage primaryStage) {
        banner = new AEXBanner();
        effectenbeurs = new MockEffectenbeurs();
        //primaryStage acts as the common stage of the AEXBanner and the 
        //BannerController:
        banner.start(primaryStage);
        
        //create a timer which polls every 2 seconds
        
        Timer pollingTimer = new Timer("KoersUpdate");
        pollingTimer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        updateKoersen();                        
                    }
                    
                });
            }
        }, 0, 2000);
        
        // todo

        
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
    
    void updateKoersen()
    {
        StringBuilder sb = new StringBuilder();
        for(IFonds iF : effectenbeurs.getKoersen())
        {
            sb.append(iF.getNaam()).append(":").append(iF.getKoers()).append("        ");
        }
        banner.setKoersen(sb.toString());
    }

}