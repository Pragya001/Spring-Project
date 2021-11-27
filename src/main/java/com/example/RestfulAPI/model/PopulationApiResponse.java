package com.example.RestfulAPI.model;

import lombok.Data;

import java.util.List;

@Data
public class PopulationApiResponse extends APIResponse {
    private List<City> data;
}
