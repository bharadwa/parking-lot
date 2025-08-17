package com.lot.enums;

public enum GateStatus {

    OPEN,
    CLOSED,
    MAINTENANCE,
    UNDER_CONSTRUCTION;

    public static GateStatus fromString(String status) {
        for (GateStatus gateStatus : GateStatus.values()) {
            if (gateStatus.name().equalsIgnoreCase(status)) {
                return gateStatus;
            }
        }
        throw new IllegalArgumentException("Unknown gate status: " + status);
    }
}
