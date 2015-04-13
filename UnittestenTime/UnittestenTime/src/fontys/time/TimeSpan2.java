/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author Jeroen Hendriks
 */
public class TimeSpan2 implements ITimeSpan {

    /* class invariant: 
     * A stretch of time with a begin time and end time.
     * The end time is always later then the begin time; the length of the time span is
     * always positive
     * 
     */
    private ITime bt;
    private long duration;
    
    /**
     * 
     * @param bt must be earlier than et
     * @param et 
     */
    public TimeSpan2(ITime bt, ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        duration=bt.difference(et);
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return bt.plus((int) duration);
    }

    @Override
    public int length() {
        return (int) duration;
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(bt.plus((int)duration)) >= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + bt + duration);
        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) <= 0) {
            throw new IllegalArgumentException("end time "
                    + bt + duration + " must be later then begin time " + bt);
        }

        duration = bt.difference(endTime);
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }
        
        duration = duration + minutes;
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) <= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) >= 0);
        //The check needed to be the other way around
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        ITime et = bt.plus((int)duration);
       //checks the other way around
       if (bt.compareTo(timeSpan.getEndTime()) < 0 || et.compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }
        
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan2(begintime, endtime);

    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {

        ITime et = bt.plus((int)duration);
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) < 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }
        
        //If timespan is before this timespan
        if(bt.compareTo(timeSpan.getEndTime())<0 && bt.compareTo(timeSpan.getBeginTime())<0)
        {
                return null;
        }
        //If timespan is after this timespan
        if(et.compareTo(timeSpan.getEndTime())>0 && et.compareTo(timeSpan.getBeginTime())>0)
        {
           return null; 
        }
        //Check needed to be the other way around
        if (begintime.compareTo(endtime) <= 0) {
            
            return null;
        }

        return new TimeSpan2(begintime, endtime);
    }
}
