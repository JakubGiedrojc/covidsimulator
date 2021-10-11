package com.example.covidsimulator.database.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "entry_simulation")
public class SimulationEntryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "P")
    private Integer population;
    @Column(name = "I")
    private Integer infectedPopulation;
    @Column(name = "R")
    private Double rIndicator;
    @Column(name = "M")
    private Double mortality;
    @Column(name = "Ti")
    private Integer timeOfInfection;
    @Column(name="Tm")
    private Integer timeOfMortality;
    @Column(name = "Ts")
    private Integer timeOfSimulation;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id",referencedColumnName = "id")
    private List<SimulationOutput> simulationOutputList;

    public void addSimulationOutput(SimulationOutput simulationOutput) {
        if (simulationOutputList==null) {
            simulationOutputList=new ArrayList<>();
        }
        simulationOutputList.add(simulationOutput);
    }

}
