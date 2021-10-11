package com.example.covidsimulator.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SimulationOutputDto {
    private Integer id;
    private Double peopleInfected;
    private Double peopleVulnerable;
    private Double peopleDead;
    private Double peopleResisted;
}
