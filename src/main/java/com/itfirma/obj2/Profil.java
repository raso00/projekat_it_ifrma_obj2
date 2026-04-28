package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Objects;


@Entity
@NamedQuery(name = Profil.GET_ALL_PROFILI, query = "Select p from Profil p")

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Profil profil)) return false;
        return Objects.equals(id, profil.id) && Objects.equals(email, profil.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Profil{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
