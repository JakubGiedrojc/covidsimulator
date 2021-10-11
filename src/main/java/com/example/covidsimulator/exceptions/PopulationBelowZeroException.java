package com.example.covidsimulator.exceptions;


public class PopulationBelowZeroException extends Exception{

    public PopulationBelowZeroException(int days){ super("During "+days+"all population will be infected");

    }
}
