package com.eatclub.ken.eatclubdeals.util;

import java.time.LocalTime;

public class TimeUtils {

    private TimeUtils(){
        //avoid instantiation
    }

    /**
     * Check if specified @param timeToCheck is inclusive withing the open and close time
     * @param timeToCheck the time to test
     * @param open the opening time inclusive
     * @param close the closing time inclusive
     * @return true if within, otherwise false
     */
    public static boolean isTimeWithin(final LocalTime timeToCheck, final LocalTime open, final LocalTime close){
        return (timeToCheck.equals(open) || timeToCheck.isAfter(open))
                && (timeToCheck.equals(close) || timeToCheck.isBefore(close));
    }
}
