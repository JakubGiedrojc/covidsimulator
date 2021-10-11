package com.example.covidsimulator;

import com.example.covidsimulator.api.SimulationEntryDataDto;
import com.example.covidsimulator.database.entities.SimulationEntryData;
import com.example.covidsimulator.database.entities.SimulationOutput;
import com.example.covidsimulator.database.repositories.SimulationOutputRepository;
import com.example.covidsimulator.services.SimulationComputingService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Log
public class StartupRunner implements CommandLineRunner {
    @Autowired
    private SimulationComputingService simulationComputingService;
    @Autowired
    private SimulationOutputRepository simulationOutputRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("executing startup actions: ");
        SimulationEntryDataDto simulationEntryData = SimulationEntryDataDto.builder()
            .infectedPopulation(10)
            .population(1000)
            .rIndicator(2.0)
            .timeOfSimulation(24)
            .timeOfInfection(10)
            .mortality(0.05)
            .timeOfMortality(5)
            .name("test")
            .build();

        SimulationEntryDataDto simulationEntryData1 = SimulationEntryDataDto.builder()
            .infectedPopulation(20)
            .population(10000)
            .rIndicator(1.5)
            .timeOfSimulation(30)
            .timeOfInfection(20)
            .mortality(0.1)
            .timeOfMortality(5)
            .name("Test2").build();
        simulationComputingService.computeSimulation(simulationEntryData);
        simulationComputingService.computeSimulation(simulationEntryData1);

        log.info(simulationOutputRepository.findAllBySimulationEntryDataName(simulationEntryData.getName()).toString());

    }
}
