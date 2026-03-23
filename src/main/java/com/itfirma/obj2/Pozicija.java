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
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode

public class Pozicija {

    public static final String GET_ALL_POZICIJE = "GetAllPozicije";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;
    public String opis;

    @ManyToMany(mappedBy = "pozicije")
    public List<Sektor> sektori;


}
