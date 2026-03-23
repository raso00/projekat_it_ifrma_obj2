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
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
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

    @ManyToMany
    @JoinTable(
            name = "sektor_pozicija",
            joinColumns = @JoinColumn(name = "sektor_id"),
            inverseJoinColumns = @JoinColumn(name = "pozicija_id")
    )
    public List<Pozicija> pozicije;

    @OneToMany(mappedBy = "sektor")
    public List<Projekat> projekti;


}
