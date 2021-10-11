package com.example.covidsimulator.services;

import com.example.covidsimulator.database.entities.SimulationEntryData;
import com.example.covidsimulator.database.entities.SimulationOutput;
import com.example.covidsimulator.database.repositories.SimulationEntrySetRepository;
import com.example.covidsimulator.database.repositories.SimulationOutputRepository;
import com.example.covidsimulator.exceptions.PopulationBelowZeroException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class SimulationComputingService {
    @Autowired
    private SimulationEntrySetRepository simulationEntrySetRepository;
    @Autowired
    private SimulationOutputRepository simulationOutputRepository;

public void computeSimulation(SimulationEntryData simulationEntryData) throws PopulationBelowZeroException{

    Integer numberOfIteration = simulationEntryData.getTimeOfSimulation();
    Double rIndicator = simulationEntryData.getRIndicator();
    Double mortalityRate = simulationEntryData.getMortality();//not yet
    Integer basicPopulation = simulationEntryData.getPopulation();
    double infectedPatients = simulationEntryData.getInfectedPopulation(); //double
    double peopleVulnerable = basicPopulation-infectedPatients; //double
    Integer timeOfInfectionInDays = simulationEntryData.getTimeOfInfection();
    Integer timeOfMort = simulationEntryData.getTimeOfMortality();
    double peopleDied = 0; //double
    double peopleResisted = 0; // double

    double[] arrayOfCohortsOfPeopleWhoSurvived = new double[numberOfIteration];

    for (int i = 0; i < numberOfIteration; i++) {
        SimulationOutput simulationOutput = new SimulationOutput();
        if (i>=timeOfInfectionInDays) {
            peopleResisted=arrayOfCohortsOfPeopleWhoSurvived[i-timeOfInfectionInDays];
        }
        if (i>=timeOfMort) {

            peopleDied = infectedPatients*mortalityRate;
        }

        if (peopleVulnerable<0) {
            throw new PopulationBelowZeroException(numberOfIteration);
        }
        infectedPatients = (infectedPatients+infectedPatients*rIndicator/timeOfInfectionInDays);
        arrayOfCohortsOfPeopleWhoSurvived[i] = infectedPatients;
        peopleVulnerable = basicPopulation-infectedPatients;
        simulationOutput.setPeopleInfected(infectedPatients-peopleDied-peopleResisted);
        simulationOutput.setPeopleVulnerable(peopleVulnerable);
        simulationOutput.setPeopleResisted(peopleResisted);
        simulationOutput.setPeopleDead(peopleDied);
        simulationEntryData.addSimulationOutput(simulationOutput);
    }
    this.simulationEntrySetRepository.save(simulationEntryData);
    }
    public List<SimulationOutput> retrieveListOfSimulation(String nameOfSimulation){
        return this.simulationOutputRepository.findAllBySimulationEntryDataName(nameOfSimulation);
    }
    public void deleteSimulationDataByName(String name){
        SimulationEntryData simulationEntryData = this.simulationEntrySetRepository.findByName(name);
        this.simulationEntrySetRepository.delete(simulationEntryData);
    }
    public List<SimulationOutput> findAllSimulationOutputs(){
        return this.simulationOutputRepository.findAll();
    }


}

