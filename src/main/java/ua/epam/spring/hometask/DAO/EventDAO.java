package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventDAO {
    private static Map<Long, Event> eventMap;

    public EventDAO() {
        eventMap = new HashMap<>();
    }

    public void initData() {
        LocalDateTime now = LocalDateTime.now();
        Event terminator1 = new Event("Terminator", 5, EventRating.MID);
        Event terminator2 = new Event("Terminator-2, Judgment Day", 5, EventRating.HIGH);
        Event terminator3 = new Event("Terminator-3,  Rise of the Machines", 5, EventRating.LOW);
        eventMap.put(3L, terminator1);
        eventMap.put(4L, terminator2);
        eventMap.put(5L, terminator3);
    }

    public Event getEvent(Long id) {
        return eventMap.get(id);
    }

    public Event getEventByName(String name) {
        return eventMap.get(searchKey(name));
    }

    public Collection<Event> getAll() {
        return eventMap.values();
    }

    public Event saveEvent(Event event) {
        if (isExist(event.getId())) return modifyEvent(event);
        return eventMap.put(event.getId(), event);
    }

    public void deleteEvent(Event event) {
        eventMap.remove(event.getId());
    }

    private boolean isExist(Long id) {
        return eventMap.containsKey(id);
    }

    private Event modifyEvent(Event event) {
        return eventMap.replace(event.getId(), event);
    }

    private Long searchKey(String name) {
        for (Map.Entry item: eventMap.entrySet()) {
            if (((Event) item.getValue()).getName().equals(name)) {
                return (Long) item.getKey();
            }
        }
        return null;
    }
}
