package com.gary.GalacticTrading.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanStringToIntegerConversionTest {

    @Test
    void convertRomanStringToInteger() {
        RomanStringToIntegerConversion romanStringToIntegerConversion = new RomanStringToIntegerConversion();
        assertEquals(1, romanStringToIntegerConversion.convertRomanStringToInteger("I"));
        assertEquals(3, romanStringToIntegerConversion.convertRomanStringToInteger("III"));
        assertEquals(4, romanStringToIntegerConversion.convertRomanStringToInteger("IV"));
        assertEquals(9, romanStringToIntegerConversion.convertRomanStringToInteger("IX"));
        assertEquals(58, romanStringToIntegerConversion.convertRomanStringToInteger("LVIII"));
        assertEquals(1994, romanStringToIntegerConversion.convertRomanStringToInteger("MCMXCIV"));
    }
}