package com.lot.repositories;

import com.lot.models.Vehicle;

import java.util.*;

public class InMemoryVehicleRepository implements VehicleRepository {

    private final Map<Long, Vehicle> vehicleMap=new TreeMap<>();
    private final Map<String,Vehicle> registrationMap=new TreeMap<>();
    private long idCounter = 0L;

    @Override
    public Vehicle save(Vehicle vehicle) {
        if(vehicle.getId()==0){
            idCounter = idCounter + 1;
            vehicle.setId(idCounter);
        } else if (vehicleMap.containsKey(vehicle.getId())) {
            // Update existing vehicle
            vehicle.setUpdatedAt(new Date());
        } else {
            // New vehicle
            vehicle.setUpdatedAt(new Date());
            vehicle.setCreatedAt(new Date());
        }
        registrationMap.put(vehicle.getLicenseNumber(),vehicle);
        vehicleMap.put(vehicle.getId(),vehicle);
        return vehicle;
    }

    @Override
    public Optional<Vehicle> getVehicleById(long id) {
        if((vehicleMap.isEmpty() || !vehicleMap.containsKey(id))) {
            return Optional.empty();
        }
        return Optional.of(vehicleMap.get(id));
    }

    @Override
    public Optional<Vehicle> getVehicleByNumber(String vehicleNumber) {
        if(vehicleMap.isEmpty() || !vehicleMap.containsKey(vehicleNumber)) {
            return Optional.empty();
        }
        return Optional.of(registrationMap.get(vehicleNumber));
    }

    @Override
    public void deleteVehicleById(long id) {
        if(vehicleMap.containsKey(id)) {
            Vehicle vehicle = vehicleMap.get(id);
            registrationMap.remove(vehicle.getLicenseNumber());
            vehicleMap.remove(id);
        }
    }
}
