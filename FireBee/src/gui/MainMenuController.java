/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jeroen
 */
public class MainMenuController extends Application {

    @FXML ImageView imgPlattegrond;
    @FXML ImageView imgNavigeren;
    @FXML ImageView imgNavigation;
    
    @FXML ImageView imgPoi1;
    @FXML ImageView imgPoi2;
    
    @FXML ImageView imgUnit1;
    @FXML ImageView imgUnit2;
    @FXML ImageView imgUnit3;
    

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void gotoPlattegrond(MouseEvent event) {
        gotoNextScreen("Plattegrond");
    }
    
    @FXML
    private void gotoNavigation(MouseEvent event) {
        gotoNextScreen("Navigation");
    }
    
    @FXML
    private void gotoMenu(MouseEvent event) {
        gotoNextScreen("Menu");
    }
    
    @FXML
    private void toggleUnits(MouseEvent event) {
        System.out.println("Toggle Units");
        imgUnit1.setVisible(!imgUnit1.isVisible());
        imgUnit2.setVisible(!imgUnit2.isVisible());
        imgUnit3.setVisible(!imgUnit3.isVisible());
    }
    
    @FXML
    private void togglePoi(MouseEvent event) {
        System.out.println("Toggle Pois");
        imgPoi1.setVisible(!imgPoi1.isVisible());
        imgPoi2.setVisible(!imgPoi2.isVisible());
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void gotoNextScreen(String input){
        if (input.equals("Plattegrond")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Plattegrond-2.fxml"));
                
                Stage stage = new Stage();
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(input.equals("Navigation"))
        {
            imgNavigation.setVisible(true);
            imgNavigation.toFront();
        }
        
        else if(input.equals("Menu"))
        {
            imgNavigation.setVisible(false);
            imgNavigation.toBack();
        }
    }
}
