package com.example.RestfulAPI.model;

import lombok.Data;
import java.util.List;

@Data
public class City {
    private String city;
    private String country;
    private List<Population> populationCounts;
}
