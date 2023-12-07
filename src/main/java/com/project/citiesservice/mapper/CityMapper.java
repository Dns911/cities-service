package com.project.citiesservice.mapper;

import com.project.citiesservice.dto.CityDTO;
import com.project.citiesservice.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CityMapper {

    @Mapping(target="countryName", expression = "java(entity.getCountry().getName())")
    CityDTO mapToDto(City entity);
}