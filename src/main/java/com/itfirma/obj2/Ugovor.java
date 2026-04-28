package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = Ugovor.GET_ALL_UGOVORI, query = "Select u from Ugovor u")
public class Ugovor {

    public static final String GET_ALL_UGOVORI = "GetAllUgovori";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String tipUgovora;
    public Double plata;
    public String datumPocetka;

    @OneToOne
    @JoinColumn(name = "osoba_id")
    public Osoba osoba;

    public Ugovor() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipUgovora(String tipUgovora) {
        this.tipUgovora = tipUgovora;
    }

    public void setPlata(Double plata) {
        this.plata = plata;
    }

    public void setDatumPocetka(String datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Long getId() {
        return id;
    }

    public String getTipUgovora() {
        return tipUgovora;
    }

    public Double getPlata() {
        return plata;
    }

    public String getDatumPocetka() {
        return datumPocetka;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ugovor ugovor)) return false;
        return Objects.equals(id, ugovor.id) && Objects.equals(tipUgovora, ugovor.tipUgovora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipUgovora);
    }

    @Override
    public String toString() {
        return "Ugovor{" +
                "id=" + id +
                ", tipUgovora='" + tipUgovora + '\'' +
                '}';
    }
}
