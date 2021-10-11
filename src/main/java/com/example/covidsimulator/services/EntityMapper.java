package com.example.covidsimulator.services;

import com.example.covidsimulator.api.SimulationEntryDataDto;
import com.example.covidsimulator.database.entities.SimulationEntryData;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public SimulationEntryData mapSimulationEntryDataDto(SimulationEntryDataDto simulationEntryDataDto) {
        SimulationEntryData simulationEntryData = new SimulationEntryData();
        simulationEntryData.setId(simulationEntryDataDto.getId());
        simulationEntryData.setInfectedPopulation(simulationEntryDataDto.getInfectedPopulation());
        simulationEntryData.setPopulation(simulationEntryDataDto.getPopulation());
        simulationEntryData.setMortality(simulationEntryDataDto.getMortality());
        simulationEntryData.setRIndicator(simulationEntryDataDto.getRIndicator());
        simulationEntryData.setTimeOfInfection(simulationEntryDataDto.getTimeOfInfection());
        simulationEntryData.setTimeOfMortality(simulationEntryDataDto.getTimeOfMortality());
        simulationEntryData.setTimeOfSimulation(simulationEntryDataDto.getTimeOfSimulation());
        return simulationEntryData;
    }
}
