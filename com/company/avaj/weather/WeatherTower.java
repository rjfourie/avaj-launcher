package com.company.avaj.weather;

import com.company.avaj.flyables.Coordinates;

import java.io.IOException;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() throws IOException {
        conditionsChanged();
    }
}
