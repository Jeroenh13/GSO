/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import fontys.time.Contact;
import fontys.time.*;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Jeroen Hendriks
 */
public class AppointmentTests {
    @Test
    public void TestContstructor()
    {
        Time t1 = new Time(2015,3,17,17,00);
        Time t2 = new Time(2015,3,17,18,00);
        TimeSpan ts = new TimeSpan(t1,t2);
        Appointment a1 = new Appointment("Meeting",ts);
        Assert.assertNotNull(a1);
        
        a1 = new Appointment("",ts);
        Assert.assertNotNull(a1);
        
        a1 = new Appointment(null,ts);
        Assert.assertNotNull(a1);
        
        try
        {
            a1 = new Appointment("Meeting",null);
        }
        catch(Exception e){System.out.println("TimeSpan cannot be null");}
    }
    
    @Test
    public void TestGetSubject()
    {
        Time t1 = new Time(2015,3,17,17,00);
        Time t2 = new Time(2015,3,17,18,00);
        TimeSpan ts = new TimeSpan(t1,t2);
        Appointment a1 = new Appointment("Meeting",ts);
        Assert.assertEquals("Subject is not the same","Meeting", a1.getSubject());
    }
    
    @Test
    public void TestGetPeriod()
    {
        Time t1 = new Time(2015,3,17,17,00);
        Time t2 = new Time(2015,3,17,18,00);
        TimeSpan ts = new TimeSpan(t1,t2);
        Appointment a1 = new Appointment("Meeting",ts);
        Assert.assertEquals("timespan is not the same",ts, a1.getTimeSpan());
    }
    
    @Test
    public void TestAddAndGetInvitees()
    {
        Time t1 = new Time(2015,3,17,17,00);
        Time t2 = new Time(2015,3,17,18,00);
        TimeSpan ts = new TimeSpan(t1,t2);
        Appointment a1 = new Appointment("Meeting",ts);
        
        Contact c1 = new Contact("Jeroen");
        Contact c2 = new Contact("Karlijn");
        Contact c3 = new Contact("Nick");
        Contact c4 = new Contact("Björn");
        Contact c5 = new Contact("Iris");
        
        try
        {
            a1.addContact(c1);
            a1.addContact(c2);
            a1.addContact(c3);
            a1.addContact(c4);
            a1.addContact(c5);
        }
        catch(Exception e){Assert.fail(e.toString());}
        
        Iterator<Contact> contacts = a1.invitees();
        int count = 0;
        while(contacts.hasNext())
        {
            Contact c = contacts.next();
            if("Jeroen".equals(c.getName()) || "Karlijn".equals(c.getName()) || "Nick".equals(c.getName()) || "Björn".equals(c.getName()) || "Iris".equals(c.getName()))
            {
                count++;
            }
        }
        
        Assert.assertEquals("Amount of contacts is not correct",5, count);
    }
    
    @Test
    public void TestAddAndRemoveContacts()
    {
        Time t1 = new Time(2015,3,17,17,00);
        Time t2 = new Time(2015,3,17,18,00);
        TimeSpan ts = new TimeSpan(t1,t2);
        Appointment a1 = new Appointment("Meeting",ts);
        
        Contact c1 = new Contact("Jeroen");
        Contact c2 = new Contact("Karlijn");
        Contact c3 = new Contact("Nick");
        Contact c4 = new Contact("Björn");
        Contact c5 = new Contact("Iris");
        
        try
        {
            a1.addContact(c1);
            a1.addContact(c2);
            a1.addContact(c3);
            a1.addContact(c4);
            a1.addContact(c5);
        }
        catch(Exception e){Assert.fail(e.toString());}
        
        Time t3 = new Time(2015,3,17,17,30);
        Time t4 = new Time(2015,3,17,18,30);
        TimeSpan ts2 = new TimeSpan(t3,t4);
        Appointment a2 = new Appointment("Training",ts2);
        try
        {
            a2.addContact(c2);
        }
        catch(Exception e){System.out.println("Contact has overlapping appointment");}
        
        //Removing of a contact
        a1.removeContact(c2);
        Iterator<Contact> contacts = a1.invitees();
        int count = 0;
        while(contacts.hasNext())
        {
            Contact c = contacts.next();
            if("Jeroen".equals(c.getName()) || "Nick".equals(c.getName()) || "Björn".equals(c.getName()) || "Iris".equals(c.getName()))
            {
                count++;
            }
        }
        
        Assert.assertEquals("Amount of contacts is not correct",4, count);
        
    }
}
