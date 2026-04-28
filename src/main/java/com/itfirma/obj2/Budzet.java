package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = Budzet.GET_ALL_BUDZETI, query = "Select b from Budzet b")
public class Budzet {

    public static final String GET_ALL_BUDZETI = "GetAllBudzeti";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Double iznos;
    public String valuta;
    public Integer godina;

    @OneToOne
    @JoinColumn(name = "sektor_id")
    public Sektor sektor;

    public Budzet() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public void setSektor(Sektor sektor) {
        this.sektor = sektor;
    }

    public Long getId() {
        return id;
    }

    public Double getIznos() {
        return iznos;
    }

    public String getValuta() {
        return valuta;
    }

    public Integer getGodina() {
        return godina;
    }

    public Sektor getSektor() {
        return sektor;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Budzet budzet)) return false;
        return Objects.equals(id, budzet.id) && Objects.equals(iznos, budzet.iznos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iznos);
    }

    @Override
    public String toString() {
        return "Budzet{" +
                "id=" + id +
                ", iznos=" + iznos +
                '}';
    }
}
