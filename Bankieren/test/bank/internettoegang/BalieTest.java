/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import fontys.observer.RemotePropertyListener;
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
    
    public BalieTest() {
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
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekening() {
        System.out.println("openRekening");
        String naam = "";
        String plaats = "";
        String wachtwoord = "";
        Balie instance = null;
        String expResult = "";
        String result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn() throws Exception {
        System.out.println("logIn");
        String accountnaam = "";
        String wachtwoord = "";
        Balie instance = null;
        IBankiersessie expResult = null;
        IBankiersessie result = instance.logIn(accountnaam, wachtwoord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inform method, of class Balie.
     */
    @Test
    public void testInform() {
        System.out.println("inform");
        int to = 0;
        Balie instance = null;
        instance.inform(to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addListener method, of class Balie.
     */
    @Test
    public void testAddListener() throws Exception {
        System.out.println("addListener");
        RemotePropertyListener listener = null;
        String property = "";
        Balie instance = null;
        instance.addListener(listener, property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeListener method, of class Balie.
     */
    @Test
    public void testRemoveListener() throws Exception {
        System.out.println("removeListener");
        RemotePropertyListener listener = null;
        String property = "";
        Balie instance = null;
        instance.removeListener(listener, property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
