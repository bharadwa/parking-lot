package com.lot.repositories;

import com.lot.models.ParkingSlot;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class InMemoryParkingSlotRepository implements ParkingSlotRepository {

    private final Map<Long, ParkingSlot> parkingSlots = new TreeMap<>();

    private long idCounter = 0;

    @Override
    public Optional<ParkingSlot> getParkingSlotById(long id) {
        if (parkingSlots.isEmpty() || !parkingSlots.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(parkingSlots.get(id));
    }


    @Override
    public ParkingSlot saveParkingSlot(ParkingSlot parkingSlot) {
        if(parkingSlot.getId()==0) {
            idCounter = idCounter + 1;
            parkingSlot.setId(idCounter);
        } else if (parkingSlots.containsKey(parkingSlot.getId())) {
            // Update existing parking slot
            parkingSlot.setUpdatedAt(new java.util.Date());
        } else {
            // New parking slot
            parkingSlot.setCreatedAt(new java.util.Date());
            parkingSlot.setUpdatedAt(new java.util.Date());
        }
        parkingSlots.put(parkingSlot.getId(), parkingSlot);
        return parkingSlot;
    }
}
