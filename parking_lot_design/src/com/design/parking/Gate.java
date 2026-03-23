package com.design.parking;
import java.util.*;

public class Gate {
    private int gateId;
    // Stores slots in order of distance from THIS specific gate
    private List<Slot> nearestSlots;

    public Gate(int id) {
        this.gateId = id;
        this.nearestSlots = new ArrayList<>();
    }

    public void addSlotByDistance(Slot slot) {
        nearestSlots.add(slot);
    }

    public List<Slot> getNearestSlots() { return nearestSlots; }
}