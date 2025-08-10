package com.lot.enums;

public enum VehicleTypes {

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
}
