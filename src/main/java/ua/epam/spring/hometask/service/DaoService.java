package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.AuditoriumDAO;
import ua.epam.spring.hometask.DAO.EventDAO;
import ua.epam.spring.hometask.DAO.UserDAO;

public class DaoService {
    private AuditoriumDAO auditoriumDAO;
    private EventDAO eventDAO;
    private UserDAO userDAO;

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
