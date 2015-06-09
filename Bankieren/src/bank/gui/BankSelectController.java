/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.gui;

import bank.internettoegang.IBalie;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class BankSelectController implements Initializable {

    private String bankNaam;
   
    @FXML
    private ComboBox<String> cbSelectBank;
    
    private BankierClient application;
    
    public void setApp(BankierClient application){
        this.application = application;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbSelectBank.getItems().addAll(FXCollections.observableArrayList("RaboBank", "ING", "SNS", "ABN AMRO", "ASN"));
        cbSelectBank.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                try {
                    bankNaam = URLEncoder.encode((String)ov.getValue(), "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(BankSelectController.class.getName()).log(Level.SEVERE, null, ex);
                }
                IBalie balie =  application.connectToBalie(bankNaam);
                application.gotoLogin(balie, "");
            }
        }
        );
    }
    
    @FXML
    private void selectBank(ActionEvent event) {
    }
}
