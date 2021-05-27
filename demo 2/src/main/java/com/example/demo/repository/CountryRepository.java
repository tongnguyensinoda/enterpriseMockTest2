package com.example.demo.repository;

import com.example.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query("SELECT c FROM Country c where c.countryName = ?1")
    Optional<Country> findCountryByCountryName(String countryName);
    @Query("SELECT c FROM Country c where c.countryId = ?1")
    Optional<Country> findCountryByCountryId(Long countryId);
}
