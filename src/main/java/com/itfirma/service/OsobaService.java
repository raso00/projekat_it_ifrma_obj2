package com.itfirma.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import com.itfirma.obj2.Osoba;
import java.util.List;

@Dependent
public class OsobaService {

    @Inject
    private EntityManager em;

    @Transactional
    public Osoba createOsoba(Osoba osoba) {
        return em.merge(osoba);
    }

    @Transactional
    public List<Osoba> getAllOsobe() {
        return em.createNamedQuery(Osoba.GET_ALL_OSOBE, Osoba.class).getResultList();
    }
}