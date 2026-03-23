package com.design.parking;
import java.util.*;

public class ParkingLot {
    private Map<Integer, Gate> gates;
    private List<Slot> allSlots;

    public ParkingLot() {
        this.gates = new HashMap<>();
        this.allSlots = new ArrayList<>();
    }

    // Explicitly mapping the ID to the Gate object
    public void addGate(int id, Gate gate) { 
        gates.put(id, gate); 
    }

    public void addSlot(Slot slot) { 
        allSlots.add(slot); 
    }

    private List<SlotType> getCompatibleTypes(SlotType vehicleType) {
        List<SlotType> compatible = new ArrayList<>();
        if (vehicleType == SlotType.SMALL) {
            compatible.add(SlotType.SMALL);
            compatible.add(SlotType.MEDIUM);
            compatible.add(SlotType.LARGE);
        } else if (vehicleType == SlotType.MEDIUM) {
            compatible.add(SlotType.MEDIUM);
            compatible.add(SlotType.LARGE);
        } else {
            compatible.add(SlotType.LARGE);
        }
        return compatible;
    }

    public Ticket park(Vehicle vehicle, long entryTime, int gateId) {
        Gate gate = gates.get(gateId);
        
        // Safety check to avoid the NullPointerException
        if (gate == null) {
            System.out.println("Error: Gate " + gateId + " not found!");
            return null;
        }

        List<SlotType> allowedTypes = getCompatibleTypes(vehicle.getType());
        List<Slot> gateSlots = gate.getNearestSlots();

        for (int i = 0; i < gateSlots.size(); i++) {
            Slot currentSlot = gateSlots.get(i);
            if (!currentSlot.isOccupied()) {
                for (int j = 0; j < allowedTypes.size(); j++) {
                    if (currentSlot.getType() == allowedTypes.get(j)) {
                        currentSlot.setOccupied(true);
                        return new Ticket(vehicle.getType().toString(), currentSlot.getId(), currentSlot.getType(), entryTime);
                    }
                }
            }
        }
        return null;
    }

    public double exit(Ticket ticket, long exitTime) {
        long duration = exitTime - ticket.getEntryTime();
        if (duration <= 0) duration = 1; 

        double rate = ticket.getAllocatedType().getPricePerHour();
        
        for (int i = 0; i < allSlots.size(); i++) {
            if (allSlots.get(i).getId() == ticket.getSlotNumber()) {
                allSlots.get(i).setOccupied(false);
                break;
            }
        }
        return duration * rate;
    }

    public void status() {
        int s = 0, m = 0, l = 0;
        for (int i = 0; i < allSlots.size(); i++) {
            if (!allSlots.get(i).isOccupied()) {
                if (allSlots.get(i).getType() == SlotType.SMALL) s++;
                else if (allSlots.get(i).getType() == SlotType.MEDIUM) m++;
                else l++;
            }
        }
        System.out.println("Available - Small: " + s + ", Medium: " + m + ", Large: " + l);
    }
}