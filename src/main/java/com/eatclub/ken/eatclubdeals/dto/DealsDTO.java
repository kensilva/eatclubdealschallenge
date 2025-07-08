package com.eatclub.ken.eatclubdeals.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DealsDTO {

    private final List<DealDTO> deals;

    public DealsDTO(final List<DealDTO> deals) {
        this.deals = Objects.requireNonNullElseGet(deals, Collections::emptyList);
    }

    public List<DealDTO> getDeals() {
        return deals;
    }

}
