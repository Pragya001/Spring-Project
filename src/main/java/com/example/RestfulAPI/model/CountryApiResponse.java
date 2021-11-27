package com.example.RestfulAPI.model;

import lombok.Data;

import java.util.List;

@Data
public class CountryApiResponse extends APIResponse {
    private List<Countries> data;
}
