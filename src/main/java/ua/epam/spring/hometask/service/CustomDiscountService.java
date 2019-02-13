package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.Interface.DiscountService;
import ua.epam.spring.hometask.service.Interface.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class CustomDiscountService implements DiscountService {

    private List<DiscountStrategy> discountList;

    public CustomDiscountService(List<DiscountStrategy> discountList) {
        this.discountList = discountList;
    }

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        return 0;
    }


}
