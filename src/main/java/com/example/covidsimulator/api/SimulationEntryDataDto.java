package com.example.covidsimulator.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class SimulationEntryDataDto {

    private Integer id;
    private String name;
    private Integer population;
    private Integer infectedPopulation;
    private Double rIndicator;
    private Double mortality;
    private Integer timeOfInfection;
    private Integer timeOfMortality;
    private Integer timeOfSimulation;
    private List<SimulationOutputDto> simulationOutputList;
}
