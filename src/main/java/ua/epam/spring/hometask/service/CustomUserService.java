package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.DAO.UserDAO;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.Interface.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service
public class CustomUserService implements UserService {
    private UserDAO userDAO;

    @Autowired
    public CustomUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User save(@Nonnull User user) {
        return userDAO.saveUser(user);
    }

    @Override
    public void remove(@Nonnull User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userDAO.getUser(id);
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDAO.getUserByEmail(email);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }
}
