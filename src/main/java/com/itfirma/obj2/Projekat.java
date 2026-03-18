package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Projekat.GET_ALL_PROJEKTI, query = "Select p from Projekat p")
public class Projekat {

    public static final String GET_ALL_PROJEKTI = "GetAllProjekti";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;

    @ManyToOne
    @JoinColumn(name = "sektor_id")
    public Sektor sektor;

    @ManyToMany(mappedBy = "projekti")
    public List<Osoba> osobe;

    public Projekat() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSektor(Sektor sektor) {
        this.sektor = sektor;
    }

    public void setOsobe(List<Osoba> osobe) {
        this.osobe = osobe;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public Sektor getSektor() {
        return sektor;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Projekat projekat = (Projekat) o;
        return Objects.equals(id, projekat.id) && Objects.equals(naziv, projekat.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }
}