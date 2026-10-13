package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;

@RestController
public class CountryController {

    private final CountryService countryservice;

    public CountryController(CountryService countryservice) {
        this.countryservice = countryservice;
    }

    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getCountries() {
        try {
            List<Country> countries = countryservice.getAllCountries();
            return new ResponseEntity<>(countries, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {
        try {
            return new ResponseEntity<>(countryservice.getCountryById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName) {
        try {
            return new ResponseEntity<>(countryservice.getCountryByName(countryName), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        try {
            return new ResponseEntity<>(countryservice.addCountry(country), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id,
                                                 @RequestBody Country country) {
        try {
            Country existing = countryservice.getCountryById(id);
            existing.setName(country.getName());
            existing.setCapital(country.getCapital());
            return new ResponseEntity<>(countryservice.updateCountry(existing), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletecountry/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable(value = "id") int id) {
        try {
            Country country = countryservice.getCountryById(id);
            countryservice.deleteCountry(country);
            return new ResponseEntity<>(country, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}