package com.eatclub.ken.eatclubdeals.controller;

import com.eatclub.ken.eatclubdeals.EatClubContants;
import com.eatclub.ken.eatclubdeals.dto.DealsDTO;
import com.eatclub.ken.eatclubdeals.dto.PeakTimeDTO;
import com.eatclub.ken.eatclubdeals.service.DealsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/deals")
public class DealsController {

    private DealsService dealsService;

    public DealsController(final DealsService dealsService){
        this.dealsService = dealsService;
    }

    @GetMapping
    public DealsDTO fetchDeals(@RequestParam @DateTimeFormat(pattern = EatClubContants.LOCAL_TIME_FORMAT) final LocalTime timeOfDay) {
        return dealsService.getDealsWithinTime(timeOfDay);
    }

    @GetMapping("/peak")
    public PeakTimeDTO getPeakTime(){
        return dealsService.getPeakTime();
    }
}
