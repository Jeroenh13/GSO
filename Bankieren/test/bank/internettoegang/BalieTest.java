/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import bank.server.BalieServer;
import fontys.observer.RemotePropertyListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeroen Hendriks
 */
public class BalieTest {

    public IBalie balie;
    public IBank bank;
    public BalieServer balieserver;

    @Before
    public void setUp() {
        bank = new Bank("Rabobank");
        try {
            balie = new Balie(bank);
        } catch (RemoteException ex) {
            Logger.getLogger(BalieTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testBalie() {
        System.out.println("Start of 'openRekening'");
        String accName;
        int accNumber;
        IBankiersessie sessie;
        try {
            accName = balie.openRekening("", "Wijchen", "asdf");
            if (accName == null) {
                System.out.println("Test Succeeded name is empty");
            }
            accName = balie.openRekening("Jeroen", "", "asdf");
            if (accName == null) {
                System.out.println("Test Succeeded city is empty");
            }
            accName = balie.openRekening("Jeroen", "Wijchen", "adf");
            if (accName == null) {
                System.out.println("Test Succeeded password too short");
            }
            accName = balie.openRekening("Jeroen", "Wijchen", "aksidufpw");
            if (accName == null) {
                System.out.println("Test Succeeded password too long");
            }
            accName = balie.openRekening("Jeroen", "Wijchen", "asdf");
            if (accName != null) {
                System.out.println("Test Succeeded");
            }

            System.out.println("End of 'openRekening' \n---------------------------");

            System.out.println("Start of login");
            sessie = balie.logIn("Jeroen", "piepo");
            if(sessie == null)
            {
                System.out.println("Test Succeeded, Account doesn't exist");
            }
            sessie = balie.logIn(accName, "asdf");
            if(sessie != null)
            {
                System.out.println("Test succeeded");
            }
            System.out.println("End of login\n---------------------------");
        } catch (RemoteException ex) {
            Logger.getLogger(BalieTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
