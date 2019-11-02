package com.company.avaj.flyables;

public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
            case "Balloon":
                return new Balloon(name, coordinates);
            default:
                System.out.println(type + "is not a valid aircraft type.");
                return null;
        }
    }
}
