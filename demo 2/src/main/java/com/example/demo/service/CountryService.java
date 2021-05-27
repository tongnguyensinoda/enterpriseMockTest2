package com.example.demo.service;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public void addNewCountry(Country country){
        Optional<Country> countryOptional = countryRepository.findCountryByCountryName(country.getCountryName());
        if (countryOptional.isPresent()){
            throw new IllegalStateException("country taken");
        }
        countryRepository.save(country);
    }
}
