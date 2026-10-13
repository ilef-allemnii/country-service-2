package com.countryservice.demo.beans;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idCountry;

    @Column(name = "name_country")
    private String name;

    @Column(name = "capital_name")
    private String capital;

    public Country() {
    }

    public Country(Integer idCountry, String name, String capital) {
        this.idCountry = (idCountry == null) ? 0 : idCountry;
        this.name = name;
        this.capital = capital;
    }

    public int getIdCountry() { return idCountry; }
    public void setIdCountry(int idCountry) { this.idCountry = idCountry; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country other = (Country) o;
        return idCountry == other.idCountry
                && Objects.equals(name, other.name)
                && Objects.equals(capital, other.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCountry, name, capital);
    }
}