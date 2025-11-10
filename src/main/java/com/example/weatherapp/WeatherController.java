package com.example.weatherapp;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @CrossOrigin(origins = "*")
    @GetMapping("/activity")
    public Map<String, String> getActivity(@RequestParam String city) {
        Map<String, String> map = new HashMap<>();
        if (city.equalsIgnoreCase("Patna")) {
            map.put("weather", "Sunny");
            map.put("activity", "Go for a walk");
        } else if (city.equalsIgnoreCase("Delhi")) {
            map.put("weather", "Rainy");
            map.put("activity", "Stay home and read a book");
        } else {
            map.put("weather", "Cloudy");
            map.put("activity", "Watch a movie");
        }
        return map;
    }
}
