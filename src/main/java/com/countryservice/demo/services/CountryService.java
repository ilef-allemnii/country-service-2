package com.countryservice.demo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;

@Service
public class CountryService {

    private final CountryRepository countryrep;

    public CountryService(CountryRepository countryrep) {
        this.countryrep = countryrep;
    }

    public List<Country> getAllCountries() {
        return countryrep.findAll();
    }

    public Country getCountryById(int id) {
        for (Country c : countryrep.findAll()) {
            if (c.getIdCountry() == id) {
                return c;
            }
        }
        throw new NoSuchElementException("Country not found: " + id);
    }

    public Country getCountryByName(String name) {
        for (Country c : countryrep.findAll()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        throw new NoSuchElementException("Country not found: " + name);
    }

    public Country addCountry(Country country) {
        country.setIdCountry(0); // let MySQL generate the id
        return countryrep.save(country);
    }

    public Country updateCountry(Country country) {
        return countryrep.save(country);
    }

    public void deleteCountry(Country country) {
        countryrep.delete(country);
    }
}