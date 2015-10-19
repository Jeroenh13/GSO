/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jeroen
 */
public class FireBeeController extends Application {

    @FXML AnchorPane apMain;
    @FXML AnchorPane apFloorPlan;    
    
    @FXML ImageView imgPlattegrond;
    @FXML ImageView imgFloorPlan;
    @FXML ImageView imgNavigeren;
    @FXML ImageView imgNavigation;
    @FXML ImageView imgParking;
    @FXML ImageView imgP;
    @FXML ImageView imgBack;
    
    @FXML ImageView imgPoi1;
    @FXML ImageView imgPoi2;
    
    @FXML ImageView imgUnit1;
    @FXML ImageView imgUnit2;
    @FXML ImageView imgUnit3;
    
    @FXML ComboBox cbTeams;
    @FXML Stage dialog;
    
    private boolean vertical = true;
    
    

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FireBee.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        stage.setWidth(768);
        stage.setHeight(1024);
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
    private void gotoParking(MouseEvent event) {
        gotoNextScreen("Parking");
    }
    
    @FXML
    private void gotoMenu(MouseEvent event) {
        gotoNextScreen("Menu");
    }
    
    @FXML
    private void messagePopup(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SendMessage.fxml"));
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
        
        cbTeams = (ComboBox) dialogScene.lookup("#cbTeams");
        
        cbTeams.getItems().addAll(
                "Team A",
                "Team B",
                "Team C"
        );
    }
    
    @FXML
    private void sendMessage(MouseEvent event){
        getDialogStage().close();
    }
    
    @FXML
    private void toggleUnits(MouseEvent event) {
        imgUnit1.setVisible(!imgUnit1.isVisible());
        imgUnit2.setVisible(!imgUnit2.isVisible());
        imgUnit3.setVisible(!imgUnit3.isVisible());
    }
    
    @FXML
    private void togglePoi(MouseEvent event) {
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
                  switchView(true);
                  apFloorPlan.setVisible(true);
                  apFloorPlan.toFront();
            } catch (Exception ex) {
                Logger.getLogger(FireBeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(input.equals("Navigation"))
        {
            imgNavigation.setVisible(true);
            imgNavigation.toFront();
            imgP.setVisible(true);
            imgP.toFront();
            imgBack.setVisible(true);
            imgBack.toFront();
        }
        
        else if(input.equals("Parking"))
        {
            imgParking.setVisible(true);
            imgParking.toFront();
            imgNavigation.setVisible(false);
            imgNavigation.toBack();
            imgP.setVisible(false);
            imgP.toBack();
        }
        
        else if(input.equals("Menu"))
        {
            switchView(false);
            apFloorPlan.setVisible(false);
            apFloorPlan.toBack();
            imgNavigation.setVisible(false);
            imgNavigation.toBack();
            imgParking.setVisible(false);
            imgParking.toBack();
            imgP.setVisible(false);
            imgP.toBack();
            imgBack.setVisible(false);
            imgBack.toBack();
        }
    }
    
    private void switchView(boolean horizontal)
    {
        if(vertical != horizontal)
            return;
        
        if(vertical)
        {
            getStage().setWidth(1024);
            getStage().setHeight(768);
            vertical = false;
        }
        else
        {
            getStage().setWidth(768);
            getStage().setHeight(1024);
            vertical = true;
        }
    }
    
    private Stage getStage() {
        return (Stage) imgPlattegrond.getScene().getWindow();
    }
    
    private Stage getDialogStage() {
        return (Stage) cbTeams.getScene().getWindow();
    }
}
