package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.Interface.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class The10thTicketStrategy implements DiscountStrategy {

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        long purchasedTicket = user==null ? 0 : user.getTickets().size();
        return (byte) (purchasedTicket % 10 == 0 ? 50 : 0);
    }
}
