package com.example.covidsimulator.controllers;

import com.example.covidsimulator.api.SimulationEntryDataDto;
import com.example.covidsimulator.api.SimulationOutputDto;
import com.example.covidsimulator.database.entities.SimulationEntryData;
import com.example.covidsimulator.database.entities.SimulationOutput;
import com.example.covidsimulator.exceptions.PopulationBelowZeroException;
import com.example.covidsimulator.services.SimulationComputingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
@RequiredArgsConstructor
@RequestMapping("covidsimulator")
public class SimulationOutputController {
    private final SimulationComputingService simulationComputingService;

    @PostMapping("createsimulation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSimulation(@RequestBody SimulationEntryDataDto simulationEntryDataDto) throws PopulationBelowZeroException {
        log.info(String.valueOf(simulationEntryDataDto));
        this.simulationComputingService.computeSimulation(simulationEntryDataDto);
        log.info("new simulation created");
    }
    @GetMapping("findsimulation/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<SimulationOutputDto> findOutputSimulation(@PathVariable String name){
        log.info("List of SimulationOutputFound for: "+name);
        log.info(this.simulationComputingService.retrieveListOfSimulation(name).stream().toString());
        return this.simulationComputingService.retrieveListOfSimulation(name);
    }
    @DeleteMapping("deletesimulation/{name}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteEntryDataAndCorrespondingSimulation(@PathVariable String name){
        log.info("deleting data");
        this.simulationComputingService.deleteSimulationDataByName(name);
        log.info("Data deleted for entry: " + name + " and corresponding simulation outputs");
    }
    @GetMapping("findallsimulations")
    @ResponseStatus(HttpStatus.FOUND)
    public List<SimulationOutput> findAllSimulationOutputs(){
        return this.simulationComputingService.findAllSimulationOutputs();
    }

    @PutMapping("modifysimulation")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public void modifySimulation(@RequestParam String name){

    }


}
