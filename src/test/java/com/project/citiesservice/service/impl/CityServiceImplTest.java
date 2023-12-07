package com.project.citiesservice.service.impl;

import com.project.citiesservice.dto.CityDTO;
import com.project.citiesservice.entity.City;
import com.project.citiesservice.mapper.CityMapper;
import com.project.citiesservice.repository.CityRepository;
import com.project.citiesservice.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private CityMapper cityMapper;
    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void testSearchByCityAndCountryName1_shouldReturnSuccess() {

        Page<City> pageWithContent = new PageImpl<>(List.of(TestData.city1()));
        when(cityRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(pageWithContent);
        Page<CityDTO> result = cityService
                .searchByCityAndCountryName("Chicago", "USA", 20, 0);

        assertEquals(1, result.getContent().size());
    }

    @Test
    void testSearchByCityAndCountryName2_shouldReturnSuccess() {

        Page<City> pageWithContent = new PageImpl<>(List.of(TestData.city3(), TestData.city4()));
        when(cityRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(pageWithContent);
        Page<CityDTO> result = cityService
                .searchByCityAndCountryName("Wars", "POl", 20, 0);

        assertEquals(2, result.getContent().size());
    }

    @Test
    void testGetUniqueCities_shouldReturnSuccess() {

        when(cityRepository.findAll()).thenReturn(TestData.cities());
        List<String> actual = cityService
                .getUniqueCities();

        assertEquals(3, actual.size());
    }

    @Test
    void testEditCity_shouldReturnSuccess() {

        City city1 = TestData.city1();
        CityDTO cityDTO = CityDTO.builder()
                .cityName("Minsk").logo("newlogo").build();

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city1));

        when(cityMapper.mapToDto(city1)).thenReturn(cityDTO);
        CityDTO actual = cityService
                .editCity(1L, cityDTO);

        assertEquals("Minsk", actual.getCityName());
    }
}