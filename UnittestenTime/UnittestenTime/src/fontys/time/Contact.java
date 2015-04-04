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
public class Contact {
    private String name;
    private ArrayList<Appointment> agenda;
    
    /**
     * Creates the contact with the given name
     * @param name the name of this contact
     */
    public Contact(String name)
    {
        this.name = name;
        agenda = new ArrayList<>();
    }
    
    /**
     * Gets the name of this contact
     * @return the name of this contact
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Adds the appointment to the agenda, but only if it doesn't overlap with another appointment
     * @param a the appointment that needs to be added
     * @return 
     */
    protected boolean addAppointment(Appointment a)
    {
        boolean succes = true;
        if(agenda==null)
        {
            agenda.add(a);
            succes = true;
        }
        else
        {
            for (Appointment app : agenda) {
                if(a.getTimeSpan().intersectionWith(app.getTimeSpan())!=null)
                {
                    succes = false;
                }
            }
            if(succes)
            {
                agenda.add(a);
                succes = true;
            }
        }
        return succes;
    }
    
    /**
     * Removes the appointment from the agenda
     * @param a the appointment to be removed
     */
    protected void removeAppointment(Appointment a)
    {
        agenda.remove(a);
    }
    
    /**
     * Gets the iterator of this agenda
     * @return the iterator of the agenda
     */
    public Iterator<Appointment> appointments()
    {
        return agenda.iterator();
    }
}
