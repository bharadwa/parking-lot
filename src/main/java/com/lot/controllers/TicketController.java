package com.lot.controllers;

import com.lot.dto.request.GenerateTicketRequestDTO;
import com.lot.dto.response.GenerateTicketResponseDTO;
import com.lot.dto.response.Response;
import com.lot.enums.ResponseStatus;
import com.lot.enums.VehicleType;
import com.lot.exceptions.GateNotFoundException;
import com.lot.exceptions.InvalidParkingLotException;
import com.lot.models.Ticket;
import com.lot.services.TicketService;

public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        // Private constructor to prevent instantiation

        this.ticketService = ticketService;
    }

    // Add methods to handle ticket operations, e.g., create, update, delete, etc.

    public GenerateTicketResponseDTO generateTicket(GenerateTicketRequestDTO request) {

        GenerateTicketResponseDTO response = new GenerateTicketResponseDTO();
        response.setResponse(new Response());
        try {

            Ticket ticket = this.ticketService.generateTicket(request.getVehicleNumber(), VehicleType.fromString(request.getVehicleType()), request.getParkingLotId(), request.getGateId());
            response.setTicketId(ticket.getId());
            response.getResponse().setResponseStatus(ResponseStatus.SUCCESS);
            response.getResponse().setMessage("Ticket Generated Successfully");
        } catch (GateNotFoundException e) {
            response.getResponse().setResponseStatus(ResponseStatus.FAILURE);
            response.getResponse().setMessage(e.getMessage());
        } catch (InvalidParkingLotException e) {
            response.getResponse().setResponseStatus(ResponseStatus.FAILURE);
            response.getResponse().setMessage(e.getMessage());
        }
        return response;
    }
}
