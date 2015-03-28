import java.util.Timer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class BannerController extends Application {

    private AEXBanner banner;
//  private IEffectenBeurs effectenbeurs;

    @Override
    public void start(Stage primaryStage) {
        banner = new AEXBanner();
        //primaryStage acts as the common stage of the AEXBanner and the 
        //BannerController:
        banner.start(primaryStage);

        //create a timer which polls every 2 seconds
        Timer pollingTimer;
        // todo

        
        //remove pollingTimer as soon as primaryStage is closing:
        //primaryStage.setOnCloseRequest((WindowEvent we) -> {
        //  pollingTimer.cancel();
        //});
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