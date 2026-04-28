package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pozicija pozicija)) return false;
        return Objects.equals(id, pozicija.id) && Objects.equals(naziv, pozicija.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Pozicija{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }

}
