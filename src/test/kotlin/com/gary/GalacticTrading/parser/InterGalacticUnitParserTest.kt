package com.gary.GalacticTrading.parser;

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter;
import com.gary.GalacticTrading.exception.InvalidIntergalacticUnitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InterGalacticUnitParserTest {
    private InterGalacticUnitParser intergalacticUnitParser;
    private IntergalacticUnitsToRomanStringConverter intergalacticUnitsToRomanStringConverter;

    @BeforeEach
    void setUp() {
        intergalacticUnitsToRomanStringConverter = new IntergalacticUnitsToRomanStringConverter();
        intergalacticUnitParser = new InterGalacticUnitParser(intergalacticUnitsToRomanStringConverter);
    }

    @Test
    void parseIntergalacticUnits() {
        intergalacticUnitParser.parseIntergalacticUnits("glob is I");
        intergalacticUnitParser.parseIntergalacticUnits("prok is V");
        intergalacticUnitParser.parseIntergalacticUnits("pish is X");
        intergalacticUnitParser.parseIntergalacticUnits("tegj is L");

        assertEquals(4, intergalacticUnitParser.getInterGalacticUnits().size());
    }

    @Test
    void getInterGalacticUnits() {
        assertEquals(0, intergalacticUnitParser.getInterGalacticUnits().size());
        intergalacticUnitParser.parseIntergalacticUnits("glob is I");
        Map<String, String> interGalacticUnits = intergalacticUnitParser.getInterGalacticUnits();
        assertEquals(1, intergalacticUnitParser.getInterGalacticUnits().size());
        assertEquals("I", interGalacticUnits.get("glob"));
    }

    @Test
    void getInvalidInterGalacticUnits() {
        assertThrows(InvalidIntergalacticUnitException.class, () -> {
            intergalacticUnitParser.parseIntergalacticUnits("glob is I is I");
        });
    }
}