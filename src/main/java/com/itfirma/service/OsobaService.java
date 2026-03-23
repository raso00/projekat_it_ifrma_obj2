package com.itfirma.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import com.itfirma.obj2.Osoba;
import java.util.List;
import com.itfirma.exception.OsobaException;

@Dependent
public class OsobaService {

    @Inject
    private EntityManager em;

    @Transactional
    public Osoba createOsoba(Osoba osoba) throws OsobaException{
        if(osoba==null){
            throw new OsobaException("Osoba nije pronadjena");
        }
        if(osoba.ime.isEmpty() || osoba.prezime.isEmpty()){
            throw new OsobaException("Ime i prezime su obavezna polja");
        }
        return em.merge(osoba);
    }

    @Transactional
    public List<Osoba> getAllOsobe () throws OsobaException{
        List<Osoba> osobe = em.createNamedQuery(Osoba.GET_ALL_OSOBE, Osoba.class).getResultList();
        if(osobe.isEmpty()){
            throw new OsobaException("Nema osoba");
        }
        return osobe;
    }
}