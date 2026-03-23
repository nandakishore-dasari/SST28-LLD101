package com.design.parking;

public class Vehicle {
    private String licensePlate;
    private SlotType type; // Original type of vehicle

    public Vehicle(String licensePlate, SlotType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }
    public SlotType getType() { return type; }
}