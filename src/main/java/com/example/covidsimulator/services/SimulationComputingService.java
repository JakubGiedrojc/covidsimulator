package com.example.covidsimulator.services;

import com.example.covidsimulator.api.SimulationEntryDataDto;
import com.example.covidsimulator.api.SimulationOutputDto;
import com.example.covidsimulator.database.entities.SimulationEntryData;
import com.example.covidsimulator.database.entities.SimulationOutput;
import com.example.covidsimulator.database.repositories.SimulationEntrySetRepository;
import com.example.covidsimulator.database.repositories.SimulationOutputRepository;
import com.example.covidsimulator.exceptions.PopulationBelowZeroException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class SimulationComputingService {
    @Autowired
    private SimulationEntrySetRepository simulationEntrySetRepository;
    @Autowired
    private SimulationOutputRepository simulationOutputRepository;
    @Autowired
    private DtoAssembler dtoAssembler;
    @Autowired
    private EntityMapper entityMapper;

public void computeSimulation(SimulationEntryDataDto simulationEntryDataDto) throws PopulationBelowZeroException{

    SimulationEntryData simulationEntryData = entityMapper.mapSimulationEntryDataDto(simulationEntryDataDto);
    Integer numberOfIteration = simulationEntryDataDto.getTimeOfSimulation();
    Double rIndicator = simulationEntryDataDto.getRIndicator();
    Double mortalityRate = simulationEntryDataDto.getMortality();//not yet
    Integer basicPopulation = simulationEntryDataDto.getPopulation();
    double infectedPatients = simulationEntryDataDto.getInfectedPopulation(); //double
    double peopleVulnerable = basicPopulation-infectedPatients; //double
    Integer timeOfInfectionInDays = simulationEntryDataDto.getTimeOfInfection();
    Integer timeOfMort = simulationEntryDataDto.getTimeOfMortality();
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
    public List<SimulationOutputDto> retrieveListOfSimulation(String nameOfSimulation){
        List<SimulationOutput> list = this.simulationOutputRepository.findAllBySimulationEntryDataName(nameOfSimulation);
        SimulationEntryData entry = list.get(0).getSimulationEntryData();
        return list.stream().map(dtoAssembler::mapSimulationOutput).collect(Collectors.toList());
    }
    public void deleteSimulationDataByName(String name){
        SimulationEntryData simulationEntryData = this.simulationEntrySetRepository.findByName(name);
        this.simulationEntrySetRepository.delete(simulationEntryData);
    }
    public List<SimulationOutput> findAllSimulationOutputs(){
        return this.simulationOutputRepository.findAll();
    }


}

