package com.example.covidsimulator.database.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "simulation_output")
public class SimulationOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Pi")
    private Double peopleInfected;
    @Column(name = "Pv")
    private Double peopleVulnerable;
    @Column(name = "Pm")
    private Double peopleDead;
    @Column(name = "Pr")
    private Double peopleResisted;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "entry_id",referencedColumnName = "id")
    private SimulationEntryData simulationEntryData;



}
