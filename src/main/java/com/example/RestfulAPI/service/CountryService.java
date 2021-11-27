package com.example.RestfulAPI.service;

import com.example.RestfulAPI.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<List<String>> getAllCitiesWithCountries(String country) {
        String url = "https://countriesnow.space/api/v0.1/countries";
        CountryApiResponse cities = restTemplate.getForObject(url, CountryApiResponse.class);
        List<String> cityList = cities.getData().stream()
                .filter(data -> country.equals(data.getCountry()))
                .flatMap(data -> data.getCities().stream())
                .collect(Collectors.toList());

        //System.out.println("Countries : " + cityList);
        return CompletableFuture.completedFuture(cityList);
    }

    @Async
    public CompletableFuture<List<String>> getAllCitiesWithPopulation(double population) {
        String url = "https://countriesnow.space/api/v0.1/countries/population/cities";
        PopulationApiResponse cities = restTemplate.getForObject(url, PopulationApiResponse.class);

        if(cities == null) return null;

        List<String> cityList = cities.getData().stream()
                .filter(d -> !Objects.isNull(d) && !d.getCity().equals("null"))
                    .filter(data -> data.getPopulationCounts().stream()
                            .anyMatch(p -> !Objects.isNull(p) && isNumeric(p.getValue()) && Double.parseDouble(p.getValue()) > population))
                    .map(City::getCity)
                    .collect(Collectors.toList());

        //System.out.println("Population API - " + cityList);
        return CompletableFuture.completedFuture(cityList);
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
