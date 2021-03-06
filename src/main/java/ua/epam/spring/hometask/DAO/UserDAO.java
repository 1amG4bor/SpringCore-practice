package ua.epam.spring.hometask.DAO;

import ua.epam.spring.hometask.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private static Map<Long, User> userMap;

    public UserDAO() {
        userMap = new HashMap<>();
    }

    public User getUser(Long id) {
        return userMap.get(id);
    }

    public User getUserByEmail(String email) {
        return userMap.get(searchKey(email));
    }

    public Collection<User> getAll() {
        return userMap.values();
    }

    public User saveUser(User user) {
        if (isExist(user.getId())) return modifyUser(user);
        return userMap.put(user.getId(), user);
    }

    public void deleteUser(User user) {
        userMap.remove(user.getId());
    }

    private boolean isExist(Long id) {
        return userMap.containsKey(id);
    }

    private User modifyUser(User user) {
        return userMap.replace(user.getId(), user);
    }

    private Long searchKey(String email) {
        for (Map.Entry item: userMap.entrySet()) {
            if (((User) item.getValue()).getEmail().equals(email)) {
                return (Long) item.getKey();
            }
        }
        return null;
    }
}
