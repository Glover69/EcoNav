package com.danielglover.econav.logic.ai;

public class AIResponse {
    public String carName;
    public String carType;
    public String carCategory;
    public Double emissionRate;
    public Double energyCost;

    public AIResponse(String carName, String carType, String carCategory, Double emissionRate, Double energyCost) {
        this.carName = carName;
        this.carType = carType;
        this.carCategory = carCategory;
        this.emissionRate = emissionRate;
        this.energyCost = energyCost;
    }
}
