package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.AuditoriumDAO;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.Interface.AuditoriumService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class CustomAuditoriumService implements AuditoriumService {

    private AuditoriumDAO auditoriumDAO;

    public CustomAuditoriumService(AuditoriumDAO auditoriumDAO) {
        this.auditoriumDAO = auditoriumDAO;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(auditoriumDAO.getAll());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDAO.getAuditoriumByName(name);
    }
}
