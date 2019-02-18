package ua.epam.spring.hometask.DAO;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAO {
    private static Map<Long, User> userMap;

    public UserDAO() {
        userMap = new HashMap<>();
    }

    @PostConstruct
    public void initData() {
        userMap.put(0L, new User("admin", " ", "admin", LocalDate.of(2000,1,1), "21232f297a57a5a743894ae4a801fc3"));
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
