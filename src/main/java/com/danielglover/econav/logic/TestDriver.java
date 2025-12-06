package com.danielglover.econav.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TestDriver {
    public static void main(String[] args) {
        // Create vehicles
        Bus electricBus = new Bus("Electric", 0.34, 0.15);
        Bus dieselBus = new Bus("Diesel", 0.85, 0.30);
        Bus hybridBus = new Bus("Hybrid", 0.55, 0.20);

        // Create route
        TransitRoute cityRoute = new TransitRoute("City Center", 50, 12, electricBus);

        // Create comparison
        EmissionComparison comparison = new EmissionComparison(cityRoute);

        // Compare all vehicles on this route
        ArrayList<Vehicle> vehicleList = new ArrayList<>(Arrays.asList(electricBus, dieselBus, hybridBus));

        comparison.compareVehicles2(vehicleList);

        comparison.displayComparison();
    }
}
