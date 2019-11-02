package com.company.avaj.flyables;

import com.company.avaj.weather.WeatherTower;

import java.io.IOException;

public interface Flyable {
    void updateConditions() throws IOException;
    void registerTower(WeatherTower weatherTower);
}
