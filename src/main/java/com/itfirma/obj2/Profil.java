package com.itfirma.obj2;

import jakarta.persistence.*;
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
    public Osoba osoba;

    public Profil() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profil profil = (Profil) o;
        return Objects.equals(id, profil.id) && Objects.equals(email, profil.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
