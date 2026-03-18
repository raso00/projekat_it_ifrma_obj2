package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Pozicija.GET_ALL_POZICIJE, query = "Select p from Pozicija p")
public class Pozicija {

    public static final String GET_ALL_POZICIJE = "GetAllPozicije";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;
    public String opis;

    @ManyToMany(mappedBy = "pozicije")
    public List<Sektor> sektori;

    public Pozicija() {
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

    public void setSektori(List<Sektor> sektori) {
        this.sektori = sektori;
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

    public List<Sektor> getSektori() {
        return sektori;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pozicija pozicija = (Pozicija) o;
        return Objects.equals(id, pozicija.id) && Objects.equals(naziv, pozicija.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }
}
