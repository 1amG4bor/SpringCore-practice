package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.TicketDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.Interface.BookingService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomBookingService implements BookingService {

    private TicketDAO ticketDAO;

    public CustomBookingService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        HashSet<Ticket> tickets = new HashSet<>(ticketDAO.getAll());
        Set<Ticket> requiredTickets = filterTickets(tickets, event, dateTime, user);
        List<Double> prices = getPrices(requiredTickets, seats);
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        tickets.forEach(item -> ticketDAO.saveTicket(item));
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        HashSet<Ticket> tickets = new HashSet<>(ticketDAO.getAll());
        return tickets.stream()
                .filter(item -> item.getEvent().equals(event) && item.getDateTime().equals(dateTime))
                .collect(Collectors.toSet());
    }

    // region Internal Calculations
    private Set<Ticket> filterTickets(HashSet<Ticket> tickets, Event event, LocalDateTime dateTime, User user) {
        return tickets.stream()
                .filter(item -> item.getEvent().equals(event) &&
                        item.getDateTime().equals(dateTime) &&
                        item.getUser().equals(user))
                .collect(Collectors.toSet());
    }

    private List<Double> getPrices(Set<Ticket> requiredTickets, Set<Long> seats) {
        return requiredTickets.stream()
                .map(ticket -> ticket.getEvent().getBasePrice())
                .collect(Collectors.toList());
    }
    // endregion
}
