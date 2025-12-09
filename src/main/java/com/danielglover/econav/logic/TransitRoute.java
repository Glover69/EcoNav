package com.danielglover.econav.logic;

public class TransitRoute {
    // Data Fields
    private String routeName;
    private double distance;
    private Vehicle vehicle;
    // Optional : Passenger Load
    // Optional : Time of the Day to consider stops and traffic


    // Constructors
    public TransitRoute(){}

    public TransitRoute(String routeName, double distance, Vehicle vehicle){
        this.routeName = routeName;
        this.distance = distance;
        this.vehicle = vehicle;
    }


    // Getters
    public String getRouteName() {
        return routeName;
    }

    public double getDistance() {
        return distance;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getRouteDetails() {
        return "----- Route Details ----- \n" +
                "Route: " + routeName + "\n" +
                "Distance: " + distance + "\n" +
                "Vehicle: " + vehicle.getVehicleType();
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // Methods
    public double calculateTotalEmissions(){
        if (vehicle == null){
            return 0;
        }

        return vehicle.calculateEmissions(distance);
    }

    public double calculateTotalEnergyCost(){
        if (vehicle == null){
            return 0;
        }

        return vehicle.calculateEnergyCost(distance);
    }

}
