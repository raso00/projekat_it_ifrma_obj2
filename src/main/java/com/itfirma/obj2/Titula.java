package com.itfirma.obj2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Titula titula)) return false;
        return Objects.equals(id, titula.id) && Objects.equals(naziv, titula.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Titula{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
