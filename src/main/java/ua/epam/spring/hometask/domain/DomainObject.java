package ua.epam.spring.hometask.domain;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.service.CustomDomainObjectService;

import javax.persistence.Entity;
import java.util.Random;

/**
 * @author Yuriy_Tkach
 */
@Entity
public class DomainObject {
    private CustomDomainObjectService domainService;
    private Long id;

    public DomainObject() {
//        this.id = generateUniqueID();
    }

    private Long generateUniqueID() {
        Long uniqueId;
        Random r = new Random();
        do {
            uniqueId = r.nextLong();
        } while (domainService.isExistDomain(uniqueId));
        return uniqueId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
