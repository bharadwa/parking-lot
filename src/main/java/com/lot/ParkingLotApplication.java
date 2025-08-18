package com.lot;

import com.lot.controllers.TicketController;
import com.lot.dto.request.GenerateTicketRequestDTO;
import com.lot.dto.response.GenerateTicketResponseDTO;
import com.lot.repositories.*;
import com.lot.services.TicketService;
import com.lot.services.TicketServiceImpl;
import com.lot.strategies.NearestSpotAssignmentStrategy;
import com.lot.strategies.SpotAssignmentStrategy;
import com.lot.models.*;
import com.lot.enums.*;
import java.util.*;

public class ParkingLotApplication {

    public static void main(String[] args) {
        // Initialize repositories
        InMemoryParkingLotRepository parkingLotRepository = new InMemoryParkingLotRepository();
        InMemoryGateRepository gateRepository = new InMemoryGateRepository();
        InMemoryParkingSlotRepository parkingSlotRepository = new InMemoryParkingSlotRepository();

        // Create and save a ParkingSlot
        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber("S1");
        slot.setSlotName("Slot 1");
        slot.setStatus(SlotStatus.AVAILABLE);
        slot.setSupportedVehicleType(VehicleType.CAR);
        parkingSlotRepository.saveParkingSlot(slot);

        // Create and save a ParkingFloor
        ParkingFloor floor = new ParkingFloor();
        floor.setName("Ground Floor");
        floor.setFloorNumber(0);
        floor.setParkingSlotList(Collections.singletonList(slot));
        floor.setStatus(ParkingFloorStatus.OPERATIONAL);
        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(floor);

        // Create and save a Gate
        Gate gate = new Gate();
        gate.setName("Main Gate");
        gate.setType(GateType.entry);
        gateRepository.saveGate(gate);
        List<Gate> gates = new ArrayList<>();
        gates.add(gate);

        // Create and save a ParkingLot
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("Test Lot");
        parkingLot.setAddress("123 Main St");
        parkingLot.setFloors(floors);
        parkingLot.setGates(gates);
        parkingLotRepository.save(parkingLot);

        // Use the initialized repositories in the controller
        TicketController controller = getTicketController(parkingLotRepository, gateRepository);

        System.out.println("Parking Lot Application Started Successfully");
        GenerateTicketRequestDTO requestDTO = new GenerateTicketRequestDTO();
        requestDTO.setParkingLotId(1);
        requestDTO.setGateId(1);
        requestDTO.setVehicleNumber("KA-01-HH-1234");
        requestDTO.setVehicleType("CAR");
        GenerateTicketResponseDTO response = controller.generateTicket(requestDTO);
        System.out.println("Parking Lot Application Ended Successfully:" + response.getResponse().getMessage());
    }

    private static TicketController getTicketController(InMemoryParkingLotRepository parkingLotRepository, InMemoryGateRepository gateRepository) {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        TicketRepository parkingTicketRepository = new InMemoryTicketRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new NearestSpotAssignmentStrategy();
        TicketService service = new TicketServiceImpl(
                parkingTicketRepository,
                gateRepository,
                parkingLotRepository,
                spotAssignmentStrategy,
                vehicleRepository
        );
        return new TicketController(service);
    }
}
