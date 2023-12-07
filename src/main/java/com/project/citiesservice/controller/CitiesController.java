package com.project.citiesservice.controller;

import com.project.citiesservice.dto.CityDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CitiesController {

    @Operation(summary = "Edit city name and logo", description = "Edit city name and logo in BD, only users with EDITOR role")
    ResponseEntity<CityDTO> updateCity(
            @PathVariable("id") Long id,
            @RequestParam String newName,
            @RequestParam String newLogo);

    @Operation(summary = "Get all unique city names", description = "Get a list of all unique city names")
    ResponseEntity<List<String>> getUniqueCities();

    @Operation(summary = "Get searched cities", description = "Get pagination of searched cities by city name and (or) country name,\n with ignore case")
    ResponseEntity<Page<CityDTO>> searchByCityAndCountryName(
            @RequestParam(required = false) String cityName,
            @RequestParam(required = false) String countryName,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page);
}
