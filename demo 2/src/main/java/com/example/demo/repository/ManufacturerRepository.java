package com.example.demo.repository;

import com.example.demo.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly= true)
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    @Query("SELECT m FROM Manufacturer m where m.manufacturerName = ?1")
    Optional<Manufacturer> findManufacturerByManufacturerName(String manufacturerName);
}
