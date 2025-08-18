package com.lot.models;

import com.lot.enums.SlotStatus;
import com.lot.enums.VehicleType;


public class ParkingSlot extends BaseModel  {

    private String slotNumber;

    private String slotName;

    private SlotStatus status;

    private Vehicle vehicle;

    private VehicleType supportedVehicleType;

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleType getSupportedVehicleType() {
        return supportedVehicleType;
    }


    public void setSupportedVehicleType(VehicleType supportedVehicleType) {
        this.supportedVehicleType = supportedVehicleType;
    }
}
