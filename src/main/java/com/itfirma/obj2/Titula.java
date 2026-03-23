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
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Titula {

    public static final String GET_ALL_TITULE = "GetAllTitule";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String naziv;

    @OneToMany(mappedBy = "titula")
    public List<Osoba> osobe;

}
