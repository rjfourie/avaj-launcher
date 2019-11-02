package com.company.avaj.flyables;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    protected Aircraft(String setName, Coordinates setCoordinates) {
        this.name = setName;
        this.coordinates = setCoordinates;
        this.id = nextId();
    }

    private long nextId() {
        return ++idCounter;
    }
}
