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

    // public void compareVehicles(List vehicle){
    //     double distance = route.getDistance();
//
    //     for (int i = 0; i < vehicle.size(); i++){
    //         Vehicle transport = vehicle.get(i);
//
    //         double emissionRate = TransitRoute.getCalculateEmissions(transport);
    //         double totalEmission = TransitRoute.getCalculateTotalEmissions(transport);
//
    //         results.add(new ComparisonResult(transport.getVehicleType(), totalEmission));
    //     }
//
    // }

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


    // Compare one vehicle on various routes

    public void compareRoutes(List<TransitRoute> routes){
        results.clear();

        // Set the first vehicle as the one being compared to
        double baseEmissions = 0;
        if (!routes.isEmpty()){
            baseEmissions = routes.getFirst().calculateTotalEmissions();
        }

        for (TransitRoute routeToCompare: routes){
            // Calculate both emissions and energy cost for this particular route
            double emissions = routeToCompare.calculateTotalEmissions();
            double energyCost = routeToCompare.calculateTotalEnergyCost();

            double savings = baseEmissions - emissions;

            // Note down this route and the car passing on it
            String thisParticularRoute = routeToCompare.getRouteName() + " (" + routeToCompare.getVehicle().getVehicleType() + ") ";

            // Create the result object and store it in the list
            ComparisonResult res = new ComparisonResult(thisParticularRoute, emissions, energyCost, savings);
            results.add(res);
        }

        System.out.println("Comparison complete. " + results.size() + " routes analyzed.");
    }



    // public void compareRoutes(List<TransitRoute> route){
//
    //     for (int i = 0; i < route.size(); i++ ){
    //         TransitRoute route = routes.get(i);
//
    //         double emissionRate = TransitRoute.getEmissions(transport);
    //         double totalEmission = TransitRoute.getTotalEmissions(transport);
//
    //         results.add(new ComparisonResult(transport.getVehicleType(), totalEmission));
//
    //     }
//
    // }

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

    public double calculateMaxSavings(){
        if (results.isEmpty()){
            return 0;
        }

        // Find highest and lowest emissions
        double maxEmissions = results.getFirst().getTotalEmissions();
        double minEmissions = results.getFirst().getTotalEmissions();

        for (ComparisonResult res: results){
            double em = res.getTotalEmissions();

            if (em > maxEmissions){
                maxEmissions = em;
            }

            if (em < minEmissions){
                minEmissions = em;
            }
        }


        return maxEmissions - minEmissions;
    }

    public ArrayList<ComparisonResult> getResults() {
        return results;
    }

    public TransitRoute getRoute() {
        return route;
    }

    public void setRoute(TransitRoute route) {
        this.route = route;
    }

    public void setResults(ArrayList<ComparisonResult> results) {
        this.results = results;
    }
}