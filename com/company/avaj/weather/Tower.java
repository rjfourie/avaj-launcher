package com.company.avaj.weather;

import com.company.avaj.flyables.Flyable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();
    private List<Flyable> deregister = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        deregister.add(flyable);
    }

    protected void conditionsChanged() throws IOException {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
        observers.removeAll(deregister);
    }
}
