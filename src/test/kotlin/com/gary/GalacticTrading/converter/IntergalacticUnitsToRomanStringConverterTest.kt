package com.gary.GalacticTrading.converter;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IntergalacticUnitsToRomanStringConverterTest {

    @Test
    void convertIntergalacticUnitsToRomanString() {
        IntergalacticUnitsToRomanStringConverter intergalacticUnitsToRomanStringConverter =
                new IntergalacticUnitsToRomanStringConverter(Map.of("glob", "I", "prok", "V", "pish", "X", "tegj", "L"));
        assertEquals("I", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob"));
        assertEquals("V", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("prok"));
        assertEquals("X", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("pish"));
        assertEquals("L", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("tegj"));
        assertEquals("II", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob glob"));
        assertEquals("IV", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob prok"));
        assertEquals("IX", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob pish"));
        assertEquals("VI", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("prok glob"));
        assertEquals("XLII", intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("pish tegj glob glob"));
    }
}