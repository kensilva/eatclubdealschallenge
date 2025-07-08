package com.eatclub.ken.eatclubdeals.repository;

import com.eatclub.ken.eatclubdeals.model.Deal;

import java.time.LocalTime;
import java.util.List;

/**
 * Interface for fetching deals. Implementor decides where the source of truth.
 */
public interface DealsRepository {

    /**
     * Fetch all deals matching the @param localTime
     *
     * @param localTime local time the deals to match
     * @return all deals matching this time.
     */
    List<Deal> findDealsWithin(final LocalTime localTime);

    /**
     * Returns all deals sorted by opening time
     * @return all deals
     */
    List<Deal> getAllDeals();
}

