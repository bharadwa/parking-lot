package com.lot.strategies;

import com.lot.enums.VehicleType;
import com.lot.models.ParkingLot;
import com.lot.models.ParkingSlot;

import java.util.Optional;

public interface SpotAssignmentStrategy {

    Optional<ParkingSlot> getParkingSpot(ParkingLot parkingLot, VehicleType vehicleType);

}
