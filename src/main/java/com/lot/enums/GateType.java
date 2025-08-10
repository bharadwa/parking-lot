package com.lot.enums;

public enum GateType {

    entry,
    exit;
    public static GateType fromString(String type) {
        for (GateType gateType : GateType.values()) {
            if (gateType.name().equalsIgnoreCase(type)) {
                return gateType;
            }
        }
        throw new IllegalArgumentException("Unknown gate type: " + type);
    }
}
