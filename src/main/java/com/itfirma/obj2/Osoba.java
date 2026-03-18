package com.itfirma.obj2;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = Osoba.GET_ALL_OSOBE, query = "Select o from Osoba o")
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

    @OneToOne(mappedBy = "osoba", cascade = CascadeType.ALL)
    public Profil profil;

    @ManyToMany
    @JoinTable(
            name = "osoba_sektor",
            joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "sektor_id")
    )
    @JsonIgnore
    public List<Sektor> sektori;

    @ManyToMany
    @JoinTable(
            name = "osoba_projekat",
            joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "projekat_id")
    )
    @JsonIgnore
    public List<Projekat> projekti;

    public Osoba() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setTitula(Titula titula) {
        this.titula = titula;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public void setSektori(List<Sektor> sektori) {
        this.sektori = sektori;
    }

    public void setProjekti(List<Projekat> projekti) {
        this.projekti = projekti;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Titula getTitula() {
        return titula;
    }

    public Profil getProfil() {
        return profil;
    }

    public List<Sektor> getSektori() {
        return sektori;
    }

    public List<Projekat> getProjekti() {
        return projekti;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return Objects.equals(id, osoba.id) && Objects.equals(ime, osoba.ime) && Objects.equals(prezime, osoba.prezime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime);
    }
}