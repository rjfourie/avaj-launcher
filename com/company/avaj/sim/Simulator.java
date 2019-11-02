package com.company.avaj.sim;

import com.company.avaj.flyables.AircraftFactory;
import com.company.avaj.flyables.Flyable;
import com.company.avaj.weather.WeatherTower;

import java.io.*;

public class Simulator {

    public static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new File("simulation.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(args[0]));
            String line = file.readLine();

            if(line == null)
                System.out.println("The file can't be empty");

            if (line != null) {
                WeatherTower weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                if (simulations <= 0) {
                    System.out.println("Invalid simulations count " + simulations);
                    System.exit(1);
                }
                while ((line = file.readLine()) != null) {
                    AircraftFactory aircraftFactory = new AircraftFactory();
                    Flyable flyable = aircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    weatherTower.register(flyable);
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= simulations; i++) {
                    weatherTower.changeWeather();
                }
            }
            writer.close();
            file.close();
            System.out.println("Simulation generated successfully.");
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't find file: " + args[0]);
        }
        catch (IOException e) {
            System.out.println("There was an error while reading the file: (" + args[0] + ")");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing information in (" + args[0] + ")");
        }
        catch (NumberFormatException e) {
            System.out.println("Incorrect data insert in (" + args[0] + ")");
        }
    }
}
