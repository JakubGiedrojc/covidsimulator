package com.example.covidsimulator.database.repositories;

import com.example.covidsimulator.database.entities.SimulationOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SimulationOutputRepository extends JpaRepository<SimulationOutput, Integer> {

    @Query(value = "select output from SimulationOutput output where output.simulationEntryData.name = ?1")
    List<SimulationOutput> findAllBySimulationEntryDataName(String name);
}
