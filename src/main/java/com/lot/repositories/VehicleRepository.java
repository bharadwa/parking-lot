package com.lot.repositories;

import com.lot.models.Vehicle;

import java.util.Optional;

public interface VehicleRepository {


    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> getVehicleById(long id);
    Optional<Vehicle> getVehicleByNumber(String vehicleNumber);
    void deleteVehicleById(long id);
}
