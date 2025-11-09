package com.example.weatheractivityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class WeatherController {

    private final String API_KEY = "04bb5404f6f81901daf5e7ed0d4e09a4";

    @GetMapping("/activity")
    public String getActivity(@RequestParam String city, Model model) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        String weather = ((Map<String, Object>) ((java.util.List<Object>) response.get("weather")).get(0)).get("main").toString();

        String activity;
        switch (weather.toLowerCase()) {
            case "rain" -> activity = "Stay home and watch a movie!";
            case "clear" -> activity = "Go for a walk outside!";
            case "clouds" -> activity = "Perfect day to read a book!";
            default -> activity = "Do whatever you love!";
        }

        model.addAttribute("city", city);
        model.addAttribute("weather", weather);
        model.addAttribute("activity", activity);

        return "result"; // return the HTML page
    }
}
