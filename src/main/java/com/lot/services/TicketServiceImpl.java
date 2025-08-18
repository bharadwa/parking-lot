package com.lot.services;

import com.lot.enums.VehicleType;
import com.lot.exceptions.GateNotFoundException;
import com.lot.exceptions.InvalidParkingLotException;
import com.lot.models.*;
import com.lot.repositories.GateRepository;
import com.lot.repositories.ParkingLotRepository;
import com.lot.repositories.TicketRepository;
import com.lot.repositories.VehicleRepository;
import com.lot.strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketServiceImpl implements  TicketService {


    private final TicketRepository ticketRepository;

    private final GateRepository  gateRepository;

    private final ParkingLotRepository parkingLotRepository;

    private final SpotAssignmentStrategy spotAssignmentStrategy;

    private final VehicleRepository vehicleRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             GateRepository gateRepository,
                             ParkingLotRepository parkingLotRepository,
                             SpotAssignmentStrategy spotAssignmentStrategy,
                             VehicleRepository vehicleRepository) {
        this.ticketRepository = ticketRepository;
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, long parkingLotId, long gateId) throws GateNotFoundException, InvalidParkingLotException {
        Optional<Gate> gate=gateRepository.getGateById(gateId);
        if(!gate.isPresent()) {
            throw  new GateNotFoundException("gate not found with id: " + gateId);
        }
        Optional<ParkingLot> parkingLot=parkingLotRepository.getParkingLotByGateId(gate.get().getId());

        if(!parkingLot.isPresent()) {
            throw new InvalidParkingLotException("parking lot not found");
        };
        Optional< ParkingSlot> parkingSlot=this.spotAssignmentStrategy.getParkingSpot(parkingLot.get(), vehicleType);

        if(!parkingSlot.isPresent()) {
            throw new RuntimeException("No parking slot available for vehicle type: " + vehicleType);
        }
        Optional< Vehicle> optionalVehicle=this.vehicleRepository.getVehicleByNumber(vehicleNumber);

        if(!optionalVehicle.isPresent()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setLicenseNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setColor("RED");
            vehicle.setOwnerName("John Doe");
            vehicle = vehicleRepository.save(vehicle);
            optionalVehicle = Optional.of(vehicle);
        }

        ParkingSlot slot = parkingSlot.get();
        Ticket ticket=new Ticket();
        ticket.setGate(gate.get());
        ticket.setEntryTime(new Date());
        ticket.setOperator(gate.get().getOperator());
        ticket.setParkingSlot(slot);
        ticket.setVehicle(optionalVehicle.get());

        return this.ticketRepository.saveTicket(ticket);
    }
}
