/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fhict
 */
public class BankTest {

    public BankTest() {
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
     * Test of openRekening method, of class Bank.
     */
    @Test
    public void testOpenRekening() {
        // Test lege name
        try {
            Bank testbank = new Bank("RaboBank");
            String name = "";
            String city = "Eindhoven";

            int expectednumber = -1;
            int actualnumber = testbank.openRekening(name, city);

            Assert.assertEquals(expectednumber, actualnumber);

        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }

        // Test lege city
        try {
            Bank testbank = new Bank("RaboBank");
            String name = "Naam";
            String city = "";

            int expectednumber = -1;
            int actualnumber = testbank.openRekening(name, city);

            Assert.assertEquals(expectednumber, actualnumber);

        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }

        // Test lege name en city
        try {
            Bank testbank = new Bank("RaboBank");
            String name = "";
            String city = "";

            int expectednumber = -1;
            int actualnumber = testbank.openRekening(name, city);

            Assert.assertEquals(expectednumber, actualnumber);

        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }

        // Test nieuwe klant
        try {
            Bank testbank = new Bank("RaboBank");
            String name = "Bob";
            String city = "Eindhoven";

            int expectednumber = 100000000;
            int actualnumber = testbank.openRekening(name, city);

            Assert.assertEquals(expectednumber, actualnumber);

        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }

        // Test 2 nieuwe klanten
        try {
            Bank testbank = new Bank("RaboBank");
            String name = "Bob";
            String city = "Eindhoven";

            int expectednumber = 100000000;
            int actualnumber = testbank.openRekening(name, city);

            String name2 = "Henk";
            String city2 = "Rotterdam";

            int expectednumber2 = 100000001;
            int actualnumber2 = testbank.openRekening(name2, city2);

            Assert.assertEquals(expectednumber, actualnumber);
            Assert.assertEquals(expectednumber2, actualnumber2);

        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test of getRekening method, of class Bank.
     */
    @Test
    public void testGetRekening() {
        //Eenmalige test
        try {
            Bank testbank = new Bank("RaboBank");
            testbank.openRekening("Henk", "Eindhoven");

            IRekening rekening = testbank.getRekening(100000000);
            Assert.assertEquals(rekening.getNr(), 100000000);
            Assert.assertEquals(rekening.getEigenaar().getNaam(), "Henk");
            Assert.assertEquals(rekening.getEigenaar().getPlaats(), "Eindhoven");
        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }

        //Drievoudige test
        try {
            Bank testbank = new Bank("RaboBank");
            testbank.openRekening("Henk", "Eindhoven");
            testbank.openRekening("Bob", "Rotterdam");
            testbank.openRekening("Sjaak", "Langeraar");

            IRekening rekeningHenk = testbank.getRekening(100000000);
            IRekening rekeningBob = testbank.getRekening(100000001);
            IRekening rekeningSjaak = testbank.getRekening(100000002);

            Assert.assertEquals(rekeningHenk.getNr(), 100000000);
            Assert.assertEquals(rekeningHenk.getEigenaar().getNaam(), "Henk");
            Assert.assertEquals(rekeningHenk.getEigenaar().getPlaats(), "Eindhoven");

            Assert.assertEquals(rekeningBob.getNr(), 100000001);
            Assert.assertEquals(rekeningBob.getEigenaar().getNaam(), "Bob");
            Assert.assertEquals(rekeningBob.getEigenaar().getPlaats(), "Rotterdam");

            Assert.assertEquals(rekeningSjaak.getNr(), 100000002);
            Assert.assertEquals(rekeningSjaak.getEigenaar().getNaam(), "Sjaak");
            Assert.assertEquals(rekeningSjaak.getEigenaar().getPlaats(), "Langeraar");
        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test
    public void testMaakOver() {
        // "Gewone" test
        try {
            Boolean maakOverBool;
            Bank testbank = new Bank("RaboBank");
            testbank.openRekening("Henk", "Eindhoven");
            testbank.openRekening("Bob", "Rotterdam");

            IRekening rekeningHenk = testbank.getRekening(100000000);
            IRekening rekeningBob = testbank.getRekening(100000001);

            Money overmaakBedrag = new Money(100, Money.EURO);
            maakOverBool = testbank.maakOver(rekeningHenk.getNr(), rekeningBob.getNr(), overmaakBedrag);

            Money saldoHenk = rekeningHenk.getSaldo();
            Money saldoBob = rekeningBob.getSaldo();

            Assert.assertEquals(saldoHenk.getCents(), 900);
            Assert.assertEquals(saldoBob.getCents(), 1100);

        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }

        // Negatief bedrag
        try {
            Boolean maakOverBool;
            Bank testbank = new Bank("RaboBank");
            testbank.openRekening("Henk", "Eindhoven");
            testbank.openRekening("Bob", "Rotterdam");

            IRekening rekeningHenk = testbank.getRekening(100000000);
            IRekening rekeningBob = testbank.getRekening(100000001);

            Money overmaakBedrag = new Money(-100, Money.EURO);
            maakOverBool = testbank.maakOver(rekeningHenk.getNr(), rekeningBob.getNr(), overmaakBedrag);
            Assert.fail();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Overmaken naar eigen rekening
        try {
            Boolean maakOverBool;
            Bank testbank = new Bank("RaboBank");
            testbank.openRekening("Henk", "Eindhoven");

            IRekening rekeningHenk = testbank.getRekening(100000000);

            Money overmaakBedrag = new Money(100, Money.EURO);
            maakOverBool = testbank.maakOver(rekeningHenk.getNr(), rekeningHenk.getNr(), overmaakBedrag);
            Assert.fail();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Overmaken naar niet bestaande rekening
        try {
            Boolean maakOverBool;
            Bank testbank = new Bank("RaboBank");
            testbank.openRekening("Henk", "Eindhoven");

            IRekening rekeningHenk = testbank.getRekening(100000000);
            Money overmaakBedrag = new Money(100, Money.EURO);

            maakOverBool = testbank.maakOver(rekeningHenk.getNr(), 1000000002, overmaakBedrag);
            Assert.fail();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Overmaken van te groot bedrag
        try {
            Boolean maakOverBool;
            Bank testbank = new Bank("RaboBank");
            testbank.openRekening("Henk", "Eindhoven");
            testbank.openRekening("Bob", "Rotterdam");

            IRekening rekeningHenk = testbank.getRekening(100000000);
            IRekening rekeningBob = testbank.getRekening(100000001);

            Money overmaakBedrag = new Money(500000, Money.EURO);
            maakOverBool = testbank.maakOver(rekeningHenk.getNr(), rekeningBob.getNr(), overmaakBedrag);
            if (maakOverBool == true) {
                Assert.fail("Can't transfer money you don't have");
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName() {
        try {
            Bank testbank = new Bank("RaboBank");
            Assert.assertEquals(testbank.getName(), "RaboBank");
        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

}
