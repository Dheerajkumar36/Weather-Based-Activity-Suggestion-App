package com.example.weatherapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @Value("${WEATHER_API_KEY}")
    private String apiKey;

    @CrossOrigin(origins = "*")
    @GetMapping("/activity")
    public Map<String, String> getActivity(@RequestParam String city) throws Exception {
        Map<String, String> map = new HashMap<>();

        // Call OpenWeatherMap API
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || response.get("main") == null) {
            map.put("weather", "Unknown");
            map.put("temperature", "Unknown");
            map.put("activity", "City not found");
            return map;
        }

        Map<String, Object> main = (Map<String, Object>) response.get("main");
        double temp = ((Number) main.get("temp")).doubleValue();

        map.put("weather", temp > 25 ? "Sunny" : "Cloudy/Rainy");
        map.put("temperature", temp + "°C");

        if (temp > 30) map.put("activity", "Go swimming or stay cool");
        else if (temp > 20) map.put("activity", "Go for a walk");
        else map.put("activity", "Stay home and read a book");

        return map;
    }
}
