package ua.epam.spring.hometask.DAO;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.domain.DomainObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DomainObjectDAO {
    private static Map<Long, DomainObject> domainMap;

    public DomainObjectDAO() {
        this.domainMap = new HashMap<>();
    }

    public boolean isExist(Long id) {
        return domainMap.containsKey(id);
    }

    public DomainObject getDomain(Long id) {
        return domainMap.get(id);
    }

    public Collection<DomainObject> getAll() {
        return domainMap.values();
    }

    public DomainObject addDomain(DomainObject object) {
        return domainMap.put(object.getId(), object);
    }

    public void removeDomain(DomainObject object) {
        domainMap.remove(object.getId());
    }
}
