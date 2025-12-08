package com.danielglover.econav.logic;

public abstract class Vehicle {
    protected String vehicleName;
    protected String vehicleType;
    protected Double emissionRate;
    protected Double energyCostPerKm;

    public Vehicle(String vehicleName, String vehicleType, Double emissionRate, Double energyCostPerKm){
        this(vehicleType, emissionRate, energyCostPerKm);
        this.vehicleName = vehicleName;
    }

    public Vehicle(String vehicleType, Double emissionRate, Double energyCostPerKm){
        setVehicleType(vehicleType);
        setEmissionRate(emissionRate);
        setEnergyCostPerKm(energyCostPerKm);
    }


    // Helper methods

    public Double calculateEmissions(Double distance){
        return emissionRate * distance;
    }

    public Double calculateEnergyCost(Double distance){
        return energyCostPerKm * distance;
    }


    // Getters & Setters

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getEmissionRate() {
        return emissionRate;
    }

    public void setEmissionRate(Double emissionRate) {
        this.emissionRate = emissionRate;
    }

    public Double getEnergyCostPerKm() {
        return energyCostPerKm;
    }

    public void setEnergyCostPerKm(Double energyCostPerKm) {
        this.energyCostPerKm = energyCostPerKm;
    }

    public abstract String getVehicleCategory();

}
