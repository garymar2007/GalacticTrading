package com.gary.GalacticTrading.converter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class RomanStringToIntegerConverterTest {
    @Test
    fun convertRomanStringToInteger() {
        val romanStringToIntegerConverter = RomanStringToIntegerConverter()
        Assertions.assertEquals(1, romanStringToIntegerConverter.convertRomanStringToInteger("I"))
        Assertions.assertEquals(3, romanStringToIntegerConverter.convertRomanStringToInteger("III"))
        Assertions.assertEquals(4, romanStringToIntegerConverter.convertRomanStringToInteger("IV"))
        Assertions.assertEquals(9, romanStringToIntegerConverter.convertRomanStringToInteger("IX"))
        Assertions.assertEquals(58, romanStringToIntegerConverter.convertRomanStringToInteger("LVIII"))
        Assertions.assertEquals(1994, romanStringToIntegerConverter.convertRomanStringToInteger("MCMXCIV"))
    }
}