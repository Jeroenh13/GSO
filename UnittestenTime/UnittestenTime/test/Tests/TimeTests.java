package Tests;

import fontys.time.*;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeroen Hendriks
 */
public class TimeTests {
    
    @Test
    public void TestConstructor(){
        Time t = new Time(1993,10,3,8,20);
        Assert.assertNotNull("Times are not equal",t);
    }
    
    @Test
    public void TestTimeMonth()
    {
        try
        {
            //Wrong Month < 1
            Time time = new Time(1995,0,20,15,20);
        }
        catch(Exception e){System.out.println("TestTimeMonth: " + e.toString());}
        
        try{
            //Wrong Month > 12
            Time time = new Time(1995,13,20,15,20);
        }
        catch(Exception e){System.out.println("TestTimeMonth: " + e.toString());}
    }
    
    @Test
    public void TestTimeDay()
    {
        try{
            //Wrong day >31
            Time time = new Time(1995,2,32,15,20);
        }
        catch(Exception e){System.out.println("TestTimeDay: " + e.toString());}
        
        try{
            //Wrong  day <1
            Time time = new Time(1995,2,0,15,20);
        }
        catch(Exception e){System.out.println("TestTimeDay: " + e.toString());}

    }
    
    @Test
    public void TestTimeHour()
    {
        try{
            //Wrong Hour >24
            Time time = new Time(1995,4,2,25,20);
        }
        catch(Exception e){System.out.println("TestTimeHour: " + e.toString());}
        
        try{
            //Wrong Hour <0
            Time time = new Time(1995,1,2,-1,20);
        }
        catch(Exception e){System.out.println("TestTimeHour: " + e.toString());}
    }
    
    @Test
    public void TestTimeMinute()
    {
        try{
            //Wrong minute >60
            Time time = new Time(1995,1,20,15,80);  
        }
        catch(Exception e){System.out.println("TestTimeMinute: " + e.toString());}
        
        try{
            //Wrong minute <0
            Time time = new Time(1995,1,20,15,-1);
        }
        catch(Exception e){System.out.println("TestTimeMinute: " + e.toString());}
    }
    
    @Test
    public void TestWrongDate()
    {
        try
        {
            //Wrong date
            Time time = new Time(2011,2,31,15,20);
        }
        catch(Exception e){System.out.println("TestWrongDate: " + e.toString());}
    }
    
    @Test
    public void TestgetDayInWeek()
    {
        Time t = new Time(1993,10,3,8,20);
        Assert.assertEquals("These are not the same", "SUN", String.valueOf(t.getDayInWeek()));
    }
    
    @Test
    public void TestGetYear()
    {
        Time t = new Time(1993,10,3,8,20);
        Assert.assertEquals("Not the expected year of 1993", 1993,t.getYear());
    }
    
    @Test
    public void TestGetMonth()
    {
        Time t = new Time(1993,10,3,8,20);
        Assert.assertEquals("Not the expected month 10", 10,t.getMonth());
    }
    
    @Test
    public void TestGetDay()
    {
        Time t = new Time(1993,10,3,8,20);
        Assert.assertEquals("Not the expected day 3", 3,t.getDay());
    }
    
    @Test
    public void TestGetHour()
    {
        Time t = new Time(1993,10,3,8,20);
        Assert.assertEquals("Not the expected hour 8", 8,t.getHours());
    }
    
    @Test
    public void TestGetMinutes()
    {
        Time t = new Time(1993,10,3,8,20);
        Assert.assertEquals("Not the expected minute 20", 20,t.getMinutes());
    }
    
    @Test
    public void TestPlusTime()
    {
        Time t = new Time(1993,10,3,8,20);
        Assert.assertEquals("Not the expected minute 20", 20,t.getMinutes());
        ITime t2 = t.plus(22);
        Assert.assertEquals("Not the expected minute 42", 42,t2.getMinutes());
        ITime t3 = t.plus(-18);
        Assert.assertEquals("Not the expected minute 2", 2,t3.getMinutes());
       
    }
    
    @Test
    public void TestCompareTo()
    {
        Time t = new Time(1993,10,3,8,20);
        Time t2 = new Time(1993,10,3,8,20);
        Assert.assertEquals("Not the expected value", 0,t.compareTo(t2));
        //plus with a plusvalue
        Time t3 = new Time(1993,10,3,8,22);
        Assert.assertEquals("Not the expected value", 1,t.compareTo(t3));
        //plus with a minvalue
        Time t4 = new Time(1993,10,3,8,18);
        Assert.assertEquals("Not the expected value", -1,t.compareTo(t4));
    }
    
    @Test
    public void Testdifference()
    {
        Time t = new Time(1993,10,3,8,20);
        Time t2 = new Time(1993,10,3,8,42);
        Assert.assertEquals("Not the expected difference", 22, t.difference(t2));
    }
}
