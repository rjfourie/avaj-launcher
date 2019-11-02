package com.company.avaj.flyables;

import com.company.avaj.sim.Simulator;
import com.company.avaj.weather.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    private String format = "JetPlane#" + this.name + "(" + this.id + ")";

    @Override
    public void updateConditions() {

        switch (weatherTower.getWeather(coordinates)){
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2);
                Simulator.writer.println(format + ": Great balls of fire!");
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight());
                Simulator.writer.println(format + ": It's raining harder than a cow pissing on a flat rock.");
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight());
                Simulator.writer.println(format + ": This fog is making it difficult to see.");
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7);
                Simulator.writer.println(format + ": Adjust the temperature! it just got chili." );
                break;
        }

        if (coordinates.getHeight() <= 0) {
            Simulator.writer.println(format + " landing.");
            this.weatherTower.unregister(this);
            Simulator.writer.println("Tower says: " + format + " unregistered from tower");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: " + format + " registered to tower");
    }
}
