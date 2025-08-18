package com.lot.repositories;

import com.lot.models.Ticket;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class InMemoryTicketRepository implements  TicketRepository {


    private final Map<Long, Ticket> tickets = new TreeMap<>();

    private long idCounter=0;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        idCounter=idCounter+1;
        ticket.setId(idCounter);
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> getTicket(long ticketId) {
        if(tickets.isEmpty()||tickets.get(ticketId)==null){
            return Optional.empty();
        }
        return Optional.of(tickets.get(ticketId));
    }

    @Override
    public void deleteTicket(long ticketId) {
        if(tickets.containsKey(ticketId)) {
            tickets.remove(ticketId);
        }
    }

    @Override
    public boolean isTicketValid(long ticketId) {
        return getTicket(ticketId).isPresent();
    }
}
