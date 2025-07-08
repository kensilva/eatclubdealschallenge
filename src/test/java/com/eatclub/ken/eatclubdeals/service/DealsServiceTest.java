package com.eatclub.ken.eatclubdeals.service;

import com.eatclub.ken.eatclubdeals.dto.PeakTimeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DealsServiceTest {

    @Autowired
    private DealsService dealsService;

    @Test
    public void getPeakTime() {

        final PeakTimeDTO result = dealsService.getPeakTime();

        Assertions.assertEquals(LocalTime.of(18,0),result.getPeakTimeStart());
        Assertions.assertEquals(LocalTime.of(21,0),result.getPeakTimeEnd());
    }

}
