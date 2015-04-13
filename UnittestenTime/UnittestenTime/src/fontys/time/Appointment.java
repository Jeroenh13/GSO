/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeroen Hendriks
 */
public class Appointment {
    
    private String subject;
    private ITimeSpan timespan;
    private ArrayList<Contact> contacts;
    
    /**
     * Creates a new Appointment with the subject and a TimeSpan
     * @param subject The subject of this appointment, the subject can be an empty string
     * @param timeSpan The timespan of this appointment
     */
    public Appointment(String subject, ITimeSpan timeSpan)
    {
        if(subject==null){this.subject = "";}
        else{this.subject = subject;}
        
        if(timeSpan==null)
        {
            throw new IllegalArgumentException("Timespan cannot be null");
        }
        else{timespan = timeSpan;}
        
        contacts = new ArrayList<>();
    }
    
    /**
     * Gets the subject of this appointment
     * @return the subject of this appointment
     */
    public String getSubject()
    {
        return subject;
    }
    
    /**
     * Gets the timespan of this appointment
     * @return the timespan of this appointment
     */
    public ITimeSpan getTimeSpan()
    {
        return timespan;
    }
    
    /**
     * Gets all the contacts that are invited to this appointment
     * @return an iterator with all invited contacts
     */
    public Iterator<Contact> invitees()
    {
        return contacts.iterator();
    }
    
    /**
     * Adds a contact to this appointment
     * this is only possible if this appointment doesn't conflict the contacts agenda
     * @param c the contact which needs to be added to this appointment
     * @return this return true if the contact was successfully added, otherwise it returns false
     */
    public boolean addContact(Contact c)
    {
        if(!c.addAppointment(this))
        {
            return false;
        }
        contacts.add(c);
        return true;
    }
    
    /**
     * Removes a contact from this appointment
     * @param c The contact to be removed
     */
    public void removeContact(Contact c)
    {
        contacts.remove(c);
    }
}
