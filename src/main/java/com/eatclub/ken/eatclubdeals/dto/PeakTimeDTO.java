package com.eatclub.ken.eatclubdeals.dto;

import java.time.LocalTime;

public class PeakTimeDTO {
    private LocalTime peakTimeStart;
    private LocalTime peakTimeEnd;

    public PeakTimeDTO(){

    }

    public PeakTimeDTO(final LocalTime peakTimeStart,final LocalTime peakTimeEnd){
        this.peakTimeStart = peakTimeStart;
        this.peakTimeEnd = peakTimeEnd;
    }

    public LocalTime getPeakTimeStart() {
        return peakTimeStart;
    }

    public void setPeakTimeStart(LocalTime peakTimeStart) {
        this.peakTimeStart = peakTimeStart;
    }

    public LocalTime getPeakTimeEnd() {
        return peakTimeEnd;
    }

    public void setPeakTimeEnd(LocalTime peakTimeEnd) {
        this.peakTimeEnd = peakTimeEnd;
    }
}
