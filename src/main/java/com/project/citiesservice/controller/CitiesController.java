package com.project.citiesservice.controller;

import com.project.citiesservice.dto.CityDTO;
import com.project.citiesservice.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
@RequiredArgsConstructor
public class CitiesController {

    private final CityService cityService;

    @Operation(summary = "Edit city name and logo", description = "Edit city name and logo in BD, only users with EDITOR role")
    @PatchMapping (value = "/{id}")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ResponseEntity<CityDTO> updateCity(
            @PathVariable("id") Long id,
            @RequestParam String newName,
            @RequestParam String newLogo) {
        CityDTO newCity = CityDTO.builder()
                .cityName(newName)
                .logo(newLogo)
                .build();
        return ResponseEntity.ok(cityService.editCity(id, newCity));
    }
    @Operation(summary = "Get all unique city names", description = "Get a list of all unique city names")
    @GetMapping("/unique")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<String>> getUniqueCities() {
        return ResponseEntity.ok(cityService.getUniqueCities());
    }

    @Operation(summary = "Get all searched cities", description = "Get all searched cities by city name and country name")
    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<CityDTO>> searchByCityAndCountryName(
            @RequestParam(required = false) String cityName,
            @RequestParam(required = false) String countryName,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(cityService.searchByCityAndCountryName(cityName, countryName, size, page));
    }
}
