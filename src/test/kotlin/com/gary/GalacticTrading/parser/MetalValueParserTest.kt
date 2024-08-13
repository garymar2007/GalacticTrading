package com.gary.GalacticTrading.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetalValueParserTest {
    private MetalValueParser metalValueParser;

    @BeforeEach
    void setUp() {
        metalValueParser = new MetalValueParser();
    }

    @Test
    void parseMetalValue() {
        metalValueParser.parseMetalValue("glob glob Silver is 34 Credits");
        assertEquals("Silver", metalValueParser.getMetalName());
        assertEquals(34, metalValueParser.getValue());
        assertEquals("glob glob", metalValueParser.getInterGalacticUnitString());
        metalValueParser.reset();

        metalValueParser.parseMetalValue("glob prok Gold is 57800 Credits");
        assertEquals("Gold", metalValueParser.getMetalName());
        assertEquals(57800, metalValueParser.getValue());
        assertEquals("glob prok", metalValueParser.getInterGalacticUnitString());
        metalValueParser.reset();

        metalValueParser.parseMetalValue("pish pish Iron is 3910 Credits");
        assertEquals("Iron", metalValueParser.getMetalName());
        assertEquals(3910, metalValueParser.getValue());
        assertEquals("pish pish", metalValueParser.getInterGalacticUnitString());
        metalValueParser.reset();
    }
}