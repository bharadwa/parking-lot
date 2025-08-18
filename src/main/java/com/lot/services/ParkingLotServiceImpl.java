package com.lot.services;

import com.lot.enums.SlotStatus;
import com.lot.enums.VehicleType;
import com.lot.exceptions.InvalidParkingLotException;
import com.lot.models.ParkingFloor;
import com.lot.models.ParkingLot;
import com.lot.repositories.ParkingLotRepository;

import java.util.*;

public class ParkingLotServiceImpl implements  ParkingLotService{

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloors, List<VehicleType> vehicleTypes) throws InvalidParkingLotException, InvalidParkingLotException {
        ParkingLot parkingLot =parkingLotRepository.getParkingLotById(parkingLotId)
                .orElseThrow(() -> new InvalidParkingLotException("Parking lot not found with id: " + parkingLotId));

        Map<ParkingFloor, Map<String, Integer>> parkingLotCapacity = new LinkedHashMap<>();

        if(parkingFloors==null||parkingFloors.isEmpty()) {
            for (ParkingFloor parkingFloor : parkingLot.getFloors()) {
                    Map<String, Integer> capacityMap = getCapacityForVehicleTypes(parkingFloor, vehicleTypes);
                    parkingLotCapacity.put(parkingFloor, capacityMap);
            }
        }else {
            for (ParkingFloor parkingFloor : parkingLot.getFloors()) {
                if (parkingFloors.contains(parkingFloor.getId())) {
                    Map<String, Integer> capacityMap = getCapacityForVehicleTypes(parkingFloor, vehicleTypes);
                    parkingLotCapacity.put(parkingFloor, capacityMap);
                }
            }
        }

        return parkingLotCapacity;
    }

    private Map<String, Integer> getCapacityForVehicleTypes(ParkingFloor parkingFloor, List<VehicleType> vehicleTypes) {
        Map<String, Integer> capacityMap = new HashMap<>();
        if(vehicleTypes == null || vehicleTypes.isEmpty()) {
            vehicleTypes= Arrays.asList(VehicleType.values()); // Return empty map if no vehicle types are provided
        }
        for (VehicleType vehicleType : vehicleTypes) {
            int count = (int)parkingFloor.getParkingSlotList().stream()
                    .filter(slot -> slot.getSupportedVehicleType()==vehicleType&&slot.getStatus()== SlotStatus.AVAILABLE)
                    .count();
            capacityMap.put(vehicleType.name(), count);
        }

        return capacityMap;

    }
}
