package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Ticket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TicketDAO {
    private static Map<Long, Ticket> ticketMap;

    public TicketDAO() { ticketMap = new HashMap<>(); }

    public Ticket getTicket(Long id) {
        return ticketMap.get(id);
    }

    public Collection<Ticket> getAll() {
        return ticketMap.values();
    }

    public Ticket saveTicket(Ticket ticket) {
        if (isExist(ticket.getId())) return modifyTicket(ticket);
        return ticketMap.put(ticket.getId(), ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketMap.remove(ticket.getId());
    }

    private boolean isExist(Long id) { return ticketMap.containsKey(id); }

    private Ticket modifyTicket(Ticket ticket) {
        return ticketMap.replace(ticket.getId(), ticket);
    }
}
