package com.eatclub.ken.eatclubdeals.dto;

import java.time.LocalTime;

public class DealDTO {
    private String restaurantObjectId;
    private String restaurantName;
    private String restaurantAddress1;
    private String restaurantSuburb;
    private LocalTime restaurantOpen;
    private LocalTime restaurantClose;
    private String dealObjectId;
    private int discount;
    private boolean lightning;
    private boolean dineIn;
    private int qtyLeft;

    public String getRestaurantObjectId() {
        return restaurantObjectId;
    }

    public void setRestaurantObjectId(String restaurantObjectId) {
        this.restaurantObjectId = restaurantObjectId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress1() {
        return restaurantAddress1;
    }

    public void setRestaurantAddress1(String restaurantAddress1) {
        this.restaurantAddress1 = restaurantAddress1;
    }

    public String getRestaurantSuburb() {
        return restaurantSuburb;
    }

    public void setRestaurantSuburb(String restaurantSuburb) {
        this.restaurantSuburb = restaurantSuburb;
    }

    public LocalTime getRestaurantOpen() {
        return restaurantOpen;
    }

    public void setRestaurantOpen(LocalTime restaurantOpen) {
        this.restaurantOpen = restaurantOpen;
    }

    public LocalTime getRestaurantClose() {
        return restaurantClose;
    }

    public void setRestaurantClose(LocalTime restaurantClose) {
        this.restaurantClose = restaurantClose;
    }

    public String getDealObjectId() {
        return dealObjectId;
    }

    public void setDealObjectId(String dealObjectId) {
        this.dealObjectId = dealObjectId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isLightning() {
        return lightning;
    }

    public void setLightning(boolean lightning) {
        this.lightning = lightning;
    }

    public int getQtyLeft() {
        return qtyLeft;
    }

    public void setQtyLeft(int qtyLeft) {
        this.qtyLeft = qtyLeft;
    }

    public boolean isDineIn() {
        return dineIn;
    }

    public void setDineIn(boolean dineIn) {
        this.dineIn = dineIn;
    }
}
