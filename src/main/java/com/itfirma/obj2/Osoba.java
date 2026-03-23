package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@NamedQuery(name = Osoba.GET_ALL_OSOBE, query = "Select o from Osoba o")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Osoba {

    public static final String GET_ALL_OSOBE = "GetAllOsobe";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String ime;
    public String prezime;

    @ManyToOne
    @JoinColumn(name = "titula_id")
    public Titula titula;

    @OneToOne(mappedBy = "osoba", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profil profil;

    @ManyToMany(mappedBy = "osobe")
    @JsonIgnore
    public List<Sektor> sektori;

    @ManyToMany(mappedBy = "osobe")
    @JsonIgnore
    public List<Projekat> projekti;


}







