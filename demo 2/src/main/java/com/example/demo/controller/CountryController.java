package com.example.demo.controller;


import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "country")
public class CountryController {
    @Autowired
    private final CountryService countryService;
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping(path = "add")
    public void addNewProduct(@RequestBody Country country){
        countryService.addNewCountry(country);
    }
    @GetMapping(path = "list")
    public List<Country> showListOfCountry(){
        return countryService.getCountries();
    }
}
