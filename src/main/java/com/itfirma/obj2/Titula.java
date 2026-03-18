package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Titula.GET_ALL_TITULE, query = "Select t from Titula t")
public class Titula {

    public static final String GET_ALL_TITULE = "GetAllTitule";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;

    @OneToMany(mappedBy = "titula")
    public List<Osoba> osobe;

    public Titula() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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

    public List<Osoba> getOsobe() {
        return osobe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Titula titula = (Titula) o;
        return Objects.equals(id, titula.id) && Objects.equals(naziv, titula.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }
}
