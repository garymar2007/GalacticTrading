package com.gary.GalacticTrading.calculator

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter
import com.gary.GalacticTrading.converter.RomanStringToIntegerConverter
import com.gary.GalacticTrading.validator.RomanSymbolRules
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MetalAndMultipleCalculatorTest {
    private lateinit var metalAndMultipleCalculator: MetalAndMultipleCalculator

    @BeforeEach
    fun setUp() {
        val interGalacticUnitsnte = mutableMapOf<String, String>(
            "glob" to "I",
            "prok" to "V",
            "pish" to "X",
            "tegj" to "L"
        )
        val intergalacticUnitsToRomanStringConverter = IntergalacticUnitsToRomanStringConverter()
        intergalacticUnitsToRomanStringConverter.setInterGalacticUnits(interGalacticUnitsnte)

        val romanStringToIntegerConverter = RomanStringToIntegerConverter()
        val romanSymbolRules = RomanSymbolRules()
        metalAndMultipleCalculator = MetalAndMultipleCalculator(
            romanSymbolRules, romanStringToIntegerConverter,
            intergalacticUnitsToRomanStringConverter
        )
    }

    @Test
    fun `test that initializing metal and multipler`() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob glob", "Silver", 34)
        Assertions.assertEquals(
            17.0,
            metalAndMultipleCalculator.metalNameMultiplerMap["Silver"]
        )
    }

    @Test
    fun `test that initialzing gold and multipler`() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob prok", "Gold", 57800)
        Assertions.assertEquals(
            14450.0,
            metalAndMultipleCalculator.metalNameMultiplerMap.get("Gold") ?: 0.0
        )
    }

    @Test
    fun `test that initializing Iron and multipler`() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("pish pish", "Iron", 3910)
        Assertions.assertEquals(195.5, metalAndMultipleCalculator.metalNameMultiplerMap["Iron"])
    }

    @Test
    fun `test that initializing three metals and their multiplers`() {
        metalAndMultipleCalculator.initializeMetalAndMultipler("pish pish", "Iron", 3910)
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob glob", "Silver", 34)
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob prok", "Gold", 57800)
        Assertions.assertEquals(
            14450.0,
            metalAndMultipleCalculator.metalNameMultiplerMap.get("Gold") ?: 0.0
        )
        Assertions.assertEquals(
            17.0,
            metalAndMultipleCalculator.metalNameMultiplerMap.get("Silver") ?: 0.0
        )
        Assertions.assertEquals(195.5, metalAndMultipleCalculator.metalNameMultiplerMap.get("Iron") ?: 0.0 )
    }

    @Test
    fun `test that calculating metal value`() {
        Assertions.assertEquals(
            42, metalAndMultipleCalculator.calculateMetalValue(
                "pish tegj glob glob",
                ""
            )
        )

        metalAndMultipleCalculator.initializeMetalAndMultipler("glob glob", "Silver", 34)
        metalAndMultipleCalculator.initializeMetalAndMultipler("glob prok", "Gold", 57800)
        metalAndMultipleCalculator.initializeMetalAndMultipler("pish pish", "Iron", 3910)
        Assertions.assertEquals(68, metalAndMultipleCalculator.calculateMetalValue("glob prok", "Silver"))
        Assertions.assertEquals(57800, metalAndMultipleCalculator.calculateMetalValue("glob prok", "Gold"))
        Assertions.assertEquals(782, metalAndMultipleCalculator.calculateMetalValue("glob prok", "Iron"))
    }
}