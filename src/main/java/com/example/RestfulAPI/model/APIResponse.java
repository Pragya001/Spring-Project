package com.example.RestfulAPI.model;

import lombok.Data;

@Data
public class APIResponse {
    private Boolean error;
    private String msg;
}
