package com.example.covidsimulator;

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
        SimulationEntryData simulationEntryData = new SimulationEntryData();
        simulationEntryData.setInfectedPopulation(10);
        simulationEntryData.setPopulation(1000);
        simulationEntryData.setRIndicator(2.0);
        simulationEntryData.setTimeOfSimulation(24);
        simulationEntryData.setTimeOfInfection(10);
        simulationEntryData.setMortality(0.05);
        simulationEntryData.setTimeOfMortality(5);
        simulationEntryData.setName("Test");

        SimulationEntryData simulationEntryData1 = new SimulationEntryData();
        simulationEntryData1.setInfectedPopulation(20);
        simulationEntryData1.setPopulation(10000);
        simulationEntryData1.setRIndicator(1.5);
        simulationEntryData1.setTimeOfSimulation(30);
        simulationEntryData1.setTimeOfInfection(20);
        simulationEntryData1.setMortality(0.1);
        simulationEntryData1.setTimeOfMortality(5);
        simulationEntryData1.setName("Test2");
        simulationComputingService.computeSimulation(simulationEntryData);
        simulationComputingService.computeSimulation(simulationEntryData1);

        log.info(simulationOutputRepository.findAllBySimulationEntryDataName(simulationEntryData.getName()).toString());

    }
}
