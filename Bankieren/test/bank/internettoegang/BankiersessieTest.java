/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import java.rmi.RemoteException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fhict
 */
public class BankiersessieTest {

    public BankiersessieTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isGeldig method, of class Bankiersessie.
     */
    @Test
    public void testIsGeldig() throws RemoteException {
        //Voor het testen is de geldigheidsduur verkort naar 6 seconden, dit moeten weer 10 minuten worden
        try {
            IBank bank = new Bank("TestBank");
            IBankiersessie sessie = new Bankiersessie(1000000, bank);
            
            Boolean b = sessie.isGeldig();
            Assert.assertTrue(b);
            
            Thread.sleep(7000);
            b = sessie.isGeldig();
            Assert.assertFalse(b);
            
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Test of maakOver method, of class Bankiersessie.
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        int bestemming = 0;
        Money bedrag = null;
        Bankiersessie instance = null;
        boolean expResult = false;
        boolean result = instance.maakOver(bestemming, bedrag);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRekening method, of class Bankiersessie.
     */
    @Test
    public void testGetRekening() throws Exception {
        System.out.println("getRekening");
        Bankiersessie instance = null;
        IRekening expResult = null;
        IRekening result = instance.getRekening();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logUit method, of class Bankiersessie.
     */
    @Test
    public void testLogUit() throws Exception {
        System.out.println("logUit");
        Bankiersessie instance = null;
        instance.logUit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
