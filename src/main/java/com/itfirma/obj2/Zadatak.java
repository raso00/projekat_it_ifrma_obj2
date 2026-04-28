package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = Zadatak.GET_ALL_ZADACI, query = "Select z from Zadatak z")
public class Zadatak {

    public static final String GET_ALL_ZADACI = "GetAllZadaci";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;
    public String opis;
    public String status;

    @ManyToOne
    @JoinColumn(name = "projekat_id")
    public Projekat projekat;

    public Zadatak() {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProjekat(Projekat projekat) {
        this.projekat = projekat;
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

    public String getStatus() {
        return status;
    }

    public Projekat getProjekat() {
        return projekat;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Zadatak zadatak)) return false;
        return Objects.equals(id, zadatak.id) && Objects.equals(naziv, zadatak.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Zadatak{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
