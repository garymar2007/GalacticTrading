package com.gary.GalacticTrading.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanStringToIntegerConverterTest {

    @Test
    void convertRomanStringToInteger() {
        RomanStringToIntegerConverter romanStringToIntegerConverter = new RomanStringToIntegerConverter();
        assertEquals(1, romanStringToIntegerConverter.convertRomanStringToInteger("I"));
        assertEquals(3, romanStringToIntegerConverter.convertRomanStringToInteger("III"));
        assertEquals(4, romanStringToIntegerConverter.convertRomanStringToInteger("IV"));
        assertEquals(9, romanStringToIntegerConverter.convertRomanStringToInteger("IX"));
        assertEquals(58, romanStringToIntegerConverter.convertRomanStringToInteger("LVIII"));
        assertEquals(1994, romanStringToIntegerConverter.convertRomanStringToInteger("MCMXCIV"));
    }
}