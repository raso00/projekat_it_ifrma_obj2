package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NamedQuery(name = Projekat.GET_ALL_PROJEKTI, query = "Select p from Projekat p")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode

public class Projekat {

    public static final String GET_ALL_PROJEKTI = "GetAllProjekti";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;

    @ManyToOne
    @JoinColumn(name = "sektor_id")
    public Sektor sektor;

    @ManyToMany
    @JoinTable(
            name = "projekat_osoba",
            joinColumns= @JoinColumn(name="projekat_id"),
            inverseJoinColumns = @JoinColumn(name="osoba_id")
    )
    public List<Osoba> osobe;

}