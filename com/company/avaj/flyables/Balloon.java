package com.company.avaj.flyables;

import com.company.avaj.sim.Simulator;
import com.company.avaj.weather.WeatherTower;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    private String format = "Balloon#" + this.name + "(" + this.id + ")";

    @Override
    public void updateConditions() {

        switch (weatherTower.getWeather(coordinates)) {
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4);
                Simulator.writer.println(format + ": I hope I don't explode!" );
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5);
                Simulator.writer.println(format + ": go away rain, you making me drop" );
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3);
                Simulator.writer.println(format + ": Inside the fogs, you think better and thus you see better!" );
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15);
                Simulator.writer.println(format + ": Ice ice baby, wait stop I'm losing height!" );
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
