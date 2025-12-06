package com.danielglover.econav.logic;

public class Bus extends Vehicle{

    public Bus(String vehicleType, Double emissionRate, Double energyCostPerKm){
        super(vehicleType, emissionRate, energyCostPerKm);
    }

    public String getVehicleCategory(){
        return "Bus";
    }
}
