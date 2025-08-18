package com.lot.strategies;

import com.lot.enums.ParkingFloorStatus;
import com.lot.enums.SlotStatus;
import com.lot.enums.VehicleType;
import com.lot.models.ParkingFloor;
import com.lot.models.ParkingLot;
import com.lot.models.ParkingSlot;

import java.util.Optional;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy {


    // When a vehicle arrives, the system should assign it to the floor with the least number of available spots for that vehicle type.
    // If there are multiple floors with same number of available spots, the system should assign the vehicle to the floor with the lowest floor number.
    // If a floor is operational, then only it should be considered, otherwise the system should ignore that floor.
    // Once a floor has been selected, the system should assign the vehicle to the nearest available spot of that vehicle type on that floor.
    // If there are no available spots on any floor, the system should not issue a ticket.


    @Override
    public Optional<ParkingSlot> getParkingSpot(ParkingLot parkingLot, VehicleType vehicleType) {


        if (parkingLot == null || parkingLot.getFloors() == null || parkingLot.getFloors().isEmpty()) {
            return Optional.empty();
        }

        int minimumAvailableSpots = Integer.MAX_VALUE;
        ParkingFloor selectedFloor = null;

        for (ParkingFloor parkingFloor : parkingLot.getFloors()) {
            if (parkingFloor.getStatus() == ParkingFloorStatus.OPERATIONAL) {
                int availableSpots = (int) parkingFloor.getParkingSlotList().stream().filter(
                        parkingSlot -> parkingSlot.getSupportedVehicleType() == vehicleType && parkingSlot.getStatus() == SlotStatus.AVAILABLE
                ).count();
                if (availableSpots < minimumAvailableSpots && (selectedFloor == null || parkingFloor.getFloorNumber() < selectedFloor.getFloorNumber())) {
                    minimumAvailableSpots = availableSpots;
                    selectedFloor = parkingFloor;
                }
            }
        }

        if (selectedFloor == null)
            return Optional.empty();
        // Find the nearest available slot on the selected floor
        return selectedFloor.getParkingSlotList().stream().
                filter(parkingSlot -> parkingSlot.getSupportedVehicleType() == vehicleType && parkingSlot.getStatus() == SlotStatus.AVAILABLE)
                .findFirst();

    }
}
