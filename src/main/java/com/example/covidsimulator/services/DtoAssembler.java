package com.example.covidsimulator.services;

import com.example.covidsimulator.api.SimulationEntryDataDto;
import com.example.covidsimulator.api.SimulationOutputDto;
import com.example.covidsimulator.database.entities.SimulationEntryData;
import com.example.covidsimulator.database.entities.SimulationOutput;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DtoAssembler {

    public SimulationEntryDataDto mapSimulationEntryData(SimulationEntryData simulationEntryData) {
        return SimulationEntryDataDto.builder()
                .id(simulationEntryData.getId())
                .infectedPopulation(simulationEntryData.getInfectedPopulation())
                .population(simulationEntryData.getPopulation())
                .mortality(simulationEntryData.getMortality())
                .rIndicator(simulationEntryData.getRIndicator())
                .timeOfInfection(simulationEntryData.getTimeOfInfection())
                .timeOfMortality(simulationEntryData.getTimeOfMortality())
                .timeOfSimulation(simulationEntryData.getTimeOfSimulation())
                .simulationOutputList(simulationEntryData.getSimulationOutputList() != null ?
                        simulationEntryData.getSimulationOutputList().stream()
                            .map(this::mapSimulationOutput)
                            .collect(Collectors.toList())
                        : null)
                .build();
    }

    public SimulationOutputDto mapSimulationOutput(SimulationOutput simulationOutput) {
        return SimulationOutputDto.builder()
                .id(simulationOutput.getId())
                .peopleDead(simulationOutput.getPeopleDead())
                .peopleInfected(simulationOutput.getPeopleInfected())
                .peopleResisted(simulationOutput.getPeopleResisted())
                .peopleVulnerable(simulationOutput.getPeopleVulnerable())
                .build();
    }
}
