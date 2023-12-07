package com.project.citiesservice.repository;

import com.project.citiesservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> , JpaSpecificationExecutor<City> {

    @Query(value = "SELECT distinct c.city_name FROM cities c", nativeQuery = true)
    List<String> findUniqueCityName();
}
