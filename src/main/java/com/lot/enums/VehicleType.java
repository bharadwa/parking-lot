package com.lot.enums;

public enum VehicleType {

    CAR,
    BIKE,
    TRUCK,
    BUS,
    VAN,
    SUV,
    ELECTRIC_CAR,
    MOTORCYCLE;

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }

    public static VehicleType fromString(String type) {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.name().equalsIgnoreCase(type)) {
                return vehicleType;
            }
        }
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }
}
