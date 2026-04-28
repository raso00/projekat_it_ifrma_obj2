package com.itfirma.obj2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "projekat", fetch = FetchType.LAZY)
    @JsonIgnore
    public List<Zadatak> zadaci;

    @ManyToMany
    @JoinTable(
            name = "projekat_osoba",
            joinColumns= @JoinColumn(name="projekat_id"),
            inverseJoinColumns = @JoinColumn(name="osoba_id")
    )
    public List<Osoba> osobe;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Projekat projekat)) return false;
        return Objects.equals(id, projekat.id) && Objects.equals(naziv, projekat.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Projekat{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }

}