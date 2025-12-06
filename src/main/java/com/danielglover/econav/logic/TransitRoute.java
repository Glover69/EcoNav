package com.danielglover.econav.logic;

public class TransitRoute {
    // Data Fields
    private String routeName;
    private double distance;
    private int numberOfStops;
    private Vehicle vehicle;
    // Optional : Passenger Load
    // Optional : Time of the Day to consider stops and traffic


    // Constructors
    public TransitRoute(){}

    public TransitRoute(String routeName, double distance, int numberOfStops, Vehicle vehicle){
        this.routeName = routeName;
        this.distance = distance;
        this.numberOfStops = numberOfStops;
        this.vehicle = vehicle;
    }


    // Getters
    public String getRouteName() {
        return routeName;
    }

    public double getDistance() {
        return distance;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getRouteDetails() {
        return "----- Route Details ----- \n" +
                "Route: " + routeName + "\n" +
                "Distance: " + distance + "\n" +
                "Number of stops (if any): " + numberOfStops + "\n" +
                "Vehicle: " + vehicle.getVehicleType();
    }

    // Setters

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // We return an immediate string for the route details so the variable isn't needed in the first place
//    public void setRouteDetails(String routeDetails) {
//        this.routeDetails = routeDetails;
//    }

    // Methods
    public double calculateTotalEmissions(){
        if (vehicle == null){
            return 0;
        }

        return vehicle.calculateEmissions(distance);
        // return 1;
        // Vehicle.getEmissionsFactorPerKilometer(in motion) * Vehicle.getDistance + Vehicle.getEmissionsStationary * numberOfStops
    }

    public double calculateTotalEnergyCost(){
        if (vehicle == null){
            return 0;
        }

        return vehicle.calculateEnergyCost(distance);
    }

}
