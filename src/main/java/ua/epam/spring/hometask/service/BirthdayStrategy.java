package ua.epam.spring.hometask.service;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.Interface.DiscountStrategy;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Component
public class BirthdayStrategy implements DiscountStrategy {

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        if (user.equals(null)) return 0;
        int birthday = user.getDateOfBirth().getDayOfYear();
        int airDate = airDateTime.getDayOfYear();
        return (byte) (Math.abs(airDate-birthday) <= 5 ? 5 : 0);
    }
}
