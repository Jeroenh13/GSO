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
import fontys.util.NumberDoesntExistException;
import java.rmi.RemoteException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        IBank bank = new Bank("TestBank");
        bank.openRekening("Jeroen", "Wijchen");
        bank.openRekening("Lisa", "Eindhoven");
        IBankiersessie sessie = new Bankiersessie(100000000, bank);
        try {
            //Werkende rekening
            sessie.maakOver(100000001, new Money(20, Money.EURO));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        try {
            //Negatief money
            sessie.maakOver(100000001, new Money(-20, Money.EURO));
        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            //Zelfde rekeningnummer
            sessie.maakOver(100000000, new Money(20, Money.EURO));
        } catch (RuntimeException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            //Niet bestaand rekeningnummer
            sessie.maakOver(100000003, new Money(20, Money.EURO));
        } catch (NumberDoesntExistException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Test of getRekening method, of class Bankiersessie.
     */
    @Test
    public void testGetRekening() throws Exception {
        try {
            IBank bank = new Bank("TestBank");
            bank.openRekening("Jeroen", "Wijchen");
            IRekening rek = bank.getRekening(100000000);
            Assert.assertEquals(100000000, rek.getNr());
            Assert.assertEquals("Jeroen", rek.getEigenaar().getNaam());
            Assert.assertEquals("Wijchen", rek.getEigenaar().getPlaats());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Test of logUit method, of class Bankiersessie.
     */
    @Test
    public void testLogUit() throws Exception {
    }

}
