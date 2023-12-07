package com.project.citiesservice.service;

import com.project.citiesservice.dto.CityDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CityService {

    List<String> getUniqueCities();
    Page<CityDTO> searchByCityAndCountryName(String cityName , String countryName, int size, int page);
    CityDTO editCity(Long id, CityDTO newCity);
}
