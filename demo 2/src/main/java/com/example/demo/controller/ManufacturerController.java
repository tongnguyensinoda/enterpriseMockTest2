package com.example.demo.controller;

import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;
import com.example.demo.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "manufacturer")
public class ManufacturerController {
    // connecting with ProductService.java
    @Autowired
    private final ManufacturerService manufacturerService;

    // connecting with ProductRepository.java
    @Autowired
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService, ManufacturerRepository manufacturerRepository) {
        this.manufacturerService = manufacturerService;
        this.manufacturerRepository = manufacturerRepository;
    }

    @PostMapping(path = "add")
    public void addNewProduct(@RequestBody Manufacturer manufacturer){
        manufacturerService.addNewManufacturer(manufacturer);
    }

    @GetMapping(path = "list")
    public List<Manufacturer> showListOfManufacturer(){
        return manufacturerService.getManufacturers();
    }


}
