package com.danielglover.econav;

import com.danielglover.econav.logic.*;
import com.danielglover.econav.logic.ai.AIResponse;
import com.danielglover.econav.logic.ai.GenerateEmissions;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {
    ScrollPane sp;
    VBox mainContent;

    // Helper method for using SVG's
    private Region createSVGIcon(String svgPath, double size, String color) {
        Region icon = new Region();
        icon.setPrefSize(size, size);
        icon.setMaxSize(size, size);
        icon.setMinSize(size, size);
        icon.setStyle(String.format("-fx-shape: \"%s\"; -fx-background-color: %s;", svgPath, color));
        return icon;
    }

    public static final String logo = "M224,224H182.94l-6.3-44.12,3.24,1.91a16,16,0,0,0,21.91-5.67l12-20.34a16,16,0,0,0-5.67-21.91l-35-20.61,40.69-69.13a16,16,0,0,0-5.67-21.91l-20.34-12a16,16,0,0,0-21.91,5.67l-20.61,35L76.12,10.22a16,16,0,0,0-21.91,5.67l-12,20.33a16,16,0,0,0,5.67,21.92l35,20.61L42.21,147.88a16,16,0,0,0,5.67,21.91l20.34,12a15.57,15.57,0,0,0,10.58,2L73.06,224H32a8,8,0,0,0,0,16H224a8,8,0,0,0,0-16Zm-24-76.34L188,168l-69.13-40.69,12-20.35ZM179.66,24,200,36l-40.69,69.14L139,93.17ZM56,44.35,68,24,137.14,64.7l-12,20.35ZM76.34,168,56,156,96.69,86.86l20.36,12Zm12.88,56L98,162.8l12.77-21.7L159,169.5l7.79,54.5Z";
    public static final String location = "M128,64a40,40,0,1,0,40,40A40,40,0,0,0,128,64Zm0,64a24,24,0,1,1,24-24A24,24,0,0,1,128,128Zm0-112a88.1,88.1,0,0,0-88,88c0,31.4,14.51,64.68,42,96.25a254.19,254.19,0,0,0,41.45,38.3,8,8,0,0,0,9.18,0A254.19,254.19,0,0,0,174,200.25c27.45-31.57,42-64.85,42-96.25A88.1,88.1,0,0,0,128,16Zm0,206c-16.53-13-72-60.75-72-118a72,72,0,0,1,144,0C200,161.23,144.53,209,128,222Z";
    public static final String navigation = "M237.33,106.21,61.41,41l-.16-.05A16,16,0,0,0,40.9,61.25a1,1,0,0,0,.05.16l65.26,175.92A15.77,15.77,0,0,0,121.28,248h.3a15.77,15.77,0,0,0,15-11.29l.06-.2,21.84-78,78-21.84.2-.06a16,16,0,0,0,.62-30.38ZM149.84,144.3a8,8,0,0,0-5.54,5.54L121.3,232l-.06-.17L56,56l175.82,65.22.16.06Z";
    public static final String route = "M200,168a32.06,32.06,0,0,0-31,24H72a32,32,0,0,1,0-64h96a40,40,0,0,0,0-80H72a8,8,0,0,0,0,16h96a24,24,0,0,1,0,48H72a48,48,0,0,0,0,96h97a32,32,0,1,0,31-40Zm0,48a16,16,0,1,1,16-16A16,16,0,0,1,200,216Z";
    public static final String stops = "M188,88a27.75,27.75,0,0,0-12,2.71V60a28,28,0,0,0-41.36-24.6A28,28,0,0,0,80,44v6.71A27.75,27.75,0,0,0,68,48,28,28,0,0,0,40,76v76a88,88,0,0,0,176,0V116A28,28,0,0,0,188,88Zm12,64a72,72,0,0,1-144,0V76a12,12,0,0,1,24,0v44a8,8,0,0,0,16,0V44a12,12,0,0,1,24,0v68a8,8,0,0,0,16,0V60a12,12,0,0,1,24,0v68.67A48.08,48.08,0,0,0,120,176a8,8,0,0,0,16,0,32,32,0,0,1,32-32,8,8,0,0,0,8-8V116a12,12,0,0,1,24,0Z";
    public static final String bus = "M184,32H72A32,32,0,0,0,40,64V208a16,16,0,0,0,16,16H80a16,16,0,0,0,16-16V192h64v16a16,16,0,0,0,16,16h24a16,16,0,0,0,16-16V64A32,32,0,0,0,184,32ZM56,176V120H200v56Zm0-96H200v24H56ZM72,48H184a16,16,0,0,1,16,16H56A16,16,0,0,1,72,48Zm8,160H56V192H80Zm96,0V192h24v16Zm-72-60a12,12,0,1,1-12-12A12,12,0,0,1,104,148Zm72,0a12,12,0,1,1-12-12A12,12,0,0,1,176,148Zm72-68v24a8,8,0,0,1-16,0V80a8,8,0,0,1,16,0ZM24,80v24a8,8,0,0,1-16,0V80a8,8,0,0,1,16,0Z";
    public static final String aiLogo = "M224.32,114.24a56,56,0,0,0-60.07-76.57A56,56,0,0,0,67.93,51.44a56,56,0,0,0-36.25,90.32A56,56,0,0,0,69,217,56.39,56.39,0,0,0,83.59,219a55.75,55.75,0,0,0,8.17-.61,56,56,0,0,0,96.31-13.78,56,56,0,0,0,36.25-90.32ZM182.85,54.43a40,40,0,0,1,28.56,48c-.95-.63-1.91-1.24-2.91-1.81L164,74.88a8,8,0,0,0-8,0l-44,25.41V81.81l40.5-23.38A39.76,39.76,0,0,1,182.85,54.43ZM144,137.24l-16,9.24-16-9.24V118.76l16-9.24,16,9.24ZM80,72a40,40,0,0,1,67.53-29c-1,.51-2,1-3,1.62L100,70.27a8,8,0,0,0-4,6.92V128l-16-9.24ZM40.86,86.93A39.75,39.75,0,0,1,64.12,68.57C64.05,69.71,64,70.85,64,72v51.38a8,8,0,0,0,4,6.93l44,25.4L96,165,55.5,141.57A40,40,0,0,1,40.86,86.93ZM73.15,201.57a40,40,0,0,1-28.56-48c.95.63,1.91,1.24,2.91,1.81L92,181.12a8,8,0,0,0,8,0l44-25.41v18.48l-40.5,23.38A39.76,39.76,0,0,1,73.15,201.57ZM176,184a40,40,0,0,1-67.52,29.05c1-.51,2-1.05,3-1.63L156,185.73a8,8,0,0,0,4-6.92V128l16,9.24Zm39.14-14.93a39.75,39.75,0,0,1-23.26,18.36c.07-1.14.12-2.28.12-3.43V132.62a8,8,0,0,0-4-6.93l-44-25.4,16-9.24,40.5,23.38A40,40,0,0,1,215.14,169.07Z";
    public static final String trophy = "M232,64H208V48a8,8,0,0,0-8-8H56a8,8,0,0,0-8,8V64H24A16,16,0,0,0,8,80V96a40,40,0,0,0,40,40h3.65A80.13,80.13,0,0,0,120,191.61V216H96a8,8,0,0,0,0,16h64a8,8,0,0,0,0-16H136V191.58c31.94-3.23,58.44-25.64,68.08-55.58H208a40,40,0,0,0,40-40V80A16,16,0,0,0,232,64ZM48,120A24,24,0,0,1,24,96V80H48v32q0,4,.39,8Zm144-8.9c0,35.52-29,64.64-64,64.9a64,64,0,0,1-64-64V56H192ZM232,96a24,24,0,0,1-24,24h-.5a81.81,81.81,0,0,0,.5-8.9V80h24Z";
    public static final String leaf = "M223.45,40.07a8,8,0,0,0-7.52-7.52C139.8,28.08,78.82,51,52.82,94a87.09,87.09,0,0,0-12.76,49c.57,15.92,5.21,32,13.79,47.85l-19.51,19.5a8,8,0,0,0,11.32,11.32l19.5-19.51C81,210.73,97.09,215.37,113,215.94q1.67.06,3.33.06A86.93,86.93,0,0,0,162,203.18C205,177.18,227.93,116.21,223.45,40.07ZM153.75,189.5c-22.75,13.78-49.68,14-76.71.77l88.63-88.62a8,8,0,0,0-11.32-11.32L65.73,179c-13.19-27-13-54,.77-76.71,22.09-36.47,74.6-56.44,141.31-54.06C210.2,114.89,190.22,167.41,153.75,189.5Z";
    private static final int CARD_WIDTH = 300;

    private TextField nameField;
    private TextField distanceField;
    private TextField stopsField;
    private TextField aiField;

    private FlowPane vehicleFlowPane;

    ArrayList<Vehicle> vehicles = new ArrayList<>();
    TransitRoute routeInputted;
    Button compareBtn;
    private VBox aiSection;
    private VBox aiResultsBox = null;
    GenerateEmissions gen = new GenerateEmissions();

    EmissionComparison comp;
    HBox bestOptionContainer;

    @Override
    public void start(Stage stage) {


        sp = new ScrollPane();

        sp.setFitToWidth(true);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        mainContent = new VBox(30); // 30px spacing
        mainContent.setStyle("-fx-background-color: #F0F5F8; -fx-alignment: center;");

        VBox header = createTopPanel();

        VBox routeConfig = createRouteConfigPanel();

        aiSection = createAIConfigPanel();

        VBox vehicleSelection = createVehicleSelectionPanel();





        // Add all sections
        mainContent.getChildren().addAll(header, routeConfig, aiSection, vehicleSelection);
        // mainContent.setStyle("-fx-padding: 0px 0px 20px 0px;");

        sp.setContent(mainContent);


        // Create scene
        Scene scene = new Scene(sp, 1200, 1100);
        stage.setTitle("EcoNav");
        stage.setScene(scene);
        stage.show();
    }



    public VBox createTopPanel(){
        VBox main = new VBox(6);
        main.setStyle("-fx-min-width: 100%; -fx-padding: 22px 12px 22px 12px; -fx-background-color: #178B63; -fx-alignment: center");

        // Content to add to the top panel
        HBox titleContainer = new HBox(12);
        titleContainer.setStyle("-fx-alignment: center;");

        // Create SVG path
        Region icon = createSVGIcon(logo, 20, "#fff");


        Label title = new Label("Public Transit Route Eco-Analyzer");
        title.setStyle("-fx-font-size: 26px; -fx-text-fill: #fff; -fx-font-family: 'Outfit Bold'; -fx-font-weight: bold;");

        Label subTitle = new Label("Compare emissions and find the greenest transit option for your route");
        subTitle.setStyle("-fx-text-fill: #fff; -fx-font-size: 16px; -fx-alignment: center; -fx-font-family: 'Inter'; -fx-font-weight: regular;");

        titleContainer.getChildren().addAll(icon, title);
        main.getChildren().addAll(titleContainer, subTitle);

        return main;
    }


     public VBox createRouteConfigPanel(){
        VBox panel = new VBox(20);
        panel.setStyle("-fx-max-width: 100%");


         HBox titleLabel = new HBox(8);
         titleLabel.setAlignment(Pos.CENTER);
         Region routeIcon = createSVGIcon(route, 18, "#12B77F");
         panel.setStyle("-fx-background-color: white; -fx-padding: 30px;");

         Label sectionTitle = new Label("Route Configuration");
         sectionTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

         titleLabel.getChildren().addAll(routeIcon, sectionTitle);

         // Form fields
         HBox formGroup = new HBox(20);
         formGroup.setStyle("-fx-min-width: 100%; -fx-alignment: center;");

         VBox inputGroupOne = new VBox(4);

         HBox labelGroupOne = new HBox(4);


         Region locationLogo = createSVGIcon(location, 15, "#677F77");
         Label nameLabel = new Label("Route Name:");

         labelGroupOne.getChildren().addAll(locationLogo, nameLabel);

         nameLabel.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px;");
         nameField = new TextField();
         nameField.textProperty().addListener((event) -> {
             validateAndUpdateButton();
         });
         nameField.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #D0D7E3; -fx-border-width: 1.5; -fx-padding: 10 14; -fx-font-size: 14px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");

         nameField.setPromptText("e.g., City Center Loop");

         inputGroupOne.getChildren().addAll(labelGroupOne, nameField);


         VBox inputGroupTwo = new VBox(4);
         HBox labelGroupTwo = new HBox(4);


         Region navLogo = createSVGIcon(navigation, 15, "#677F77");
         Label distanceLabel = new Label("Distance (km):");

         labelGroupTwo.getChildren().addAll(navLogo, distanceLabel);

         distanceLabel.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px;");
         distanceField = new TextField();
         distanceField.textProperty().addListener((event) -> {
             validateAndUpdateButton();
         });
         distanceField.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #D0D7E3; -fx-border-width: 1.5; -fx-padding: 10 14; -fx-font-size: 14px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");

         distanceField.setPromptText("e.g., 50");

         inputGroupTwo.getChildren().addAll(labelGroupTwo, distanceField);


         VBox inputGroupThree = new VBox(4);
         HBox labelGroupThree = new HBox(4);

         inputGroupThree.setStyle("-fx-min-width: 100%");


         Region stopsLogo = createSVGIcon(stops, 15, "#677F77");
         Label stopsLabel = new Label("Number of Stops:");

         labelGroupThree.getChildren().addAll(stopsLogo, stopsLabel);

         stopsLabel.setStyle("-fx-font-family: 'Inter'; -fx-font-size: 14px;");
         stopsField = new TextField();
         stopsField.textProperty().addListener((event) -> {
             validateAndUpdateButton();
         });
         stopsField.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #D0D7E3; -fx-border-width: 1.5; -fx-padding: 10 14; -fx-font-size: 14px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");

         stopsField.setPromptText("e.g., 12");

         inputGroupThree.getChildren().addAll(labelGroupThree, stopsField);


         formGroup.getChildren().addAll(inputGroupOne, inputGroupTwo);


         panel.getChildren().addAll(titleLabel, formGroup);


        return panel;
    }



    public VBox createAIConfigPanel(){
        VBox panel = new VBox(20);
        panel.setStyle("-fx-max-width: 100%");

        VBox headerAndSub = new VBox(5);

        HBox titleLabel = new HBox(8);
        titleLabel.setAlignment(Pos.CENTER_LEFT);
        Region routeIcon = createSVGIcon(aiLogo, 18, "#12B77F");
        panel.setStyle("-fx-background-color: white; -fx-padding: 40px;");

        Label sectionTitle = new Label("AI Vehicle Lookup");
        sectionTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        titleLabel.getChildren().addAll(routeIcon, sectionTitle);

        Label subTitle = new Label("Ask AI for the emission rate of any vehicle not in our list");
        subTitle.setStyle("-fx-font-size: 16px; -fx-alignment: center; -fx-font-family: 'Inter'; -fx-font-weight: regular;");

        headerAndSub.getChildren().addAll(titleLabel, subTitle);

        // Form fields
        HBox inputPlusButton = new HBox(10);


        aiField = new TextField();
        aiField.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #D0D7E3; -fx-border-width: 1.5; -fx-padding: 10 14; -fx-font-size: 14px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");
        aiField.setPromptText("e.g., Mercedes Benz E300");

        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-padding: 10px 18px 10px 18px; -fx-background-color: #178B63; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: semibold; -fx-font-size: 14px; -fx-font-family: 'Outfit Semibold'; -fx-text-fill: white;");

        HBox.setHgrow(aiField, Priority.ALWAYS);
        inputPlusButton.setAlignment(Pos.CENTER_LEFT);
        inputPlusButton.getChildren().addAll(aiField, searchBtn);

        searchBtn.setOnAction((e) -> {
            String carModel = aiField.getText().trim();
            gen.generateOutput(carModel);

            if (!gen.getRes().isEmpty()){

                // Remove the previous result if it exists
                if (aiResultsBox != null) {
                    aiSection.getChildren().remove(aiResultsBox);
                }
                aiResultsBox = createAIResultCard();
                panel.getChildren().addAll(aiResultsBox);
            }
        });



        panel.getChildren().addAll(headerAndSub, inputPlusButton);

        return panel;
    }


     public VBox createVehicleSelectionPanel(){
         VBox panel = new VBox(20);
         panel.setStyle("-fx-min-width: 100%; -fx-alignment: center;");

         HBox titleLabel = new HBox(8);
         titleLabel.setAlignment(Pos.CENTER);
         Region routeIcon = createSVGIcon(bus, 18, "#12B77F");
         panel.setStyle("-fx-background-color: white; -fx-padding: 30px;");

         Label sectionTitle = new Label("Select Vehicles to Compare");
         sectionTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

         titleLabel.getChildren().addAll(routeIcon, sectionTitle);

         VBox vehicleSelectionContainer = new VBox(20);

         // Rows
         // HBox busRow = new HBox(15);
         // busRow.setAlignment(Pos.CENTER);
         // HBox trainRow = new HBox(15);
         // trainRow.setAlignment(Pos.CENTER);

         // New method (FlowPane)
         vehicleFlowPane = new FlowPane();
         vehicleFlowPane.setHgap(15);
         vehicleFlowPane.setVgap(15);
         vehicleFlowPane.setAlignment(Pos.CENTER);


         // Various vehicle cards
         VBox electricBusCard = createVehicleCard("Electric Bus", "0.12kg CO2/km", 0.12,  "/images/Bus.png", "#EAFAF5", new Bus("Electric", 0.12, 0.12));
         VBox dieselBusCard = createVehicleCard("Diesel Bus", "0.89kg CO2/km", 0.89, "/images/OncomingBus.png", "", new Bus("Diesel", 0.89, 0.12));
         VBox hybridBusCard = createVehicleCard("Hybrid Bus", "0.34kg CO2/km", 0.34, "/images/Minibus.png", "", new Bus("Hybrid", 0.34, 0.12));
         VBox electricTrainCard = createVehicleCard("Electric Train", "0.04kg CO2/km", 0.04, "/images/Train.png", "", new Train("Electric", 0.04, 0.12));
         VBox dieselTrainCard = createVehicleCard("Diesel Train", "0.65kg CO2/km", 0.65, "/images/Locomotive.png", "", new Train("Diesel", 0.65, 0.12));

         // busRow.getChildren().addAll(electricBusCard, dieselBusCard, hybridBusCard);
         // trainRow.getChildren().addAll(electricTrainCard, dieselTrainCard);

         // now add all 5 vehicles to the Flow Pane
         vehicleFlowPane.getChildren().addAll(electricBusCard, dieselBusCard, hybridBusCard, electricTrainCard, dieselTrainCard);

         compareBtn = new Button("Compare Emissions");
         compareBtn.setDisable(true);
         compareBtn.setStyle("-fx-padding: 12px 16px 12px 16px; -fx-background-color: #178B63; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: semibold; -fx-font-size: 16px; -fx-font-family: 'Outfit Semibold'; -fx-text-fill: white;");

         compareBtn.setOnAction((event) -> {
             routeInputted = new TransitRoute(nameField.getText().trim(), Integer.parseInt(distanceField.getText().trim()), vehicles.getFirst());

             // Create comparison
             comp = new EmissionComparison(routeInputted);

             comp.compareVehicles2(vehicles);
             comp.displayComparison();

             comp.getBestOption();

             // Remove previous result before showing new one
             if (bestOptionContainer != null) {
                 mainContent.getChildren().remove(bestOptionContainer);
             }
             bestOptionContainer = createBestOptionPanel(comp.getBestOption());
             mainContent.getChildren().add(bestOptionContainer);
         });


         panel.setAlignment(Pos.CENTER);
         vehicleSelectionContainer.getChildren().addAll(vehicleFlowPane);

         panel.getChildren().addAll(titleLabel, vehicleSelectionContainer, compareBtn);

         return panel;
     }


     public VBox createVehicleCard(String name, String emissionRate, double emissionValue, String iconPath, String color, Vehicle vehicle){
        VBox card = new VBox(6);
        card.setPrefWidth(CARD_WIDTH);
        card.setMinWidth(CARD_WIDTH);
        card.setMaxWidth(CARD_WIDTH);



        card.setBackground(Background.fill(Color.rgb(234, 250, 245)));
        card.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-border-color: #B5E5D2; -fx-background-radius: 8px; -fx-border-radius: 8px;");

        CheckBox checkbox = new CheckBox();


        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)));
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);


        Label vehicleName = new Label(name);
        vehicleName.setStyle("-fx-font-size: 18px; -fx-font-family: 'Outfit Bold'; -fx-font-weight: bold;");

        Label rate = new Label(emissionRate);
        rate.setStyle("-fx-font-size: 13px; -fx-text-fill: #6B7280;");

        card.getChildren().addAll(checkbox, imageView, vehicleName, rate);


         card.setOnMouseClicked(event -> {
             checkbox.setSelected(!checkbox.isSelected());
         });


         // We want to use the object for the vehicle being selected and add
         // it to the array of vehicles to be compared, since it is being selected

         // Else, we remove it from the array (if unchecking)

         checkbox.selectedProperty().addListener((observable, was, is) -> {
             if (is) {
                 if (!vehicles.contains(vehicle)) {
                     vehicles.add(vehicle);
                     System.out.println("Added: " + vehicle.getVehicleType() + " " + vehicle.getVehicleCategory());
                     validateAndUpdateButton();
                 }
             } else {
                 // Remove THIS vehicle from comparison list
                 vehicles.remove(vehicle);
                 System.out.println("Removed " + vehicle.getVehicleType() + " " + vehicle.getVehicleCategory());
                 validateAndUpdateButton();
             }
         });

        return card;
     }


     public VBox createAIResultCard(){

        AIResponse results = gen.getFormattedRes();
        VBox panel = new VBox(20);
        panel.setStyle("-fx-background-color: #ECF7F4; -fx-border-color: #B7E3D0; -fx-padding: 20px; -fx-background-radius: 8px; -fx-border-radius: 8px;");
        HBox topPart = new HBox();
        HBox bottomPart = new HBox(30);

        VBox topPartLeft = new VBox(3);


        Label name = new Label(results.carName);
        name.setStyle("-fx-font-family: 'Outfit Bold'; -fx-font-weight: bold; -fx-font-size: 18px;");

        Label subLabel = new Label("Type: " + " " + results.carType + "  |  " + "Category: " + " " + results.carCategory);
        subLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #677F77;");

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);


        Button addToCompareBtn = new Button("Add to Compare");
        addToCompareBtn.setStyle("-fx-padding: 12px 16px 12px 16px; -fx-background-color: #178B63; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: semibold; -fx-font-size: 16px; -fx-font-family: 'Outfit Semibold'; -fx-text-fill: white;");

        addToCompareBtn.setOnAction(a -> {
            VBox newCard = createVehicleCard(results.carName, (results.emissionRate) + "kg CO2/km", results.emissionRate, "/images/Automobile.png", "", new Car(results.carName, results.carType, results.emissionRate, results.energyCost, results.carCategory));
            vehicleFlowPane.getChildren().add(newCard);
            clearAIResults();
        });

        Label emissionRate = new Label("Emission Rate: " + results.emissionRate + "kg CO2/km");
        Label energyCostRate = new Label("Energy Cost Rate: $" + results.energyCost + "/km");



        topPartLeft.getChildren().addAll(name, subLabel);
        topPart.getChildren().addAll(topPartLeft, space, addToCompareBtn);
        bottomPart.getChildren().addAll(emissionRate, energyCostRate);

        panel.getChildren().addAll(topPart, bottomPart);

        return panel;
     }

     public void validateAndUpdateButton(){
        if (!nameField.getText().isEmpty() && !distanceField.getText().isEmpty() && !vehicles.isEmpty()){
            compareBtn.setDisable(false);
        }else{
            compareBtn.setDisable(true);
        }
     }

    private void clearAIResults() {
        if (aiResultsBox != null) {
            aiSection.getChildren().remove(aiResultsBox);
            aiResultsBox = null;
        }
    }




    // Comparison Results

    public HBox createBestOptionPanel(ComparisonResult bestOption){
        HBox panel = new HBox();
        DecimalFormat df = new DecimalFormat();

        panel.setStyle("-fx-padding: 25px; -fx-background-color: #14A072; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        panel.prefWidthProperty().bind(sp.widthProperty().multiply(0.80));
        panel.setMaxWidth(Region.USE_PREF_SIZE);

        VBox leftPart = new VBox(12);

        HBox champTag = new HBox(5);
        champTag.setAlignment(Pos.CENTER_LEFT);

        Region champIcon = createSVGIcon(trophy, 18, "#fff");
        Label bestOptionlLabel = new Label("Best Option");
        bestOptionlLabel.setStyle("-fx-text-fill: '#fff'; -fx-font-size: 16px;");

        champTag.getChildren().addAll(champIcon, bestOptionlLabel);

        Label title = new Label(bestOption.getVehicleType());
        title.setStyle("-fx-text-fill: #fff; -fx-font-size: 24px; -fx-font-family: 'Outfit Bold'; -fx-font-weight: bold;");

        HBox statsRow = new HBox(20);

        HBox firstStat = new HBox(5);
        Region leafIcon = createSVGIcon(leaf, 18, "#fff");
        Label firstStatLabel = new Label(df.format(bestOption.getTotalEmissions()) + " kg CO2");
        firstStatLabel.setStyle("-fx-text-fill: #fff; -fx-font-size: 14px;");
        firstStat.getChildren().addAll(leafIcon, firstStatLabel);

        Label secondStatLabel = new Label("$ " + df.format(bestOption.getEnergyCost()) + " energy cost");
        secondStatLabel.setStyle("-fx-text-fill: #fff; -fx-font-size: 14px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        VBox rightPart = new VBox(5);

        Label savingsValue = new Label(df.format(bestOption.getSavings()) + " kg");
        savingsValue.setStyle("-fx-text-fill: #fff; -fx-font-size: 24px; -fx-font-family: 'Outfit Bold'; -fx-font-weight: bold;");

        Label savingsDesc = new Label("CO₂ saved vs worst");
        savingsDesc.setStyle("-fx-text-fill: #fff; -fx-font-size: 14px");

        rightPart.setAlignment(Pos.TOP_RIGHT);
        rightPart.getChildren().addAll(savingsValue, savingsDesc);


        statsRow.getChildren().addAll(firstStat, secondStatLabel);

        leftPart.getChildren().addAll(champTag, title, statsRow);



        panel.getChildren().addAll(leftPart, spacer, rightPart);
        panel.setAlignment(Pos.CENTER);

        return panel;
    }
}
