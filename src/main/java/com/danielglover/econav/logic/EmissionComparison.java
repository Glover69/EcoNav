package com.danielglover.econav.logic;

import java.util.ArrayList;
import java.util.List;

public class EmissionComparison {
    private TransitRoute route;
    private ArrayList<ComparisonResult> results;


    public EmissionComparison(TransitRoute route){
        setRoute(route);
        this.results = new ArrayList<>();
    }


    // Comparing different vehicles on one route
    public void compareVehicles2(List<Vehicle> vehicles){
        results.clear();

        // Using the worst emissions as the base (in the loop)
        double baseEmissions = 0;

        for (Vehicle v: vehicles){
            if (v.getVehicleType().equalsIgnoreCase("Diesel")) {
                route.setVehicle(v);
                baseEmissions = route.calculateTotalEmissions();
                break;
            }
        }

        for (Vehicle v: vehicles){
            route.setVehicle(v);
            double emissions = route.calculateTotalEmissions();
            double energyCost = route.calculateTotalEnergyCost();

            double savings = baseEmissions - emissions;

            String thisParticularVehicle;

            if (v.vehicleName == null){
                thisParticularVehicle = v.getVehicleType() + " " + v.getVehicleCategory();
            }else{
                thisParticularVehicle = v.vehicleName;
            }



            // Create the result object and store it in the list
            ComparisonResult res = new ComparisonResult(thisParticularVehicle, emissions, energyCost, savings);
            results.add(res);
        }

        System.out.println("Comparison complete. " + results.size() + " vehicles analyzed.");
    }


    public void displayComparison(){
        if (results.isEmpty()){
            System.out.println("No results");

        }

        System.out.println("Emission Comparison Results: \n");

            // for (int i = 0; i < results.size(); i++ ){
            //     ComparisonResult compare = results.get(i);
            //     System.out.println(i + 1, compare.getVehicleType(), compare.getEmissions());
            // }

        System.out.println("----- Route Details ----- \n" +
                " Route: " + route.getRouteName() + "\n" +
                " Distance: " + route.getDistance() + "km" +  "\n" +
                " ------------------------------------------------ " + "\n"
        );

        for (ComparisonResult r: results){
            System.out.println(r.toString() + "\n" +
                    " ------------------------------------ " + "\n"
            );
        }

        ComparisonResult best = getBestOption();
        if (best != null){
            System.out.println("--------- BEST OPTION (Lowest Emissions) ---------");
            System.out.println(best.toString());
        }
    }

    public ComparisonResult getBestOption(){
        if (results.isEmpty()){
            return null;
        }

        ComparisonResult best = results.getFirst();
        for (ComparisonResult res: results){
            if (res.getTotalEmissions() < best.getTotalEmissions()){
                best = res;
            }
        }

        return best;
    }


    public ArrayList<ComparisonResult> getResults() {
        return results;
    }


    public void setRoute(TransitRoute route) {
        this.route = route;
    }

    public void setResults(ArrayList<ComparisonResult> results) {
        this.results = results;
    }
}