package com.lot.enums;

public enum ParkingFloorStatus {
    OPEN,
    CLOSED,
    UNDER_MAINTENANCE,
    FULL;

    public static ParkingFloorStatus fromString(String status) {
        for (ParkingFloorStatus s : ParkingFloorStatus.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }

}
