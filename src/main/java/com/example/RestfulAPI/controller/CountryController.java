package com.example.RestfulAPI.controller;

import com.example.RestfulAPI.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/cities-in-countries-with-population")
    public List<String> getCitiesInCountriesWithPopulation(@RequestParam("population") int population,
                                                           @RequestParam("country") String country) {
        CompletableFuture<List<String>> cityInCountry = countryService.getAllCitiesWithCountries(country);
        CompletableFuture<List<String>> cityWithPopulation = countryService.getAllCitiesWithPopulation(population);
        List<String> result = null;
        try {
            result = cityInCountry.get().stream().distinct().filter(cityWithPopulation.get()::contains).collect(Collectors.toList());
        } catch (Exception e) {
            e.getCause();
        }
        return result;
    }
}
