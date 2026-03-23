package com.design.parking;

public class Ticket {
    private String vehiclePlate;
    private int slotNumber;
    private SlotType allocatedType;
    private long entryTime;

    public Ticket(String plate, int slotNum, SlotType type, long time) {
        this.vehiclePlate = plate;
        this.slotNumber = slotNum;
        this.allocatedType = type;
        this.entryTime = time;
    }
    // Getters
    public long getEntryTime() { return entryTime; }
    public SlotType getAllocatedType() { return allocatedType; }
    public int getSlotNumber() { return slotNumber; }
}