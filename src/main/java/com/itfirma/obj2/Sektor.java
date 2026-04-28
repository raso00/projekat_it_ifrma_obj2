package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NamedQuery(name = Sektor.GET_ALL_SEKTORI, query = "Select s from Sektor s")

public class Sektor {

    public static final String GET_ALL_SEKTORI = "GetAllSektori";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;
    public String opis;

    @ManyToMany
    @JoinTable(
            name = "sektor_osoba",
            joinColumns = @JoinColumn(name="sektor_id"),
            inverseJoinColumns = @JoinColumn(name="osoba_id")
    )
    public List<Osoba> osobe;

    @OneToOne(mappedBy = "sektor", cascade = CascadeType.ALL)
    public Budzet budzet;

    @ManyToMany
    @JoinTable(
            name = "sektor_pozicija",
            joinColumns = @JoinColumn(name = "sektor_id"),
            inverseJoinColumns = @JoinColumn(name = "pozicija_id")
    )
    public List<Pozicija> pozicije;

    @OneToMany(mappedBy = "sektor")
    public List<Projekat> projekti;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sektor sektor)) return false;
        return Objects.equals(id, sektor.id) && Objects.equals(naziv, sektor.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Sektor{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }

}
