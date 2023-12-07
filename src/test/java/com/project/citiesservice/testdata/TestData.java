package com.project.citiesservice.testdata;


import com.project.citiesservice.entity.City;
import com.project.citiesservice.entity.Country;

import java.util.ArrayList;
import java.util.List;


public class TestData {

    public static Country country1() {
        return Country.builder()
                .name("USA")
                .build();
    }


    public static Country country2() {
        return Country.builder()
                .name("Poland")
                .build();
    }

    public static City city1() {
        return City.builder()
                .id(1L)
                .cityName("Chicago")
                .country(country1())
                .logo("logo1.png")
                .build();
    }

    public static City city2() {
        return City.builder()
                .id(2L)
                .cityName("New York")
                .country(country1())
                .logo("logo2.png")
                .build();
    }

    public static City city3() {
        return City.builder()
                .id(3L)
                .cityName("Warsaw")
                .country(country2())
                .logo("logo3.png")
                .build();
    }

    public static City city4() {
        return City.builder()
                .id(4L)
                .cityName("Warsaw")
                .country(country1())
                .logo("logo4.png")
                .build();
    }

    public static List<City> cities(){
        List<City> cities = new ArrayList<>();
        cities.add(city1());
        cities.add(city2());
        cities.add(city3());
        cities.add(city4());
        return cities;
    }
}
