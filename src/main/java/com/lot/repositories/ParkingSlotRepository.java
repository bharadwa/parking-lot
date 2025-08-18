package com.lot.repositories;

import com.lot.models.ParkingSlot;

import java.util.Optional;

public interface ParkingSlotRepository {

    Optional<ParkingSlot> getParkingSlotById(long id);
    ParkingSlot saveParkingSlot(ParkingSlot parkingSlot);
}
