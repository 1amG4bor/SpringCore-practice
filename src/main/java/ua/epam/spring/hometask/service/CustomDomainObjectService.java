package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.DAO.DomainObjectDAO;
import ua.epam.spring.hometask.domain.DomainObject;
import ua.epam.spring.hometask.service.Interface.AbstractDomainObjectService;

import javax.annotation.Nonnull;
import java.util.Collection;

public class CustomDomainObjectService implements AbstractDomainObjectService {
    private DomainObjectDAO domainDAO;

    public CustomDomainObjectService(DomainObjectDAO domainObjectDAO) {
        this.domainDAO = domainObjectDAO;
    }

    public boolean isExistDomain(Long id) {
        return domainDAO.isExist(id);
    }

    @Override
    public DomainObject save(@Nonnull DomainObject object) {
        return domainDAO.addDomain(object);
    }

    @Override
    public void remove(@Nonnull DomainObject object) {
        domainDAO.removeDomain(object);
    }

    @Override
    public DomainObject getById(@Nonnull Long id) {
        return domainDAO.getDomain(id);
    }

    @Nonnull
    @Override
    public Collection getAll() {
        return domainDAO.getAll();
    }


}
