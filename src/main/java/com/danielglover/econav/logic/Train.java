package com.danielglover.econav.logic;

public class Train extends Vehicle{

    public Train(String vehicleType, Double emissionRate, Double energyCostPerKm){
        super(vehicleType, emissionRate, energyCostPerKm);
    }

    public String getVehicleCategory(){
        return "Train";
    }

    @Override
    public String toString(){
         return "Vehicle : " + getVehicleType() + "\n" +
                "Emission rate : " + emissionRate + " kg CO2" + "\n" +
                "Energy cost per km : GHS " + energyCostPerKm + "\n";
    }
}
