package com.example.covidsimulator.database.repositories;

import com.example.covidsimulator.database.entities.SimulationEntryData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationEntrySetRepository extends JpaRepository<SimulationEntryData,Integer> {
    SimulationEntryData findByName(String name);
}
