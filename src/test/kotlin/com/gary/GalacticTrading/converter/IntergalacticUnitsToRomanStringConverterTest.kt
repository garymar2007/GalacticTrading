package com.gary.GalacticTrading.converter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class IntergalacticUnitsToRomanStringConverterTest {
    @Test
    fun convertIntergalacticUnitsToRomanString() {
        val intergalacticUnitsToRomanStringConverter =
            IntergalacticUnitsToRomanStringConverter()
        val interGalacticUnits = mutableMapOf<String, String>(
            "glob" to "I",
            "prok" to "V",
            "pish" to "X",
            "tegj" to "L"
        )
        intergalacticUnitsToRomanStringConverter.setInterGalacticUnits(interGalacticUnits)
        Assertions.assertEquals(
            "I",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob")
        )
        Assertions.assertEquals(
            "V",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("prok")
        )
        Assertions.assertEquals(
            "X",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("pish")
        )
        Assertions.assertEquals(
            "L",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("tegj")
        )
        Assertions.assertEquals(
            "II",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob glob")
        )
        Assertions.assertEquals(
            "IV",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob prok")
        )
        Assertions.assertEquals(
            "IX",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("glob pish")
        )
        Assertions.assertEquals(
            "VI",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("prok glob")
        )
        Assertions.assertEquals(
            "XLII",
            intergalacticUnitsToRomanStringConverter.convertIntergalacticUnitsToRomanString("pish tegj glob glob")
        )
    }
}