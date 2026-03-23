package com.design.parking;

public enum SlotType {
    SMALL(10), MEDIUM(20), LARGE(50);

    private int pricePerHour;
    SlotType(int price) { this.pricePerHour = price; }
    public int getPricePerHour() { return pricePerHour; }
}