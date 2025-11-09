package com.example.weather;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private final WeatherService service = new WeatherService();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
        model.addAttribute("city", city);
        model.addAttribute("weather", service.getWeather(city));
        model.addAttribute("activity", service.suggestActivity(city));
        return "result";
    }
}
