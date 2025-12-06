# 🚌 Public Transit Route Eco-Analyzer

A JavaFX application that compares the environmental impact and energy costs of different public transit vehicles, enabling data-driven decisions for sustainable transportation planning.

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-21-blue.svg)

## 📋 Overview

The Public Transit Route Eco-Analyzer is an Object-Oriented Programming project developed for CS 213 at Ashesi University. It models transit routes with different vehicle types (buses and trains) and calculates their respective CO2 emissions and energy costs, providing clear comparisons to demonstrate the environmental benefits of electric vehicles over diesel alternatives.

## ✨ Features

- **Route Configuration**: Define custom transit routes with distance and number of stops
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

## 🛠️ Technologies Used

- **Language**: Java 17+
- **UI Framework**: JavaFX 21
- **Architecture**: Object-Oriented Programming (OOP)
- **Design Patterns**: Inheritance, Polymorphism, Composition

## 📐 System Architecture

### Class Structure
```
Vehicle (Abstract)
├── Bus
└── Train

TransitRoute
EmissionComparison
ComparisonResult
```

### Key OOP Concepts
- **Inheritance**: Bus and Train classes extend the abstract Vehicle class
- **Polymorphism**: Different vehicle types implement common calculation methods
- **Encapsulation**: Data and behavior bundled within appropriate classes
- **Composition**: TransitRoute contains Vehicle objects

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- JavaFX SDK 21 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/transit-route-eco-analyzer.git
cd transit-route-eco-analyzer
```

2. Open the project in your IDE

3. Configure JavaFX in your project settings

4. Run the main application class

## 💻 Usage

1. **Configure Route**: Enter route name, distance (km), and number of stops
2. **Select Vehicles**: Choose one or more vehicle types to compare
3. **Compare**: Click "Compare Emissions" to generate results
4. **Analyze**: Review the comparison table and identify the most efficient option

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

## 📊 Emission Data Sources

Emission rates are based on:
- UK Government Greenhouse Gas Conversion Factors 2025
- International research on electric vs diesel public transport
- Data adapted for Ghanaian energy context

### Default Emission Rates (kg CO2/km)

| Vehicle Type | Emission Rate |
|-------------|---------------|
| Electric Bus | 0.34 |
| Diesel Bus | 0.85 |
| Hybrid Bus | 0.55 |
| Electric Train | 0.05 |
| Diesel Train | 0.08 |

## 👥 Team

**CS 213 OOP Project Team**
- Daniel Glover
- Vladimir Noel Aduama
- Davis Baffour Addo
- Samuel Asare Oppong

**Instructor**: Dr. Daniel Addo

## 🎓 Academic Context

This project was developed as part of the CS 213 Object-Oriented Programming course at Ashesi University (September - December 2025). The project demonstrates the application of OOP principles to solve real-world problems related to climate change and environmental sustainability.

## 📝 Project Requirements

- ✅ Minimum 4 interacting classes
- ✅ Use of inheritance and polymorphism
- ✅ Java collections (ArrayList)
- ✅ Substantial logic in methods
- ✅ Climate change/sustainability focus
- ✅ Team collaboration

```

