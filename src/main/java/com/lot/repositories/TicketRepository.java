package com.lot.repositories;

import com.lot.models.Ticket;

import java.util.Optional;

public interface TicketRepository {

    Ticket saveTicket(Ticket ticket);

    Optional<Ticket> getTicket(long ticketId);

    void deleteTicket(long ticketId);

    boolean isTicketValid(long ticketId);
}
