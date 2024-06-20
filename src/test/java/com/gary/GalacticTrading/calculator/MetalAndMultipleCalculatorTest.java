package com.gary.GalacticTrading.calculator;

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter;
import com.gary.GalacticTrading.converter.RomanStringToIntegerConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MetalAndMultipleCalculatorTest {
    private MetalAndMultipleCalculator metalAndMultipleCalculator;
    private IntergalacticUnitsToRomanStringConverter intergalacticUnitsToRomanStringConverter;
    private RomanStringToIntegerConverter romanStringToIntegerConverter;

    @BeforeEach
    void setUp() {
        intergalacticUnitsToRomanStringConverter = new IntergalacticUnitsToRomanStringConverter(
                Map.of("glob", "I", "prok", "V", "pish", "X", "tegj", "L"));
        romanStringToIntegerConverter = new RomanStringToIntegerConverter();
        metalAndMultipleCalculator = new MetalAndMultipleCalculator(romanStringToIntegerConverter,
                intergalacticUnitsToRomanStringConverter);
    }

    @Test
    void initializeSilverAndMultipler() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob glob", "Silver", 34);
        assertEquals(17, metalAndMultipleCalculator.getMetalNameMultiplerMap().get("Silver"));
    }

    @Test
    void initializeGoldAndMultipler() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob prok", "Gold", 57800);
        assertEquals(14450, metalAndMultipleCalculator.getMetalNameMultiplerMap().get("Gold"));
    }

    @Test
    void initializeIronAndMultipler() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("pish pish", "Iron", 3910);
        assertEquals(195.5, metalAndMultipleCalculator.getMetalNameMultiplerMap().get("Iron"));
    }

    @Test
    void initializeThreeMetalsAndTheirMultiplers() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("pish pish", "Iron", 3910);
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob glob", "Silver", 34);
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob prok", "Gold", 57800);
        assertEquals(14450, metalAndMultipleCalculator.getMetalNameMultiplerMap().get("Gold"));
        assertEquals(17, metalAndMultipleCalculator.getMetalNameMultiplerMap().get("Silver"));
        assertEquals(195.5, metalAndMultipleCalculator.getMetalNameMultiplerMap().get("Iron"));
    }

    @Test
    void calculateMetalValue() {
        assertEquals(42, metalAndMultipleCalculator.calculateMetalValue("pish tegj glob glob",
                null));

        metalAndMultipleCalculator.initializeMetalAndMultipler("glob glob", "Silver", 34);
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob prok", "Gold", 57800);
        metalAndMultipleCalculator.initializeMetalAndMultipler("pish pish", "Iron", 3910);
        assertEquals(68, metalAndMultipleCalculator.calculateMetalValue("glob prok", "Silver"));
        assertEquals(57800, metalAndMultipleCalculator.calculateMetalValue("glob prok", "Gold"));
        assertEquals(782, metalAndMultipleCalculator.calculateMetalValue("glob prok", "Iron"));
    }
}