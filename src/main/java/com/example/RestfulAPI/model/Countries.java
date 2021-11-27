package com.example.RestfulAPI.model;

import lombok.Data;

import java.util.List;

@Data
public class Countries {
    private String country;
    private List<String> cities;
}
