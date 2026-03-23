package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@NamedQuery(name = Profil.GET_ALL_PROFILI, query = "Select p from Profil p")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Profil {

    public static final String GET_ALL_PROFILI = "GetAllProfili";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String email;
    public String telefon;
    public String linkedin;

    @OneToOne
    @JoinColumn(name = "osoba_id")
    private Osoba osoba;

}
