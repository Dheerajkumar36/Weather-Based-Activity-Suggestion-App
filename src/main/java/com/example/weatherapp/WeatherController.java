package com.example.weatherapp;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @CrossOrigin(origins = "*")
    @GetMapping("/activity")
    public Map<String, Object> getActivity(@RequestParam String city) {

        String apiKey = System.getenv("WEATHER_API_KEY"); // Your API key from environment

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                     + city + "&appid=" + apiKey + "&units=metric";

        JsonNode root = restTemplate.getForObject(url, JsonNode.class);

        String weather = root.path("weather").get(0).path("description").asText();
        double temp = root.path("main").path("temp").asDouble(); // Temperature in °C

        String activity = suggestActivity(weather);

        Map<String, Object> result = new HashMap<>();
        result.put("weather", weather);
        result.put("temperature", temp + "°C");
        result.put("activity", activity);

        return result;
    }

    private String suggestActivity(String weather) {
        weather = weather.toLowerCase();
        if (weather.contains("rain")) return "Stay inside and enjoy tea or music.";
        if (weather.contains("sunny")) return "Great time to go for a walk or cycling.";
        if (weather.contains("cloud")) return "Maybe go for a relaxed outdoor stroll.";
        return "Check conditions locally and decide.";
    }
}
