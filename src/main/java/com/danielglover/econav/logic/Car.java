package com.danielglover.econav.logic;

public class Car extends Vehicle{
    private String vehicleCategory;

    public Car(String vehicleType, Double emissionRate, Double energyCostPerKm, String category){
        super(vehicleType, emissionRate, energyCostPerKm);
    }

    public String getVehicleCategory(){
        return vehicleCategory;
    }

    @Override
    public String toString(){
         return "Vehicle : " + getVehicleType() + "\n" +
                "Emission rate : " + emissionRate + " kg CO2" + "\n" +
                "Energy cost per km : GHS " + energyCostPerKm + "\n";
    }
}
