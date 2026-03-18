package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Sektor.GET_ALL_SEKTORI, query = "Select s from Sektor s")
public class Sektor {

    public static final String GET_ALL_SEKTORI = "GetAllSektori";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;
    public String opis;

    @ManyToMany(mappedBy = "sektori")
    public List<Osoba> osobe;

    @ManyToMany
    @JoinTable(
            name = "sektor_pozicija",
            joinColumns = @JoinColumn(name = "sektor_id"),
            inverseJoinColumns = @JoinColumn(name = "pozicija_id")
    )
    public List<Pozicija> pozicije;

    @OneToMany(mappedBy = "sektor")
    public List<Projekat> projekti;

    public Sektor() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setOsobe(List<Osoba> osobe) {
        this.osobe = osobe;
    }

    public void setPozicije(List<Pozicija> pozicije) {
        this.pozicije = pozicije;
    }

    public void setProjekti(List<Projekat> projekti) {
        this.projekti = projekti;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public List<Osoba> getOsobe() {
        return osobe;
    }

    public List<Pozicija> getPozicije() {
        return pozicije;
    }

    public List<Projekat> getProjekti() {
        return projekti;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sektor sektor = (Sektor) o;
        return Objects.equals(id, sektor.id) && Objects.equals(naziv, sektor.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }
}
