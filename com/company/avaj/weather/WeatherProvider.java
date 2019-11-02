package com.company.avaj.weather;

import com.company.avaj.flyables.Coordinates;

import java.util.Random;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        Random random = new Random();
        int randomWeather = random.nextInt(weather.length);
        return weather[randomWeather];
    }
}
