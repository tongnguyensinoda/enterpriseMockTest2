package com.example.demo.service;

import com.example.demo.entity.Manufacturer;
import com.example.demo.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getManufacturers() {
        return manufacturerRepository.findAll();
    }

    public void addNewManufacturer(Manufacturer manufacturer){
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findManufacturerByManufacturerName(manufacturer.getManufacturerName());
        if (manufacturerOptional.isPresent()){
            throw new IllegalStateException("manufacturer taken");
        }
        manufacturerRepository.save(manufacturer);
    }

}
