package com.itfirma.obj2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Osoba.GET_ALL_OSOBE, query = "Select o from Osoba o")
@NamedQuery(name = Osoba.GET_OSOBA_BY_IME, query = "Select o from Osoba o where o.ime = :imeO")

public class Osoba {

    public static final String GET_ALL_OSOBE = "GetAllOsobe";
    public static final String GET_OSOBA_BY_IME = "GetOsobaByIme";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="osoba_seq")
    @SequenceGenerator(name="osoba_seq", sequenceName="osoba_seq", allocationSize=1)
    private Long id;

    private String ime;
    private String prezime;

    @ManyToOne
    @JoinColumn(name = "titula_id")
    private Titula titula;

    @OneToOne(mappedBy = "osoba", cascade = CascadeType.ALL)
    private Profil profil;

    @OneToOne(mappedBy = "osoba", cascade = CascadeType.ALL)
    private Ugovor ugovor;

    @ManyToMany
    @JoinTable(
            name = "osoba_sektor",
            joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "sektor_id")
    )
    @JsonIgnore
    private List<Sektor> sektori = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "osoba_projekat",
            joinColumns = @JoinColumn(name = "osoba_id"),
            inverseJoinColumns = @JoinColumn(name = "projekat_id")
    )
    @JsonIgnore
    private List<Projekat> projekti = new ArrayList<>();

    @OneToMany(mappedBy = "osoba", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Certifikat> certifikati = new ArrayList<>();

    public Osoba() {
    }

    public Osoba(Long id, String ime, String prezime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Titula getTitula() {
        return titula;
    }

    public void setTitula(Titula titula) {
        this.titula = titula;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Ugovor getUgovor() {
        return ugovor;
    }

    public void setUgovor(Ugovor ugovor) {
        this.ugovor = ugovor;
    }

    public List<Sektor> getSektori() {
        return sektori;
    }

    public void setSektori(List<Sektor> sektori) {
        this.sektori = sektori;
    }

    public List<Projekat> getProjekti() {
        return projekti;
    }

    public void setProjekti(List<Projekat> projekti) {
        this.projekti = projekti;
    }

    public List<Certifikat> getCertifikati() {
        return certifikati;
    }

    public void setCertifikati(List<Certifikat> certifikati) {
        this.certifikati = certifikati;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Osoba osoba)) return false;
        return Objects.equals(id, osoba.id) && Objects.equals(ime, osoba.ime) && Objects.equals(prezime, osoba.prezime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime);
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                '}';
    }
}