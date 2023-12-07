package com.project.citiesservice.specification;


import com.project.citiesservice.entity.City;
import org.springframework.data.jpa.domain.Specification;

public class CitySpecifications {

    private CitySpecifications() {
    }

    public static Specification<City> getSpecification(String cityName, String countryName) {

        return Specification.where(hasCityName(cityName)).and(hasCountryName(countryName));
    }

    private static Specification<City> hasCityName(String cityName) {
        return (r, q, cb) -> cityName == null ? null : cb.like(r.get("cityName"), "%" + cityName + "%");
    }

    private static Specification<City> hasCountryName(String countryName) {
        return (r, q, cb) -> countryName == null ? null : cb.like(r.get("country").get("name"), "%" + countryName + "%");
    }

}
