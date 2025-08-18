package com.lot.services;

import com.lot.enums.VehicleType;
import com.lot.exceptions.GateNotFoundException;
import com.lot.exceptions.InvalidParkingLotException;
import com.lot.models.Ticket;

public interface TicketService {


    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType,long parkingLotId,long gateId) throws GateNotFoundException, InvalidParkingLotException;

}
