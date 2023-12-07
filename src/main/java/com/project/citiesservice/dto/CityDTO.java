package com.project.citiesservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CityDTO {

    private String cityName;
    private String logo;
    private String countryName;
}
