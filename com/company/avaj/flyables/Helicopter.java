package com.company.avaj.flyables;

import com.company.avaj.sim.Simulator;
import com.company.avaj.weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    private String format = "Helicopter#" + this.name + "(" + this.id + ")";

    @Override
    public void updateConditions() {

        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2);
                Simulator.writer.println(format + ": Chopper like it's hot." );
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                Simulator.writer.println(format + ": Nothin like some good ol' rain." );
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                Simulator.writer.println(format + ": Ooh that's misty!" );
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12);
                Simulator.writer.println(format + ": Slice n dice snowflake." );
                break;
        }

        if (coordinates.getHeight() <= 0) {
            Simulator.writer.println(format + " landing.");
            this.weatherTower.unregister(this);
            Simulator.writer.println("Tower says: " + format + " is unregistered from tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: " + format + " registered to tower");
    }
}
