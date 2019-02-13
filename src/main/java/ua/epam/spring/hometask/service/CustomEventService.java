package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.EventDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.Interface.EventService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class CustomEventService implements EventService {

    private EventDAO eventDAO;

    public CustomEventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventDAO.saveEvent(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDAO.deleteEvent(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDAO.getEvent(id);
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDAO.getEventByName(name);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDAO.getAll();
    }
}
