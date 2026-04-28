package com.itfirma.obj2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = Certifikat.GET_ALL_CERTIFIKATI_BY_OSOBA_ID, query = "Select c from Certifikat c where c.osoba.id = :id")
public class Certifikat {

    public static final String GET_ALL_CERTIFIKATI_BY_OSOBA_ID = "GetAllCertifikatiByOsobaId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="certifikat_seq")
    @SequenceGenerator(name="certifikat_seq", sequenceName="certifikat_seq", allocationSize=1)
    public Long id;

    public String naziv;
    public String institucija;
    public String datumIzdavanja;

    @ManyToOne
    @JoinColumn(name = "osoba_id")
    @JsonBackReference("osoba-certifikati")
    public Osoba osoba;

    public Certifikat() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public void setDatumIzdavanja(String datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getInstitucija() {
        return institucija;
    }

    public String getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Certifikat certifikat)) return false;
        return Objects.equals(id, certifikat.id) && Objects.equals(naziv, certifikat.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv);
    }

    @Override
    public String toString() {
        return "Certifikat{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
