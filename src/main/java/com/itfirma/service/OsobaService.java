package com.itfirma.service;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import com.itfirma.obj2.Osoba;
import java.util.List;
import com.itfirma.obj2.Certifikat;
import com.itfirma.obj2.Ugovor;
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
        if(osoba.getIme().isEmpty() || osoba.getPrezime().isEmpty()){
            throw new OsobaException("Ime i prezime su obavezna polja");
        }
        if (osoba.getCertifikati() != null) {
            for (Certifikat c : osoba.getCertifikati()) {
                c.setOsoba(osoba);
            }
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

    public List<Osoba> getOsobaByIme(String ime) {
        List<Osoba> osobe = em.createNamedQuery(Osoba.GET_OSOBA_BY_IME, Osoba.class)
                .setParameter("imeO", ime)
                .getResultList();
        return osobe;
    }

    public List<Certifikat> getCertifikatiByOsobaId(Long id) {
        List<Certifikat> certifikati = em.createNamedQuery(Certifikat.GET_ALL_CERTIFIKATI_BY_OSOBA_ID, Certifikat.class)
                .setParameter("id", id)
                .getResultList();
        return certifikati;
    }


}