package com.eatclub.ken.eatclubdeals.repository;

import com.eatclub.ken.eatclubdeals.model.Deal;
import com.eatclub.ken.eatclubdeals.model.Restaurant;
import com.eatclub.ken.eatclubdeals.util.TimeUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This version of repo is locally cached deals only based on a json file
 * For Db based, a different repository should be created then configure the app to use that repo either by active profiles or env or whatever is the commonly used in project
 */
@Service
public class JsonDealsRepository implements DealsRepository, InitializingBean {

    private static final String SOURCE_BASE = "https://eccdn.com.au/misc/challengedata.json";
    private static final String ROOT_ELEMENT = "restaurants";

    private List<Deal> deals;

    private ObjectMapper objectMapper;

    public JsonDealsRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Deal> findDealsWithin(LocalTime time) {
        final Predicate<Deal> withinTimeCriteria = deal -> TimeUtils.isTimeWithin(time,deal.getOpen(),deal.getClose());
        return this.deals.stream()
                .filter(withinTimeCriteria)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Deal> getAllDeals(){
        return deals;
    }

    protected void preload() throws IOException {

        final JsonNode jsonNode = objectMapper.readTree(URI.create(SOURCE_BASE).toURL());
        final List<Restaurant> restaurants = objectMapper.treeToValue(jsonNode.get(ROOT_ELEMENT), new TypeReference<List<Restaurant>>() {
        });

        restaurants.forEach(this::assignRestaurantToDeals);

        this.deals = restaurants.stream()
                .map(Restaurant::getDeals)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Deal::getOpen))
                .collect(Collectors.toUnmodifiableList());
    }


    protected void assignRestaurantToDeals(final Restaurant restaurant) {
        restaurant.getDeals().forEach(deal -> resolveDealRestaurantAndTime(restaurant, deal));
    }

    /**
     * Assign restaurant as deals is the current main concern. Will use deals as primary data source
     * This will also fix/rectify and do some assumptions
     * - Assumed the all restaurant will have open/close for simplicity
     * - If deal doesn't have close and/or open, it will fallback to restaurant
     * - If open is before the restaurant open, it should fallback to restaurant
     * - If close beyond restaurant close, it should fallback to restaurant
     *
     * @param deal
     */
    protected void resolveDealRestaurantAndTime(final Restaurant restaurant, final Deal deal) {

        deal.setRestaurant(restaurant);
        if (deal.getOpen() == null || deal.getOpen().isBefore(restaurant.getOpen())) {
            deal.setOpen(restaurant.getOpen());
        }

        if (deal.getClose() == null || deal.getClose().isAfter(restaurant.getClose())) {
            deal.setClose(deal.getRestaurant().getClose());
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        preload();
    }


}
