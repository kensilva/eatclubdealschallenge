package com.eatclub.ken.eatclubdeals.service;

import com.eatclub.ken.eatclubdeals.dto.DealDTO;
import com.eatclub.ken.eatclubdeals.dto.DealsDTO;
import com.eatclub.ken.eatclubdeals.dto.PeakTimeDTO;
import com.eatclub.ken.eatclubdeals.model.Deal;
import com.eatclub.ken.eatclubdeals.model.Restaurant;
import com.eatclub.ken.eatclubdeals.repository.DealsRepository;
import com.eatclub.ken.eatclubdeals.util.TimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealsService {

    private DealsRepository dealsRepository;
    private PeakTimeDTO peakTimeDTO = null;

    public DealsService(DealsRepository dealsRepository) {
        this.dealsRepository = dealsRepository;
    }

    public DealsDTO getDealsWithinTime(final LocalTime time) {
        final List<Deal> deals = dealsRepository.findDealsWithin(time);
        final List<DealDTO> dealDTOList = deals.stream().map(this::mapToDealDTO).collect(Collectors.toUnmodifiableList());
        return new DealsDTO(dealDTOList);
    }

    /**
     * Calculates the peak time ones, then remembers it.
     * QTY is considered when calculating the available deals withing that timeslot.
     * @return peaktime
     */
    public PeakTimeDTO getPeakTime() {
        if (peakTimeDTO == null) {
            peakTimeDTO = calculatePeakTime();
        }
        return peakTimeDTO;
    }

    /**
     * Calculates the peak time
     * @return peak time calculated.
     */
    protected PeakTimeDTO calculatePeakTime() {
        final List<Deal> deals = dealsRepository.getAllDeals(); // sorted asc
        LocalTime peakTimeStart = null;
        LocalTime peakTimeEnd = null;
        int currentPeakTimeScore = 0;
        for (Deal deal : deals) {
            final LocalTime openTime = deal.getOpen();
            final List<Deal> dealsWithinOpenTime = dealsRepository.findDealsWithin(openTime); //sorted asc
            for (Deal withinOpenDealStart : dealsWithinOpenTime) {
                final LocalTime closeTime = withinOpenDealStart.getClose();
                if(openTime.equals(closeTime)){
                    continue;
                }
                int score = calculateScore(openTime,closeTime,dealsWithinOpenTime);
                if (score > currentPeakTimeScore) {
                    currentPeakTimeScore = score;
                    peakTimeStart = openTime;
                    peakTimeEnd = closeTime;
                }
            }
        }

        return new PeakTimeDTO(peakTimeStart, peakTimeEnd);
    }

    /**
     * Calculates score based on the qty where the close is within the open and each deals close time.
     * @param open the opening time
     * @param close the close time
     * @param deals the deals to check. this should be filtered already
     * @return score
     */
    protected int calculateScore(final LocalTime open,LocalTime close, final Collection<Deal> deals){
        int score = 0;
        for (Deal withinOpenDeal : deals) {
            if (TimeUtils.isTimeWithin(close, open, withinOpenDeal.getClose())) {
                score += withinOpenDeal.getQtyLeft();
            }
        }
        return score;
    }

    protected DealDTO mapToDealDTO(final Deal deal) {
        final Restaurant restaurant = deal.getRestaurant();
        final DealDTO dealDTO = new DealDTO();
        dealDTO.setRestaurantObjectId(restaurant.getObjectId());
        dealDTO.setRestaurantName(restaurant.getName());
        dealDTO.setRestaurantAddress1(restaurant.getAddress1());
        dealDTO.setRestaurantSuburb(restaurant.getSuburb());
        dealDTO.setRestaurantOpen(restaurant.getOpen());
        dealDTO.setRestaurantClose(restaurant.getClose());
        dealDTO.setDealObjectId(deal.getObjectId());
        dealDTO.setDiscount(deal.getDiscount());
        dealDTO.setDineIn(deal.isDineIn());
        dealDTO.setLightning(deal.isLightning());
        dealDTO.setQtyLeft(deal.getQtyLeft());
        return dealDTO;
    }
}
