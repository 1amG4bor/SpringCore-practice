package ua.epam.spring.hometask.domain;

import ua.epam.spring.hometask.service.CustomDomainObjectService;

import java.util.Random;

/**
 * @author Yuriy_Tkach
 */
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
