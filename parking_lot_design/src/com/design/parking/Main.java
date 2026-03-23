package com.design.parking;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();

        // 1. Create Slots
        Slot s1 = new Slot(1, SlotType.SMALL);
        Slot s2 = new Slot(2, SlotType.MEDIUM);
        Slot s3 = new Slot(3, SlotType.LARGE);

        lot.addSlot(s1);
        lot.addSlot(s2);
        lot.addSlot(s3);

        // 2. Setup Gate 101
        Gate gate1 = new Gate(101);
        gate1.addSlotByDistance(s1); // Nearest
        gate1.addSlotByDistance(s2); 
        gate1.addSlotByDistance(s3); 
        
        // Passing both the ID and the Object
        lot.addGate(101, gate1); 

        // 3. Test Parking
        System.out.println("--- Initial Status ---");
        lot.status();

        Vehicle myBike = new Vehicle("KA-01-1234", SlotType.SMALL);
        System.out.println("\nParking a Bike via Gate 101...");
        Ticket t1 = lot.park(myBike, 10, 101); 

        if (t1 != null) {
            System.out.println("Bike successfully parked!");
            System.out.println("Slot Number: " + t1.getSlotNumber());
            System.out.println("Allocated Type: " + t1.getAllocatedType());
            
            // 4. Test Exit
            System.out.println("\nExiting after 3 hours...");
            double amount = lot.exit(t1, 13); 
            System.out.println("Total Bill Amount: $" + amount);
        }

        System.out.println("\n--- Final Status ---");
        lot.status();
    }
}