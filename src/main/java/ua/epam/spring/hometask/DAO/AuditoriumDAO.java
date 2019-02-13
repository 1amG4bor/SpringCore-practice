package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class AuditoriumDAO {
    private static Map<Long, Auditorium> auditoriumMap;

    public AuditoriumDAO() {
        auditoriumMap = new HashMap<>();
    }

    public void initData() {
        Auditorium main = new Auditorium("Main room", 180);
        main.setVipSeats(LongStream.rangeClosed(156, 180).boxed().collect(Collectors.toSet()));
        auditoriumMap.put(0L, main);
        Auditorium room1 = new Auditorium("Room-1", 80);
        main.setVipSeats(LongStream.rangeClosed(65, 80).boxed().collect(Collectors.toSet()));
        auditoriumMap.put(1L, room1);
        Auditorium room2 = new Auditorium("Room-2", 60);
        main.setVipSeats(LongStream.rangeClosed(45, 60).boxed().collect(Collectors.toSet()));
        auditoriumMap.put(2L, room2);
    }

    public Auditorium getAuditoriumByName(String name) {
        return auditoriumMap.get(searchKey(name));
    }

    public Collection<Auditorium> getAll() {
        return auditoriumMap.values();
    }

    public Auditorium saveAuditorium(Auditorium auditorium) {
        if (auditoriumMap.containsKey(auditorium.getId())) return modifyAuditorium(auditorium);
        return auditoriumMap.put(auditorium.getId(), auditorium);
    }

    public void deleteAuditorium(Auditorium auditorium) {
        auditoriumMap.remove(auditorium.getId());
    }

    private boolean isExist(Long id) { return auditoriumMap.containsKey(id); }

    private Auditorium modifyAuditorium(Auditorium auditorium) {
        return auditoriumMap.replace(auditorium.getId(), auditorium);
    }

    private Long searchKey(String name) {
        for (Map.Entry item: auditoriumMap.entrySet()) {
            if (((Auditorium) item.getValue()).getName().equals(name)) {
                return (Long) item.getKey();
            }
        }
        return null;
    }
}
