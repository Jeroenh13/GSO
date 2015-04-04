package Tests;

import org.junit.Test;
import fontys.time.*;
import org.junit.Assert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeroen Hendriks
 */
public class TimeSpanTests {
    
    @Test
    public void TestConstructor()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,4,12,50);
        try
        {
            TimeSpan ts = new TimeSpan(t2,t1);
        }
        catch(Exception e){System.out.println("TestConstructor: " + e.toString());}
        try
        {
            TimeSpan ts1 = new TimeSpan(t1,t2);
            System.out.println("TestConstructor: Begin time is earlier than endtime - Passed");
        }
        catch(Exception e){System.out.println("TestConstructor: " + e.toString());}
    }
    
    @Test
    public void TestGetBegin()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,4,12,50);
        TimeSpan ts = new TimeSpan(t1,t2);
        Assert.assertSame("Begintimes are not equal", ts.getBeginTime(), t1);
    }
    
    @Test
    public void TestGetEnd()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,4,12,50);
        TimeSpan ts = new TimeSpan(t1,t2);
        Assert.assertSame("Endtimes are not equal", ts.getEndTime(), t2);
    }
    
    @Test
    public void TestLength()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,3,12,50);
        TimeSpan ts = new TimeSpan(t1,t2);
        Assert.assertSame("Length is not the expected time",30, ts.length());
    }
    
    @Test
    public void TestSetBegin()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,3,12,50);
        TimeSpan ts = new TimeSpan(t1,t2);
        Time t3 = new Time(1993,10,3,13,40);
        Time t4 = new Time(1993,10,2,12,50);
        try
        {
            ts.setBeginTime(t4);
            System.out.println("TestSetBegin: BeginTime is earlier - Passed");
        }
        catch(Exception e){System.out.println("TestSetBegin: " + e.toString());}
        try
        {
            ts.setBeginTime(t3);
        }
        catch(Exception e){System.out.println("TestSetBegin: " + e.toString());}
    }
    
    @Test
    public void TestMove()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,3,12,40);
        TimeSpan ts = new TimeSpan(t1,t2);
        ts.move(10);
        Assert.assertEquals("Begintimes are not equal", 30, ts.getBeginTime().getMinutes());
        Assert.assertEquals("Endtimes are not equal", 50, ts.getEndTime().getMinutes());
    }
    
    @Test
    public void TestChangeLengthWith()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,3,12,40);
        TimeSpan ts = new TimeSpan(t1,t2);
        Assert.assertEquals("Length is not as expected ", 20, ts.length());
        try
        {
            ts.changeLengthWith(-10);
        }
        catch(Exception e){System.out.println("TestChangeLengthWith: " + e.toString());}
        
        ts.changeLengthWith(10);
        Assert.assertEquals("Length is not as expected ", 30, ts.length());
    }
    
    @Test
    public void TestIsPartOfBeginOnly()
    {
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,3,12,40);
        TimeSpan ts1 = new TimeSpan(t1,t2);
        Time t3 = new Time(1993,10,3,12,10);
        Time t4 = new Time(1993,10,3,12,50);
        TimeSpan ts2 = new TimeSpan(t3,t4);
        Assert.assertTrue("Timespan1 is not part of timespan2", ts1.isPartOf(ts2));
        
        Time t5 = new Time(1993,10,3,12,30);
        Time t6 = new Time(1993,10,3,12,50);
        TimeSpan ts3 = new TimeSpan(t5,t6);
        Assert.assertFalse("Timespan1 is not part of timespan2", ts1.isPartOf(ts3));
        
        Time t7 = new Time(1993,10,3,12,10);
        Time t8 = new Time(1993,10,3,12,20);
        TimeSpan ts4 = new TimeSpan(t7,t8);
        Assert.assertFalse("Timespan1 is not part of timespan2", ts1.isPartOf(ts4));
        
        Time t9 = new Time(1993,10,3,12,22);
        Time t10= new Time(1993,10,3,12,34);
        TimeSpan ts5 = new TimeSpan(t9,t10);
        Assert.assertFalse("Timespan1 is not part of timespan2", ts1.isPartOf(ts5));
    }
   
    @Test
    public void TestUnionWith()
    {
        TimeSpan tsUnion;
        TimeSpan ts2;
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,3,12,40);
        TimeSpan ts1 = new TimeSpan(t1,t2);
        
        //Time with end
        Time t3 = new Time(1993,10,3,12,30);
        Time t4 = new Time(1993,10,3,13,25);
        ts2 = new TimeSpan(t3,t4);
        tsUnion = (TimeSpan) ts1.unionWith(ts2);
        Assert.assertEquals("Not the expected value of 30", 30, tsUnion.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 40", 40, tsUnion.getEndTime().getMinutes());
        
        //Time with begin
        Time t5 = new Time(1993,10,3,12,10);
        Time t6 = new Time(1993,10,3,12,25);
        ts2 = new TimeSpan(t5,t6);
        tsUnion = (TimeSpan) ts1.unionWith(ts2);
        Assert.assertEquals("Not the expected value of 20", 20, tsUnion.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 25", 25, tsUnion.getEndTime().getMinutes());
        
        //Time within
        Time t7 = new Time(1993,10,3,12,25);
        Time t8 = new Time(1993,10,3,12,35);
        ts2 = new TimeSpan(t5,t6);
        tsUnion = (TimeSpan) ts1.unionWith(ts2);
        Assert.assertEquals("Not the expected value of 20", 20, tsUnion.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 25", 25, tsUnion.getEndTime().getMinutes());
        
        //Time before
        Time t9 = new Time(1993,10,3,11,25);
        Time t10 = new Time(1993,10,3,11,35);
        ts2 = new TimeSpan(t9,t10);
        tsUnion = (TimeSpan) ts1.unionWith(ts2);
        Assert.assertNull("TsUnion is niet null", tsUnion);
        
        //Time after
        Time t11 = new Time(1993,10,3,13,25);
        Time t12 = new Time(1993,10,3,13,35);
        ts2 = new TimeSpan(t11,t12);
        tsUnion = (TimeSpan) ts1.unionWith(ts2);
        Assert.assertNull("TsUnion is niet null", tsUnion);
        
        //Time overlapping
        Time t13 = new Time(1993,10,3,12,05);
        Time t14 = new Time(1993,10,3,12,50);
        ts2 = new TimeSpan(t13,t14);
        tsUnion = (TimeSpan) ts1.unionWith(ts2);
        Assert.assertEquals("Not the expected value of 20", 20, tsUnion.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 40", 40, tsUnion.getEndTime().getMinutes());
    }
    
    @Test
    public void TestIntersectionWith()
    {
        TimeSpan tsIntersect;
        TimeSpan ts2;
        Time t1 = new Time(1993,10,3,12,20);
        Time t2 = new Time(1993,10,3,12,40);
        TimeSpan ts1 = new TimeSpan(t1,t2);
        
        //Time with end
        Time t3 = new Time(1993,10,3,12,30);
        Time t4 = new Time(1993,10,3,13,25);
        ts2 = new TimeSpan(t3,t4);
        tsIntersect = (TimeSpan) ts1.intersectionWith(ts2);
        Assert.assertEquals("Not the expected value of 20", 20, tsIntersect.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 25", 25, tsIntersect.getEndTime().getMinutes());
        
        //Time with begin
        Time t5 = new Time(1993,10,3,12,10);
        Time t6 = new Time(1993,10,3,12,25);
        ts2 = new TimeSpan(t5,t6);
        tsIntersect = (TimeSpan) ts1.intersectionWith(ts2);
        Assert.assertEquals("Not the expected value of 10", 10, tsIntersect.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 40", 40, tsIntersect.getEndTime().getMinutes());
        
        //Time within
        Time t7 = new Time(1993,10,3,12,25);
        Time t8 = new Time(1993,10,3,12,35);
        ts2 = new TimeSpan(t5,t6);
        tsIntersect = (TimeSpan) ts1.intersectionWith(ts2);
        Assert.assertEquals("Not the expected value of 10", 10, tsIntersect.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 40", 40, tsIntersect.getEndTime().getMinutes());
        
        //Time before
        Time t9 = new Time(1993,10,3,11,25);
        Time t10 = new Time(1993,10,3,11,35);
        ts2 = new TimeSpan(t9,t10);
        tsIntersect = (TimeSpan) ts1.intersectionWith(ts2);
        Assert.assertNull("TsUnion is niet null", tsIntersect);
        
        //Time after
        Time t11 = new Time(1993,10,3,13,25);
        Time t12 = new Time(1993,10,3,13,35);
        ts2 = new TimeSpan(t11,t12);
        tsIntersect = (TimeSpan) ts1.intersectionWith(ts2);
        Assert.assertNull("TsUnion is niet null", tsIntersect);
        
        //Time overlapping
        Time t13 = new Time(1993,10,3,12,05);
        Time t14 = new Time(1993,10,3,12,50);
        ts2 = new TimeSpan(t13,t14);
        tsIntersect = (TimeSpan) ts1.intersectionWith(ts2);
        Assert.assertEquals("Not the expected value of 05", 05, tsIntersect.getBeginTime().getMinutes());
        Assert.assertEquals("Not the expected value of 50", 50, tsIntersect.getEndTime().getMinutes());
   
    }
}
