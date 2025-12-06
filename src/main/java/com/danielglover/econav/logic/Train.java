package com.danielglover.econav.logic;

public class Train extends Vehicle{

    public Train(String vehicleType, Double emissionRate, Double energyCostPerKm){
        super(vehicleType, emissionRate, energyCostPerKm);
    }

    public String getVehicleCategory(){
        return "Train";
    }
}
