package com.danielglover.econav.logic;

import java.text.DecimalFormat;

public class ComparisonResult {
    private String vehicleType;
    private Double totalEmissions;
    private Double energyCost;
    private Double savings;

    // Constructor

    public ComparisonResult(String vehicleType, Double totalEmissions, Double energyCost, Double savings){
        setVehicleType(vehicleType);
        setTotalEmissions(totalEmissions);
        setEnergyCost(energyCost);
        setSavings(savings);
    }



    // Getters & setters
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getTotalEmissions() {
        return totalEmissions;
    }

    public void setTotalEmissions(Double totalEmissions) {
        this.totalEmissions = totalEmissions;
    }

    public Double getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(Double energyCost) {
        this.energyCost = energyCost;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }


    // Helper methods

    // Display information as a string (formatted)
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");

        return "Vehicle : " + getVehicleType() + "\n" +
                "Total emissions : " + df.format(getTotalEmissions()) + " kg CO2" + "\n" +
                "Energy cost : GHS " + df.format(getEnergyCost()) + "\n" +
                "Savings : " + df.format(getSavings()) + " kg CO2";
    }
}
