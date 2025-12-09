# Public Transit Route Eco-Analyzer

A JavaFX application that compares the emissions and energy costs of different public and private vehicles, enabling data-driven decisions for sustainable transportation planning.

## Overview

The Public Transit Route Eco-Analyzer is an Object-Oriented Programming project developed for CS 213 at Ashesi University. It models transit routes with different vehicle types (buses and trains) and calculates their respective CO2 emissions and energy costs, providing clear comparisons to demonstrate the environmental benefits of electric vehicles over diesel alternatives.

## Features

- **Route Configuration**: Define custom transit routes with their distance 
- **Vehicle Comparison**: Compare emissions across multiple vehicle types:
  - Electric Bus
  - Diesel Bus
  - Hybrid Bus
  - Electric Train
  - Diesel Train
- **Emission Calculations**: Real-time calculation of CO2 emissions based on route distance
- **Cost Analysis**: Energy cost computation for different vehicle types
- **Savings Visualization**: Clear display of environmental savings when switching to electric vehicles
- **Interactive UI**: User-friendly JavaFX interface with visual feedback

## Technologies Used

- **Language**: Java 17+
- **UI Framework**: JavaFX 21
- **Design Patterns**: Inheritance, Polymorphism, Composition, Data modelling

## System Architecture

### Class Structure
```
Vehicle (Abstract)
|- Bus
|- Train
|- Car

TransitRoute
EmissionComparison
ComparisonResult
GenerateEmissions (where the AI API calls are made)
AIResponse (data structure for the response output from the AI)
```

### Key OOP Concepts
- **Inheritance**: Bus, Car and Train classes extend the abstract Vehicle class
- **Polymorphism**: Different vehicle types implement common calculation methods
- **Composition**: TransitRoute contains Vehicle objects

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- JavaFX SDK 21 or higher
- IDE (IntelliJ IDEA recommended)
- An API key to use for Gemini

### Installation

1. Clone the repository 
```bash
git clone https://github.com/Glover69/transit-route-eco-analyzer.git
cd transit-route-eco-analyzer
```

2. Open the project in your IDE

3. Configure JavaFX in your project settings

4. Run the main application class

## Usage

1. **Configure Route**: Enter route name and the distance (in km)
2. **Select Vehicles**: Choose one or more vehicle types to compare (or lookup a specific one you want)
3. **Compare**: Click "Compare Emissions" to generate results

### Example Output
```
Route: City Center Loop
Distance: 50 km

Vehicle: Electric Bus
- Total Emissions: 17.00 kg CO2
- Energy Cost: GHS 7.50
- Savings: 25.50 kg CO2 (vs Diesel)

Vehicle: Diesel Bus
- Total Emissions: 42.50 kg CO2
- Energy Cost: GHS 15.00
- Savings: 0.00 kg CO2 (baseline)
```

## Team

- Daniel Glover
- Vladimir Noel Aduama
- Davis Baffour Addo
- Samuel Asare Oppong

**Instructor**: Dr. Daniel Addo

```

