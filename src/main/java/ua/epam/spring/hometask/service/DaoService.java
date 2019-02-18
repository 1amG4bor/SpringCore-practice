package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.DAO.AuditoriumDAO;
import ua.epam.spring.hometask.DAO.EventDAO;
import ua.epam.spring.hometask.DAO.UserDAO;

@Service
public class DaoService {
    private AuditoriumDAO auditoriumDAO;
    private EventDAO eventDAO;
    private UserDAO userDAO;

    @Autowired
    public DaoService(AuditoriumDAO auditoriumDAO, EventDAO eventDAO, UserDAO userDAO) {
        this.auditoriumDAO = auditoriumDAO;
        this.eventDAO = eventDAO;
        this.userDAO = userDAO;
    }

    public AuditoriumDAO getAuditoriumDAO() {
        return auditoriumDAO;
    }

    public EventDAO getEventDAO() {
        return eventDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
