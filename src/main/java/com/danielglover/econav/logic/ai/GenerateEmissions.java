package com.danielglover.econav.logic.ai;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GenerateEmissions {
    private String res;

    public void generateOutput(String carModel){
        Client client = Client.builder().apiKey("AIzaSyA-WwlbGylgLMNrATkqv9hb4-xb3VFor5A").build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        "\"What is the emission rate (in kg CO2 per km) and energy cost (per km) for a " + carModel + "\n" +
                                "Respond ONLY with numbers in this format: car_name,car_type,car_category,emission_rate,energy_cost. And be" +
                                "consistent with your values. If it's Petrol, just say Diesel.\"",
                        null);

        System.out.println(response.text());
        this.res = response.text();
    }

    public String getRes(){
        return this.res;
    }

    public AIResponse getFormattedRes() {
        String[] arr = res.split(",");

        return new AIResponse(arr[0], arr[1], arr[2], Double.parseDouble(arr[3]), Double.parseDouble(arr[4]));
    }
}


