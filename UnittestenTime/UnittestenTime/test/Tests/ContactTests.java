/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import org.junit.Assert;
import org.junit.Test;
import fontys.time.*;
import java.util.Iterator;

/**
 *
 * @author Jeroen Hendriks
 */
public class ContactTests {
    @Test
    public void TestContstructor()
    {
        Contact c1 = new Contact("Jeroen");
        Contact c2 = new Contact("Karlijn");
        
        Assert.assertNotNull("Object is not initialized", c1);
        Assert.assertNotNull("Object is not initialized", c2);
    }
    
    @Test
    public void TestGetNaam()
    {
        Contact c1 = new Contact("Jeroen");
        Contact c2 = new Contact("Karlijn");
        
        Assert.assertEquals("Name is not as expected","Jeroen", c1.getName());
        Assert.assertEquals("Name is not as expected","Karlijn", c2.getName());
    }
    
    @Test
    public void TestAddAppointment()
    {
        Time t1 = new Time(2015,3,17,17,00);
        Time t2 = new Time(2015,3,17,18,00);
        TimeSpan ts = new TimeSpan(t1,t2);
        Appointment a1 = new Appointment("Meeting",ts);
        
        Contact c1 = new Contact("Jeroen");
        
        Assert.assertTrue(a1.addContact(c1));
               
        Iterator it = a1.invitees();
        int i = 0;
        while(it.hasNext()) {
            i++;
            it.next();
        }
        Assert.assertEquals(1, i);
        
        //Adding an overlapping timespan
        Time t3 = new Time(2015,3,17,17,30);
        Time t4 = new Time(2015,3,17,18,30);
        TimeSpan ts2 = new TimeSpan(t1,t2);
        Appointment a2 = new Appointment("Meeting",ts);
        
        Assert.assertFalse(a2.addContact(c1));
        it = a2.invitees();
        i = 0;
        while(it.hasNext()) {
            i++;
            it.next();
        }
        Assert.assertEquals(0, i);
    }
}
