package com.danielglover.econav.logic;

public class Bus extends Vehicle{

    public Bus(String vehicleType, Double emissionRate, Double energyCostPerKm){
        super(vehicleType, emissionRate, energyCostPerKm);
    }

    public String getVehicleCategory(){
        return "Bus";
    }

    @Override
    public String toString(){
        return "Vehicle : " + getVehicleType() + "\n" +
                "Emission rate : " + emissionRate + " kg CO2" + "\n" +
                "Energy cost per km : GHS " + energyCostPerKm + "\n";
    }
}
