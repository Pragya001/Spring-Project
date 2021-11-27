package com.example.RestfulAPI.controller;


import org.junit.jupiter.api.Test;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryResourceTest {

    @Test
    public void testSpringVersion() {
        assertEquals("5.3.13", SpringVersion.getVersion());
    }
}
