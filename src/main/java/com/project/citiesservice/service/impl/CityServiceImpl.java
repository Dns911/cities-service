package com.project.citiesservice.service.impl;


import com.project.citiesservice.dto.CityDTO;
import com.project.citiesservice.entity.City;
import com.project.citiesservice.exception.CityNotFoundException;
import com.project.citiesservice.mapper.CityMapper;
import com.project.citiesservice.repository.CityRepository;
import com.project.citiesservice.service.CityService;
import com.project.citiesservice.specification.CitySpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    @Override
    public List<String> getUniqueCities() {
        return cityRepository.findUniqueCityName();
    }

    @Override
    public Page<CityDTO> searchByCityAndCountryName(String cityName , String countryName, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<City> spec = CitySpecifications.getSpecification(cityName, countryName);
        Page<City> pageWithContent = cityRepository.findAll(spec, pageable);
        return pageWithContent.map(cityMapper::mapToDto);
    }

    @Override
    public CityDTO editCity(Long id, CityDTO newCity) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));
        city.setCityName(newCity.getCityName());
        city.setLogo(newCity.getLogo());
        cityRepository.save(city);

        return cityMapper.mapToDto(city);
    }
}
