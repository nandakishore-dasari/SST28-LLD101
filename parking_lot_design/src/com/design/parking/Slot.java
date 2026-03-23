package com.design.parking;

public class Slot {
    private int id;
    private SlotType type;
    private boolean isOccupied;

    public Slot(int id, SlotType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    public int getId() { return id; }
    public SlotType getType() { return type; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
}